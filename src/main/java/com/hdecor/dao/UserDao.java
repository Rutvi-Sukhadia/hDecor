package com.hdecor.dao;

import java.util.List;

import com.hdecor.model.User;

public interface UserDao extends GenericDAO<User> {

	public long addUser(User user);

	public User getById(long id);

	public List<User> getAll();

	public boolean deleteUser(long id);

	public int emailCheck(User user);

}
