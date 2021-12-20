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

import com.hdecor.dao.AreaDao;
import com.hdecor.dao.UserDao;
import com.hdecor.model.Admin;
import com.hdecor.model.Area;
import com.hdecor.model.ConfigProperties;
import com.hdecor.model.User;
import com.hdecor.service.UserService;
import com.hdecor.util.FTPUtils;
import com.hdecor.util.FileUtils;
import com.hdecor.util.Mailer;
import com.hdecor.util.PasswordEncode;
import com.hdecor.util.RandomString;

@Service("userservice")
@Transactional
@SessionAttributes("admin")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userdao;

	@Autowired
	HttpSession session;

	@Autowired
	AreaDao areadao;

	public long addUser(User user, MultipartFile file1) {
		String UPLOADED_FOLDER = ConfigProperties.getFolderPath() + "userProfilePicture" + File.separator;
		String newPP = FileUtils.getFileName(file1.getOriginalFilename());
		user.setProfilePicture(newPP);
		user.setProfileImage(file1);
		String password = RandomString.getAlphaNumericString(8);
		Admin ad = (Admin)session.getAttribute("admin");
		user.setCreatedBy(ad.getAdminId());
		user.setIsActive(1);
		user.setCreatedDate(new Date(System.currentTimeMillis()));

		int result = userdao.emailCheck(user);
		if (result != 0) {
			return 0;
		}
		if (user != null) {
			user.setPassword(PasswordEncode.passwordEncode(password));
		}

		User a = userdao.saveObject(user);
		if (a != null) {
			Mailer.send(ConfigProperties.getMailId(), ConfigProperties.getMailPassword(), user.getEmailId(),
					"Welcome to hDecor!", " Hello " + user.getFname() + ", \nYour password is" + password);
			FileUtils.uploadImageFile(file1, UPLOADED_FOLDER + newPP);
			FTPUtils.uploadOnFTP(UPLOADED_FOLDER + newPP, newPP);
			return a.getUserId();
		} else {
			return 0;
		}
	}

	public User getById(long id) {
		User user = userdao.getById(User.class, id);
		return user;
	}

	public boolean deleteUser(long id) {
		return userdao.deleteUser(id);
	}

	public List<User> getAll() {
		return userdao.getAll();
	}

	public User getAllDetails(long userId) {
		User u = userdao.getById(User.class, userId);
		String UPLOADED_FOLDER = ConfigProperties.getFolderPath() + "userProfilePicture" + File.separator;
		String newPP = u.getProfilePicture();
		File ProfileImage = new File(UPLOADED_FOLDER + newPP);
		if (!ProfileImage.exists()) {
			FTPUtils.download(newPP, UPLOADED_FOLDER + newPP);
		}
		Area a = areadao.getAllDetails(u.getAreaId());
		u.setStateId(a.getStateId());
		u.setStateName(a.getStateName());
		u.setCityId(a.getCityId());
		u.setCityName(a.getCityName());
		u.setAreaName(a.getAreaName());
		return u;
	}

}
