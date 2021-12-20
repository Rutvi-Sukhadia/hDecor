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
import com.hdecor.model.State;
import com.hdecor.service.StateService;

@Controller
@RequestMapping("/state")
@SessionAttributes("admin")
public class StateController {
		
		@Autowired
		StateService stateservice;
		
		@Autowired
		HttpSession session;
		
		@RequestMapping("/statelist")
		public ModelAndView getAllStates(){
			ModelAndView model = new ModelAndView();
			try {
				if ((Admin) session.getAttribute("admin") == null)
					throw new Exception("Admin Session not found.. Please Login");
				List<State> list = stateservice.getAll();
				model.setViewName("stateList");
				model.addObject("liststate",list);
				model.addObject("state",new  State());
				model.addObject("edit",false);
			} catch (Exception e) {
				e.printStackTrace();
				model.addObject("errorMsg", e.getMessage());
				model.setViewName("errorPage");
			}
			return model;
		}
		
		@RequestMapping("/saveState")
		public String saveState(@ModelAttribute("state") State state, Model model) {
			try {
				if ((Admin) session.getAttribute("admin") == null)
					throw new Exception("Admin Session not found.. Please Login");
				long result1=stateservice.addUpdateState(state);				if(result1<=0)
					model.addAttribute("errorMsg", "It is already exists or something else went wrong..Try again!");
				return "redirect:/state/statelist";
			} 
			catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMsg", e.getMessage());
				return "errorPage";
			}
		}
	
		@RequestMapping("/deleteState/{id}")
		public String deleteState(@PathVariable long id, Model model) {
			try {
				if ((Admin) session.getAttribute("admin") == null)
					throw new Exception("Admin Session not found.. Please Login");
				boolean result=stateservice.deleteState(id);
				if(result==false)
					model.addAttribute("errorMsg", "something went wrong..Try again!");
				return "redirect:/state/statelist";
			} 
			catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMsg", e.getMessage());
				return "errorPage";
			}
		}

		@RequestMapping("/editState/{id}")
		public ModelAndView editState(@PathVariable long id ) {
			ModelAndView model = new ModelAndView();
			try {
				if ((Admin) session.getAttribute("admin") == null)
					throw new Exception("Admin Session not found.. Please Login");
				State state=stateservice.getById(id);
				List<State> list = stateservice.getAll();
				model.setViewName("stateList");
				model.addObject("liststate",list);
				model.addObject("state",state);
				model.addObject("edit",true);
			} catch (Exception e) {
				e.printStackTrace();
				model.addObject("errorMsg", e.getMessage());
				model.setViewName("errorPage");
			}
			return model;
		}
}
