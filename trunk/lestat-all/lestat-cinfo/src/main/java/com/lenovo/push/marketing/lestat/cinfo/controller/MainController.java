package com.lenovo.push.marketing.lestat.cinfo.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lenovo.push.marketing.lestat.cinfo.test.datagenerator.ModelGenerator;


/**
 * 
 * @author Hongkai Liu
 *
 */
@Controller
public class MainController {
	
	private static final Logger logger = Logger.getLogger(MainController.class);

	@RequestMapping(value = { "/", "/home**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {
		logger.debug("==============default page==================");
		ModelAndView model = ModelGenerator.getHomeModel();
		
		model.addObject("title", "Spring Security Login Form - Database Authentication");
		model.addObject("message", "This is home page!");
		
		List<String> list1 = new ArrayList<String>();
		list1.add("nihao");
		list1.add("中文");
		model.addObject("list1", list1);
		
		model.setViewName("home");
		return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}
	
	//for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();
		
		//check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);
		
			model.addObject("username", userDetail.getUsername());
			
		}
		
		model.setViewName("403");
		return model;

	}

}