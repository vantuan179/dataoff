package com.dac.dateoff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dac.dateoff.model.User;
import com.dac.dateoff.security.SecurityService;
import com.dac.dateoff.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return "registration";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		return "login";
	}

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String welcome(Model model) {
		return "home";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm) {
		String pass = userForm.getPassword();
		userService.regisUser(userForm);
		// autologin
		securityService.autologin(userForm.getEmail(), pass);

		return "redirect:/home";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, String error, String logout) {
		return "login";
	}
}
