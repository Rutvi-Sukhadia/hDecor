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

import com.hdecor.model.Subcategory;
import com.hdecor.model.Admin;
import com.hdecor.model.Category;
import com.hdecor.service.SubcategoryService;
import com.hdecor.service.CategoryService;

@Controller
@RequestMapping("/subcategory")
@SessionAttributes("admin")
public class SubcategoryController {

	@Autowired
	SubcategoryService subcategoryservice;

	@Autowired
	CategoryService categoryservice;
	
	@Autowired
	HttpSession session;

	@RequestMapping("/subcategoryList")
	public ModelAndView getAllSubcategories() {
		ModelAndView model = new ModelAndView();
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			List<Subcategory> list = subcategoryservice.getAll();
			Subcategory subcategory = new Subcategory();
			List<Category> categoryList = categoryservice.getAll();
			model.addObject("categoryList", categoryList);
			model.addObject("subcategory", subcategory);
			model.addObject("edit", false);
			model.addObject("listsubcategory", list);
			model.setViewName("subcategorylist");
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("errorMsg", e.getMessage());
			model.setViewName("errorPage");
		}
		return model;
	}

	@RequestMapping("/saveSubcategory")
	public String saveSubcategory(@ModelAttribute("subcategory") Subcategory subcategory, Model model) {
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			long result1 = subcategoryservice.addUpdateSubcategory(subcategory);
			if(result1<=0)
				model.addAttribute("errorMsg", "It is already exists or something else went wrong..Try again!");
			return "redirect:/subcategory/subcategoryList";
		} 
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
	}


	@RequestMapping("/deleteSubcategory/{id}")
	public String deleteSubcategory(@PathVariable long id, Model model) {
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			boolean result = subcategoryservice.deleteSubcategory(id);
			if(result==false)
				model.addAttribute("errorMsg", "something went wrong..Try again!");
			return "redirect:/subcategory/subcategoryList";
		} 
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
	}

	@RequestMapping("/editSubcategory/{id}")
	public ModelAndView editSubcategory(@PathVariable long id) {
		ModelAndView model = new ModelAndView();
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			List<Subcategory> list = subcategoryservice.getAll();
			Subcategory subcategory = subcategoryservice.getById(id);
			List<Category> categoryList = categoryservice.getAll();
			model.addObject("categoryList", categoryList);
			model.addObject("subcategory", subcategory);
			model.addObject("edit", true);
			model.addObject("listsubcategory", list);
			model.setViewName("subcategorylist");
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("errorMsg", e.getMessage());
			model.setViewName("errorPage");
		}
		return model;
	}

}
