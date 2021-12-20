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
import com.hdecor.model.City;
import com.hdecor.model.State;
import com.hdecor.service.CityService;
import com.hdecor.service.StateService;

@Controller
@RequestMapping("/city")
@SessionAttributes("admin")
public class CityController {

	@Autowired
	CityService cityservice;

	@Autowired
	StateService stateservice;
	
	@Autowired
	HttpSession session;

	@RequestMapping("/cityList")
	public ModelAndView getAllCities() {
		ModelAndView model = new ModelAndView();
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");List<City> list = cityservice.getAll();
			City city = new City();
			List<State> stateList = stateservice.getAll();
			model.addObject("stateList", stateList);
			model.addObject("city", city);
			model.addObject("edit", false);
			model.addObject("listcity", list);
			model.setViewName("citylist");
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("errorMsg", e.getMessage());
			model.setViewName("errorPage");
		}
		return model;
	}

	@RequestMapping("/saveCity")
	public String saveCity(@ModelAttribute("city") City city, Model model) {
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			long result1 = cityservice.addUpdateCity(city);
			if(result1<=0)
				model.addAttribute("errorMsg", "It is already exists or something else went wrong..Try again!");
			return "redirect:/city/cityList";
		} 
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
	}

	@RequestMapping("/deleteCity/{id}")
	public String deleteCity(@PathVariable long id, Model model) {
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			boolean result = cityservice.deleteCity(id);			
			if(result==false)
				model.addAttribute("errorMsg", "something went wrong..Try again!");
			return "redirect:/city/cityList";
		} 
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
	}

	@RequestMapping("/editCity/{id}")
	public ModelAndView editCity(@PathVariable long id) {
		ModelAndView model = new ModelAndView();
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");List<City> list = cityservice.getAll();
			City city = cityservice.getById(id);
			List<State> stateList = stateservice.getAll();
			model.addObject("stateList", stateList);
			model.addObject("city", city);
			model.addObject("edit", true);
			model.addObject("listcity", list);
			model.setViewName("citylist");
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("errorMsg", e.getMessage());
			model.setViewName("errorPage");
		}
		return model;
	}

}
