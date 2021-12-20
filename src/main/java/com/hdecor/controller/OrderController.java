package com.hdecor.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hdecor.dao.OrderDao;
import com.hdecor.model.Admin;
import com.hdecor.model.Order;
import com.hdecor.service.OrderService;


@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	OrderDao orderDao;

	@Autowired
	HttpSession session;
	
	@RequestMapping("/orderList")
	public String orderlist(Model model) {
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			List<Order> list = orderService.getAll();
			model.addAttribute("orderList", list);
			return "orderlist";
		} 
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
	}

}