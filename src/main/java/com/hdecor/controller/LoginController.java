package com.hdecor.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hdecor.model.Admin;
import com.hdecor.service.LoginService;

@Controller
@RequestMapping("/admin")
@SessionAttributes("admin")
public class LoginController {

	@Autowired
	LoginService loginService;

	@Autowired
	HttpSession session;

	@RequestMapping("/login")
	public String loginPage() {
		return "adminlogin";
	}

	@RequestMapping("/loggingIn")
	public String login(@ModelAttribute("log") Admin admin, Model model) {
		Admin newAdmin;
		try {
			newAdmin = loginService.login(admin);
			if (newAdmin == null) {
				model.addAttribute("errorMsg", "Wrong Username or Password...!!!");
				return "redirect:/admin/login";
			}

			model.addAttribute("admin", newAdmin);
			session.setMaxInactiveInterval(600);
			return "redirect:/admin/home";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}

	}

	@RequestMapping("/logout")
	public String logout(Model model) {
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			session.invalidate();
			return "redirect:/admin/login";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}

	}

	@RequestMapping("/changePassword")
	public String change(Model model) {
		model.addAttribute("cpass", new Admin());
		model.addAttribute("changePassword", true);
		return "changepassword";
	}

	@RequestMapping("/change")
	public String changePass(@ModelAttribute("cpass") Admin admin, Model model) {
		int result;
		try {
			result = loginService.changePass(admin);
			if (result != 0) {
				if (result == 1)
					model.addAttribute("errorMsg", "Old Password does not match...!!!");
				else if (result == 2)
					model.addAttribute("errorMsg", "New Password and confirm password does not match...!!!");
				else if (result == 3)
					model.addAttribute("errorMsg", "Old Password and new passwords are same...!!!");
				else
					model.addAttribute("errorMsg", "Something went wrong..Try again!!!");
				return "redirect:/admin/changePassword";
			}
			Admin ad = (Admin) session.getAttribute("admin");
			model.addAttribute("admin", ad);
			return "redirect:/admin/home";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}

	}

}