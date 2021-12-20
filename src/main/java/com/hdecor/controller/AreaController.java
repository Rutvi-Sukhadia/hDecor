package com.hdecor.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hdecor.model.Admin;
import com.hdecor.model.Area;
import com.hdecor.model.City;
import com.hdecor.model.State;
import com.hdecor.service.AreaService;
import com.hdecor.service.CityService;
import com.hdecor.service.StateService;

@Controller
@RequestMapping("/area")
@SessionAttributes("admin")
public class AreaController {

	@Autowired
	AreaService areaservice;

	@Autowired
	CityService cityservice;

	@Autowired
	StateService stateservice;

	@Autowired
	HttpSession session;

	@RequestMapping("/areaList")
	public ModelAndView getAllAreas() {
		ModelAndView model = new ModelAndView();
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			List<Area> list = areaservice.getAll();
			Area area = new Area();
			List<State> stateList = stateservice.getAll();
			List<City> cityList = cityservice.getAll();
			model.addObject("stateList", stateList);
			model.addObject("cityList", cityList);
			model.addObject("area", area);
			model.addObject("edit", false);
			model.addObject("listarea", list);
			model.setViewName("arealist");
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("errorMsg", e.getMessage());
			model.setViewName("errorPage");
		}
		return model;
	}

	@RequestMapping("/saveArea")
	public String saveArea(@ModelAttribute("area") Area area,Model model) {
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			long result1 = areaservice.addUpdateArea(area);
			if(result1<=0)
				model.addAttribute("errorMsg", "It is already exists or something else went wrong..Try again!");
			return "redirect:/area/areaList";
		} 
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
	}

	@RequestMapping("/deleteArea/{id}")
	public String deleteArea(@PathVariable long id, Model model) {
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			boolean result = areaservice.deleteArea(id);
			if(result==false)
				model.addAttribute("errorMsg", "something went wrong..Try again!");
			return "redirect:/area/areaList";
		} 
		catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", e.getMessage());
			return "errorPage";
		}
	}

	@RequestMapping("/editArea/{id}")
	public ModelAndView editArea(@PathVariable long id) {
		ModelAndView model = new ModelAndView();
		try {
			if ((Admin) session.getAttribute("admin") == null)
				throw new Exception("Admin Session not found.. Please Login");
			List<Area> list = areaservice.getAll();
			Area area = areaservice.getById(id);
			List<State> stateList = stateservice.getAll();
			List<City> cityList = cityservice.getAll();
			model.addObject("stateList", stateList);
			model.addObject("cityList", cityList);
			model.addObject("area", area);
			model.addObject("edit", true);
			model.addObject("listarea", list);
			model.setViewName("arealist");
		} catch (Exception e) {
			e.printStackTrace();
			model.addObject("errorMsg", e.getMessage());
			model.setViewName("errorPage");
		}
		return model;
	}

	@RequestMapping(value = "/getCities/{stateId}", method = RequestMethod.GET)
	public @ResponseBody List<City> getAllCitiesByState(@PathVariable("stateId") long stateId) {
		return cityservice.getAllCitiesByStateId(stateId);
	}

}
