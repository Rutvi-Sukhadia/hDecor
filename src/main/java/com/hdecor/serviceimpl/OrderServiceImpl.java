package com.hdecor.serviceimpl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hdecor.dao.OrderDao;
import com.hdecor.dao.ProductDao;
import com.hdecor.dao.UserDao;
import com.hdecor.model.Order;
import com.hdecor.model.Product;
import com.hdecor.model.User;
import com.hdecor.service.OrderService;

@Service("orderservice")
@Transactional
@SessionAttributes("admin")
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao orderDao;

	@Autowired
	HttpSession session;

	@Autowired
	UserDao userdao;

	@Autowired
	ProductDao productDao;

	public List<Order> getAll() {
		List<Order> orderList = orderDao.getActive();
		for (Order order : orderList) {
			order.setPname(productDao.getById(Product.class, order.getProductId()).getName());
			User u = userdao.getById(order.getUserId());
			order.setTname(u.getFname() + " " + u.getLname());
		}
		return orderList;
	}

}
