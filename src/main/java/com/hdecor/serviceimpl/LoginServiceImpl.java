package com.hdecor.serviceimpl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hdecor.dao.AdminDao;
import com.hdecor.model.Admin;
import com.hdecor.service.LoginService;
import com.hdecor.util.PasswordEncode;

@Service("loginservice")
@Transactional
@SessionAttributes("admin")
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	AdminDao admindao;
	
	@Autowired
	HttpSession session;

	public Admin login(Admin admin) throws Exception {
		String password = admin.getPassword();
		admin.setPassword(PasswordEncode.passwordEncode(password));
		return admindao.loginCheck(admin);
	}

	public int changePass(Admin admin) throws Exception{
		Admin ad =(Admin)session.getAttribute("admin");
		Admin u = admindao.getById(Admin.class, ad.getAdminId());
		if(!u.getPassword().equals(PasswordEncode.passwordEncode(admin.getOldPassword()))){
			return 1;
		}else if(!admin.getPassword().equals(admin.getCpassword())) {
			return 2;
		}else if(admin.getOldPassword().equals(admin.getPassword())) {
			return 3;
		}
		u.setPassword(PasswordEncode.passwordEncode(admin.getPassword()));
		Admin newAdmin = admindao.updateObject(u);
		if(newAdmin!=null)
			return 0;
		else
			return 4;
	}
	
	
}
