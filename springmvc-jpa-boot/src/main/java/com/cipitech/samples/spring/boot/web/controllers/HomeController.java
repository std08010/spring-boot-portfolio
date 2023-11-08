package com.cipitech.samples.spring.boot.web.controllers;

import com.cipitech.samples.spring.boot.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{
	private final UserRepository userRepo;

	public HomeController(UserRepository userRepo)
	{
		this.userRepo = userRepo;
	}

	@RequestMapping("/")
	public String home(Model model)
	{
		model.addAttribute("users", userRepo.findAll());
		return "index";
	}
}
