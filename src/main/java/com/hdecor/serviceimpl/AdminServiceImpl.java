package com.hdecor.serviceimpl;

import java.io.File;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.hdecor.model.Admin;
import com.hdecor.model.ConfigProperties;
import com.hdecor.util.FTPUtils;
import com.hdecor.dao.AdminDao;
import com.hdecor.service.AdminService;
import com.hdecor.util.FileUtils;
import com.hdecor.util.Mailer;
import com.hdecor.util.PasswordEncode;
import com.hdecor.util.RandomString;


@Service("adminService")
@Transactional
@SessionAttributes("admin")
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminDao adminDao;
	
	@Autowired
	HttpSession session;
	
	public long saveAdmin(Admin admin1, MultipartFile file1) throws Exception {
		String UPLOADED_FOLDER1 = ConfigProperties.getFolderPath() + "profilePicture"+File.separator;
		String newPP=FileUtils.getFileName(file1.getOriginalFilename());
		admin1.setProfilePicture(newPP);
		admin1.setProfileImage(file1);
		String password = RandomString.getAlphaNumericString(8);
		admin1.setPassword(PasswordEncode.passwordEncode(password));
		Admin ad=(Admin)session.getAttribute("admin");
		admin1.setIsActive(1);
		admin1.setIsDeleted(0);
		admin1.setCreatedBy(ad.getAdminId());
		admin1.setCreatedDate(new Date(System.currentTimeMillis()));
		int result = adminDao.emailCheck(admin1);
		if(result!=0)
		{
			throw new Exception("Admin with given email already exists");
		}
		Admin a=adminDao.saveObject(admin1);
		if(a!=null)
		{
			Mailer.send(ConfigProperties.getMailId(), ConfigProperties.getMailPassword(), admin1.getEmailId(),"Welcome To hDecor!" , "Hello " + admin1.getFname() + ",\nYour password is " + password);
			FileUtils.uploadImageFile(file1, UPLOADED_FOLDER1+newPP);
			FTPUtils.uploadOnFTP(UPLOADED_FOLDER1+newPP, newPP);
			return a.getAdminId();
		}
		return 0;
	}

	public List<Admin> getAll(){
		return adminDao.getAll();
	}
	
	public Admin getById(long id) {
		Admin admin = adminDao.getById(Admin.class, id);
		String newPP = admin.getProfilePicture();
		String UPLOADED_FOLDER1 = ConfigProperties.getFolderPath() + "profilePicture"+File.separator;
		File ProfileImage = new File(UPLOADED_FOLDER1+newPP);
		if(!ProfileImage.exists())
		{
			FTPUtils.download(newPP, UPLOADED_FOLDER1+newPP);
		}
		return admin;

	}
	public long updateAdmin(Admin admin, MultipartFile file1){
		Admin oldad=adminDao.getById(Admin.class,admin.getAdminId());
		Admin ad = (Admin) session.getAttribute("admin");
		admin.setCreatedBy(oldad.getCreatedBy());
		admin.setPassword(oldad.getPassword());
		admin.setModifiedBy(ad.getAdminId());
		admin.setModifiedDate(new Date(System.currentTimeMillis()));
		if(file1.isEmpty()) {
			admin.setProfilePicture(oldad.getProfilePicture());
		}
		else if(!oldad.getProfilePicture().equals(file1.getOriginalFilename()))
		{
			String UPLOADED_FOLDER1 = ConfigProperties.getFolderPath() + "profilePicture"+File.separator;
			String newPP = FileUtils.getFileName(file1.getOriginalFilename());
			FileUtils.uploadImageFile(file1, UPLOADED_FOLDER1+newPP);
			FTPUtils.uploadOnFTP(UPLOADED_FOLDER1+newPP, newPP);
			admin.setProfilePicture(newPP);
		}
		Admin a1 = adminDao.updateObject(admin);
		return a1.getAdminId();
	}

	public boolean deleteAdmin(long id) {
		return adminDao.deleteAdmin(id);
	}
	
}