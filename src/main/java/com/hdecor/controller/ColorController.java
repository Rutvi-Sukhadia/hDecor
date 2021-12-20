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
import com.hdecor.model.Color;
import com.hdecor.service.ColorService;

@Controller
@RequestMapping("/color")
@SessionAttributes("admin")
public class ColorController {
		
		@Autowired
		ColorService colorservice;
		
		@Autowired
		HttpSession session;
		
		@RequestMapping("/colorlist")
		public ModelAndView getAllColors() {
			ModelAndView model = new ModelAndView();
			try {
				if ((Admin) session.getAttribute("admin") == null)
					throw new Exception("Admin Session not found.. Please Login");
				List<Color> list = colorservice.getAll();
				model.setViewName("colorList");
				model.addObject("listcolor",list);
				model.addObject("color",new  Color());
				model.addObject("edit",false);
			} catch (Exception e) {
				e.printStackTrace();
				model.addObject("errorMsg", e.getMessage());
				model.setViewName("errorPage");
			}
			return model;
		}
		
		@RequestMapping("/saveColor")
		public String saveColor(@ModelAttribute("color") Color color,Model model) {
			try {
				if ((Admin) session.getAttribute("admin") == null)
					throw new Exception("Admin Session not found.. Please Login");
				long result1=colorservice.addUpdateColor(color);
				if(result1<=0)
					model.addAttribute("errorMsg", "It is already exists or something else went wrong..Try again!");
				return "redirect:/color/colorlist";
			} 
			catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMsg", e.getMessage());
				return "errorPage";
			}
		}
		
		
		@RequestMapping("/deleteColor/{id}")
		public String deleteColor(@PathVariable long id, Model model) {
			try {
				if ((Admin) session.getAttribute("admin") == null)
					throw new Exception("Admin Session not found.. Please Login");
				boolean result=colorservice.deleteColor(id);
				if(result==false)
					model.addAttribute("errorMsg", "something went wrong..Try again!");
				return "redirect:/color/colorlist";
			} 
			catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMsg", e.getMessage());
				return "errorPage";
			}
		}

		@RequestMapping("/editColor/{id}")
		public ModelAndView editColor(@PathVariable long id ) {
			ModelAndView model = new ModelAndView();
			try {
				if ((Admin) session.getAttribute("admin") == null)
					throw new Exception("Admin Session not found.. Please Login");
				Color color=colorservice.getById(id);
				List<Color> list = colorservice.getAll();
				model.setViewName("colorList");
				model.addObject("listcolor",list);
				model.addObject("color",color);
				model.addObject("edit",true);
			} catch (Exception e) {
				e.printStackTrace();
				model.addObject("errorMsg", e.getMessage());
				model.setViewName("errorPage");
			}
			return model;
		}
}
