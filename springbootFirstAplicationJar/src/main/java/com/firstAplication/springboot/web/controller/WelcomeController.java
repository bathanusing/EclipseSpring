package com.firstAplication.springboot.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.firstAplication.springboot.web.configuration.BasicConfiguration;

@Controller
@SessionAttributes("name")
public class WelcomeController {
	//Injected automatically es decir injeccion de dependencias
//	@Autowired
//	LoginServices services;
	
	// Para traer valores desde el property
	@Value("${welcome.message}")
	private String mensajeBienvenida;

	// Excelente forma para guardar datos de configuracion de forma organizada (1)
	@Autowired
	private BasicConfiguration configuration;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showWelcomePage(ModelMap model) {
		model.put("name", getLoggedInUserName());
		return "welcome";
	}
	
	private String getLoggedInUserName() {
		Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		return principal.toString();
	}
	
	// Excelente forma para guardar datos de configuracion de forma organizada (2)
	@RequestMapping(value = "/dynamic-configuration", method = RequestMethod.GET)
	@ResponseBody
	public Map dynamicConfiguration() {
		// Not the best practice to use a map to store differnt types!
		Map map = new HashMap();
		map.put("message", configuration.getMessage());
		map.put("number", configuration.getNumber());
		map.put("key", configuration.isValue());
		return map;
	}

//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public String showWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap model) {
//		boolean isValidUser = services.validatePassword(name, password);
//		if(!isValidUser) {
//			model.put("error", "El password es incorrecto");
//			return "login";
//		}
//		model.put("name", name);
//		return "welcome";	
//	}
}
