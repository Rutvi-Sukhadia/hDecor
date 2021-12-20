package com.hdecor.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hdecor.model.Review;
import com.hdecor.model.User;
import com.hdecor.dao.ProductDao;
import com.hdecor.model.Admin;
import com.hdecor.model.Product;
import com.hdecor.service.ProductService;
import com.hdecor.service.ReviewService;
import com.hdecor.service.UserService;

@Controller
@RequestMapping("/product")

public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	ReviewService reviewService;

	@Autowired
	ProductDao productDao;
	
	@Autowired
	UserService userservice;

	@Autowired
	HttpSession session;

	@RequestMapping("/productList")
	public String productlist(Model model) {
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			List<Product> list = productService.getAll();
			model.addAttribute("productList", list);
			model.addAttribute("heading", "All registered products");
			return "productlist";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
	}

	@RequestMapping("/viewProduct/{id}")
	public String viewProduct(@PathVariable long id, Model model) {
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			Product product = productService.getAllDetails(id);
			model.addAttribute("product", product);
			List<Review> reviewList = reviewService.getReviewsByProductId(product.getProductId());
			model.addAttribute("reviewList", reviewList);
			return "productsinglepage";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
	}

	@RequestMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable("id") long id, Model model) {
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			int result1 = productService.deleteProduct(id);
			if (result1 == 0)
				model.addAttribute("errorMsg", "something went wrong..Try again!");
			return "redirect:/product/productList";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
	}

	@RequestMapping("/usersProducts/{id}")
	public String getProductsByUserId(@PathVariable("id") long id, Model model) {
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			User user = userservice.getById(id);
			if(user==null)
				throw new Exception("Something went wrong!");
			List<Product> list = productService.getProductsByUserId(user.getUserId());
			model.addAttribute("productList", list);
			model.addAttribute("heading", "All registered products of "+ user.getFname() +" " +user.getLname());
			return "productlist";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
	}

}