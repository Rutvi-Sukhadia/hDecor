package com.hdecor.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hdecor.dao.ReviewDao;
import com.hdecor.model.Review;
import com.hdecor.service.ReviewService;


@Controller
@RequestMapping("/review")

public class ReviewController {

	@Autowired
	ReviewService reviewService;

	@Autowired
	ReviewDao reviewDao;

	@Autowired
	HttpSession session;

	@RequestMapping("/reviewList")
	public String reviewlist(Model model) {
		List<Review> list = reviewService.getAll();
		model.addAttribute("reviewList", list);
		return "reviewlist";
	}

}