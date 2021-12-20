package com.hdecor.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hdecor.model.Admin;
import com.hdecor.model.Feedback;

import com.hdecor.service.FeedbackService;

@Controller
@RequestMapping("/feedback")
@SessionAttributes("admin")
public class FeedbackController {

	@Autowired
	FeedbackService feedbackservice;

	@Autowired
	HttpSession session;

	@RequestMapping("/feedbackList")
	public ModelAndView getAllFeedbacks() {
		ModelAndView model = new ModelAndView();
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			List<Feedback> list = feedbackservice.getAll();
			model.setViewName("feedbacklist");
			model.addObject("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("errorMsg", e.getMessage());
			model.setViewName("errorPage");
		}
		return model;
	}

	@RequestMapping("/deleteFeedback/{id}")
	public String deleteFeedback(@PathVariable long id, Model model) {
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			boolean result = feedbackservice.deleteFeedback(id);
			if(result==false)
				model.addAttribute("errorMsg", "something went wrong..Try again!");
			return "redirect:/feedback/feedbackList";
		} 
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
	}

	@RequestMapping("/replyFeedback/{id}")
	public String replyFeedback(@PathVariable long id, ModelMap model) {
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			Feedback feedback = feedbackservice.getFeedback(id);
			model.addAttribute("feedback", feedback);
			return "feedbackreply";
		}
		catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMsg", e.getMessage());
				return "errorPage";
			}
	}

	@RequestMapping("/sendReply")
	public String sendReply(@ModelAttribute("feedback") Feedback feedback, Model model) {
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			int result1 = feedbackservice.sendReply(feedback);
			if(result1==0)
				model.addAttribute("errorMsg", "Could not send email.. Try again later!");	
			return "redirect:/feedback/feedbackList";
		}
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
	}

}
