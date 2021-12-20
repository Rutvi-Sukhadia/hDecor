package com.hdecor.service;

import com.hdecor.model.Admin;

public interface LoginService {
	
	public Admin login(Admin admin) throws Exception;

	public int changePass(Admin admin) throws Exception;

}

