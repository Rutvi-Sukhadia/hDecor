package com.hdecor.daoimpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hdecor.dao.UserDao;
import com.hdecor.model.User;

@Repository("userdao")
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

	public long addUser(User user) {
		user.setIsActive(1);
		user.setIsDeleted(0);
		return super.saveUpdateObject(user).getUserId();
	}

	public User getById(long id) {
		return super.getById(User.class, id);
	}

	public List<User> getAll() {
		return super.getByQuery("FROM User u WHERE u.isDeleted=0");
	}

	public boolean deleteUser(long id) {
		User obj = super.getById(User.class, id);
		obj.setIsDeleted(1);
		return super.updateObject(obj).getUserId() > 0;
	}

	public int emailCheck(User user) {
		String query = "FROM User u where u.emailId='" + user.getEmailId() + "'  and u.isDeleted=0";
		List<User> list = super.getByQuery(query);
		return list.size();
	}

}
