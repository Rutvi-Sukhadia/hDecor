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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.hdecor.model.Admin;
import com.hdecor.model.Area;
import com.hdecor.model.City;
import com.hdecor.model.Product;
import com.hdecor.model.State;
import com.hdecor.model.User;
import com.hdecor.service.AreaService;
import com.hdecor.service.CityService;
import com.hdecor.service.ProductService;
import com.hdecor.service.StateService;
import com.hdecor.service.UserService;

@Controller
@SessionAttributes("admin")
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userservice;

	@Autowired
	HttpSession session;

	@Autowired
	StateService stateservice;

	@Autowired
	CityService cityservice;

	@Autowired
	AreaService areaservice;
	
	@Autowired
	ProductService productService;

	@RequestMapping("/userList")
	public String listUser(Model model) {
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			List<User> userList = userservice.getAll();
			model.addAttribute("listuser", userList);
			return "userlist";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
	}

	@RequestMapping("/addUser")
	public String adminRegistrationPage(Model model) {
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			User user = new User();
			List<State> liststate = stateservice.getAll();
			model.addAttribute("liststate", liststate);
			List<City> listcity = cityservice.getAll();
			model.addAttribute("listcity", listcity);
			List<Area> listarea = areaservice.getAll();
			model.addAttribute("listarea", listarea);
			model.addAttribute("user", user);
			model.addAttribute("edit", false);
			return "adduser";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
	}

	@RequestMapping("/saveUser")
	public String registrationPage(@ModelAttribute("user") User user, BindingResult result, Model model,
			@RequestParam("profileImage") MultipartFile file1) {
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			long result1 = userservice.addUser(user, file1);
			if (result1 == 0) {
				model.addAttribute("errorMsg", "User with given emailId already exists or something else went wrong.. Try again!");
				return "redirect:/user/addUser";
			}
			return "redirect:/user/userList";
		}
		catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
	}

	@RequestMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable long id, Model model) {
		try{
			if((Admin)session.getAttribute("admin")==null) throw new Exception("Admin Session not found.. Please Login");
			List<Product> list = productService.getProductsByUserId(id);
			if(list.size()!=0) {
				model.addAttribute("errorMsg", "Could not delete user as products Listed by the user exists..");
				return "redirect:/user/userList";
			}
			boolean result = userservice.deleteUser(id);			
			if(result==false) {
				model.addAttribute("errorMsg", "Something went wrong..Try again!");
			}
			return "redirect:/user/userList";
		}
		catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
	}

	@RequestMapping("/viewUser/{id}")
	public String viewUser(@PathVariable long id, Model model) {
		try{
			if((Admin)session.getAttribute("admin")==null) throw new Exception("Admin Session not found.. Please Login");
			User user = userservice.getAllDetails(id);
			if(user==null) {
				model.addAttribute("errorMsg", "Something went wrong..Try again!");
				return "redirect:/user/userList";
			}else {
				model.addAttribute("user", user);
			}
			return "userprofile";
		}
		catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
		
	}

}
