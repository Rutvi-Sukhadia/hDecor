package com.hdecor.daoimpl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hdecor.dao.AdminDao;
import com.hdecor.model.Admin;
@Repository("adminDao")
@SessionAttributes("admin")
public class AdminDaoImpl extends GenericDaoImpl<Admin> implements AdminDao {

	@Autowired
	HttpSession session;
	
	public Admin loginCheck(Admin admin) {
		String query = "FROM Admin where emailId='" + admin.getEmailId() + "' and password='" + admin.getPassword() + "'  and isDeleted=0";
		List<Admin> list = super.getByQuery(query);
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);

	}
	public int emailCheck(Admin admin) {
		String query = "FROM Admin a where a.emailId='"+admin.getEmailId()+"' and a.isDeleted=0";
		List<Admin> list = super.getByQuery(query);
		return list.size();
	}
	
	public List<Admin> getAll() 
	{
		Admin ad = (Admin)session.getAttribute("admin");
		return super.getByQuery("FROM Admin a WHERE a.isDeleted=0 and a.emailId not like '"+ad.getEmailId()+"'");
	}

	public Admin getById(long id)
	{
		return super.getById(Admin.class,id);
	}
	
	public boolean deleteAdmin(long id) {
		Admin obj = super.getById(Admin.class,id);
		obj.setIsDeleted(1);
		return super.updateObject(obj).getAdminId()>0;
	}

	
}