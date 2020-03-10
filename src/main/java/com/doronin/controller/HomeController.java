package com.doronin.controller;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class HomeController 
{
	private static final Logger LOGGER = LogManager.getLogger(HomeController.class);

	@GetMapping("/home")
	public String homeInit(Locale locale, Model model) {
		LOGGER.info(model.toString());
		return "home";
	}
}
