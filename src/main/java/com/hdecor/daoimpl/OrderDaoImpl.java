package com.hdecor.daoimpl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hdecor.dao.OrderDao;
import com.hdecor.model.Order;

@Repository("orderDao")
@SessionAttributes("admin")

public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao {

	@Autowired
	HttpSession session;

	public List<Order> getActive() {
		return (List<Order>) super.getByQuery("FROM Order o");
	}

}
