package com.hdecor.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.hdecor.model.Admin;
import com.hdecor.service.AdminService;
import com.hdecor.service.FeedbackService;
import com.hdecor.service.ProductService;
import com.hdecor.service.UserService;

@Controller
@RequestMapping("/admin")
@SessionAttributes("admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@Autowired
	UserService userservice;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	FeedbackService feedbackservice;

	@Autowired
	HttpSession session;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "redirect:/admin/login";
	}
	
	@RequestMapping("/home")
	public String home(Model model) {
		Admin a = (Admin)session.getAttribute("admin");
		try {
			if(a==null) throw new Exception("Admin Session not found.. Please Login");
			adminService.getById(a.getAdminId());
			int users = userservice.getAll().size();
			int products = productService.getAll().size();
			int admins = adminService.getAll().size();
			int feedbacks = feedbackservice.getAll().size();
			model.addAttribute("users", users);
			model.addAttribute("products", products);
			model.addAttribute("feedbacks", feedbacks);
			model.addAttribute("admins", admins);
			return "adminindex2";
		}
		catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
	}

	@RequestMapping("/adminList")
	public String adminList(Model model) {
		try {
			if((Admin)session.getAttribute("admin")==null) throw new Exception("Admin Session not found.. Please Login");
			List<Admin> adminList =adminService.getAll();
			model.addAttribute("listadmin", adminList);
			return "adminlist";
		}
		catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
	}
	
	@RequestMapping("/addAdmin")
	public String adminRegistrationPage(Model model) {
		try{
			if((Admin)session.getAttribute("admin")==null) throw new Exception("Admin Session not found.. Please Login");
			Admin admin = new Admin();
			model.addAttribute("admin1", admin);
			model.addAttribute("edit", false);
			return "addadmin";
		}
		catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}		
	}
	
	@RequestMapping("/saveAdmin")
	public String saveAdmin(@ModelAttribute("admin1") Admin admin1, BindingResult result, Model model,
			@RequestParam("profilePicture") MultipartFile file1) {
		try{
			if((Admin)session.getAttribute("admin")==null) throw new Exception("Admin Session not found.. Please Login");
			long result1 = adminService.saveAdmin(admin1, file1);
			if (result1 == 0) {
				model.addAttribute("errorMsg", "It is already exists or something else went wrong..Try again!");
				return "redirect:/admin/addAdmin";
			}
			return "redirect:/admin/adminList";
		}
		catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
		
	}
	

	@RequestMapping("/deleteAdmin/{id}")
	public String deleteAdmin(@PathVariable long id,Model model){
		try{
			if((Admin)session.getAttribute("admin")==null) throw new Exception("Admin Session not found.. Please Login");
			boolean result=adminService.deleteAdmin(id);
			if(result==false) {
				model.addAttribute("errorMsg", "Something went wrong..Try again!");
			}
			return "redirect:/admin/adminList";
		}
		catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
		
	}
	
	@RequestMapping("/editAdmin/{id}")
	public String editAdmin(@PathVariable("id") long id, Model model)
	{
		try{
			if((Admin)session.getAttribute("admin")==null) throw new Exception("Admin Session not found.. Please Login");
			Admin admin = adminService.getById(id);
			model.addAttribute("admin1", admin);
			model.addAttribute("edit", true);
			return "editadmin";
		}
		catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
		
	}
	
	@RequestMapping("/updateAdmin")
	public String updateAdmin(@ModelAttribute("admin1") Admin admin1, BindingResult result, Model model,
			@RequestParam("profilePicture") MultipartFile file1)
	{
		try{
			if((Admin)session.getAttribute("admin")==null) throw new Exception("Admin Session not found.. Please Login");
			long res=adminService.updateAdmin(admin1,file1);
			if (res == 0) {
				model.addAttribute("errorMsg", "Something went wrong..Try again!");
				return "redirect:/admin/editAdmin/"+admin1.getAdminId();
			}
			return "redirect:/admin/adminList";
		}
		catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
		
	}
	
	@RequestMapping("/editProfile")
	public String editProfile(Model model)
	{
		try{
			Admin a = (Admin)session.getAttribute("admin");
			if(a==null) throw new Exception("Admin Session not found.. Please Login");
			Admin admin = adminService.getById(a.getAdminId());
			model.addAttribute("admin", admin);
			model.addAttribute("edit", true);
			return "editprofile";
		}
		catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
		
	}
	
	@RequestMapping("/updateProfile")
	public String updateProfile(@ModelAttribute("admin") Admin admin, BindingResult result, Model model,
			@RequestParam("profilePicture") MultipartFile file1)
	{
		try{
			if((Admin)session.getAttribute("admin")==null) throw new Exception("Admin Session not found.. Please Login");
			long res=adminService.updateAdmin(admin,file1);
			if (res == 0) {
				model.addAttribute("errorMsg", "Something went wrong..Try again!");
				return "redirect:/admin/editAdmin/"+admin.getAdminId();
			}
			session.setAttribute("admin", admin);
			return "redirect:/admin/home";
		}
		catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
		
	}
	
	
	
}

