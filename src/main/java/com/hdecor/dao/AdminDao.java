package com.hdecor.dao;

import java.util.List;

import com.hdecor.model.Admin;

public interface AdminDao extends GenericDAO<Admin> {

	public List<Admin> getAll();

	public Admin getById(long id);

	public Admin loginCheck(Admin admin);

	int emailCheck(Admin admin);

	public boolean deleteAdmin(long id);

}
