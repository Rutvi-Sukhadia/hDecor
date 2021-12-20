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
import com.hdecor.model.Material;
import com.hdecor.service.MaterialService;

@Controller
@RequestMapping("/material")
@SessionAttributes("admin")
public class MaterialController {

	@Autowired
	MaterialService materialservice;

	@Autowired
	HttpSession session;

	@RequestMapping("/materiallist")
	public ModelAndView getAllMaterials() {
		ModelAndView model = new ModelAndView();
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			List<Material> list = materialservice.getAll();
			model.setViewName("materialList");
			model.addObject("listmaterial", list);
			model.addObject("material", new Material());
			model.addObject("edit", false);
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("errorMsg", e.getMessage());
			model.setViewName("errorPage");
		}
		return model;
	}

	@RequestMapping("/saveMaterial")
	public String saveMaterial(@ModelAttribute("material") Material material, Model model) {
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			long result1 = materialservice.addUpdateMaterial(material);
			if(result1<=0)
				model.addAttribute("errorMsg", "It is already exists or something else went wrong..Try again!");
			return "redirect:/material/materiallist";
		} 
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
	}

	@RequestMapping("/deleteMaterial/{id}")
	public String deleteMaterial(@PathVariable long id, Model model) {
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			boolean result = materialservice.deleteMaterial(id);
			if(result==false)
				model.addAttribute("errorMsg", "something went wrong..Try again!");
			return "redirect:/material/materiallist";
		} 
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
	}

	@RequestMapping("/editMaterial/{id}")
	public ModelAndView editMaterial(@PathVariable long id) {
		ModelAndView model = new ModelAndView();
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			Material material = materialservice.getById(id);
			List<Material> list = materialservice.getAll();
			model.setViewName("materialList");
			model.addObject("listmaterial", list);
			model.addObject("material", material);
			model.addObject("edit", true);
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("errorMsg", e.getMessage());
			model.setViewName("errorPage");
		}
		return model;
	}
}
