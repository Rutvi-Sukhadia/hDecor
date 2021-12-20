package com.hdecor.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hdecor.model.Admin;
import com.hdecor.model.Category;
import com.hdecor.service.CategoryService;

@Controller
@RequestMapping("/category")
@SessionAttributes("admin")
public class CategoryController {
		
		@Autowired
		CategoryService categoryservice;
		
		@Autowired
		HttpSession session;
		
		@RequestMapping("/categoryList")
		public ModelAndView getAllCategorys()
		{
			ModelAndView model = new ModelAndView();
			try {
				if ((Admin) session.getAttribute("admin") == null)
					throw new Exception("Admin Session not found.. Please Login");
				List<Category> list = categoryservice.getAll();
				model.setViewName("categoryList");
				model.addObject("listcategory",list);
				model.addObject("category",new  Category());
				model.addObject("edit",false);
			} catch (Exception e) {
				e.printStackTrace();
				model.addObject("errorMsg", e.getMessage());
				model.setViewName("errorPage");
			}
			return model;
		}
		
		@RequestMapping("/saveCategory")
		public String saveCategory(@ModelAttribute("category") Category category,Model model)
		{
			try {
				if ((Admin) session.getAttribute("admin") == null)
					throw new Exception("Admin Session not found.. Please Login");
				long result1=categoryservice.addUpdateCategory(category);
				if(result1<=0)
					model.addAttribute("errorMsg", "It is already exists or something else went wrong..Try again!");
				return "redirect:/category/categoryList";
			} 
			catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMsg", e.getMessage());
				return "errorPage";
			}
			
		}
		
	
		@RequestMapping("/deleteCategory/{id}")
		public String deleteCategory(@PathVariable long id, Model model) {
			try {
				if ((Admin) session.getAttribute("admin") == null)
					throw new Exception("Admin Session not found.. Please Login");
				boolean result=categoryservice.deleteCategory(id);				if(result==false)
					model.addAttribute("errorMsg", "something went wrong..Try again!");
				return "redirect:/category/categoryList";
			} 
			catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMsg", e.getMessage());
				return "errorPage";
			}
		}

		@RequestMapping("/editCategory/{id}")
		public ModelAndView editCategory(@PathVariable long id ) {
			ModelAndView model = new ModelAndView();
			try {
				if ((Admin) session.getAttribute("admin") == null)
					throw new Exception("Admin Session not found.. Please Login");Category category=categoryservice.getById(id);
				List<Category> list = categoryservice.getAll();
				model.setViewName("categoryList");
				model.addObject("listcategory",list);
				model.addObject("category",category);
				model.addObject("edit",true);
			} catch (Exception e) {
				e.printStackTrace();
				model.addObject("errorMsg", e.getMessage());
				model.setViewName("errorPage");
			}	
			return model;
		}
}
