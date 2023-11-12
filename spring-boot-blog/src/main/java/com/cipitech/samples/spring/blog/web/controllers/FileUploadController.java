package com.cipitech.samples.spring.blog.web.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;

@Controller
@RequestMapping("/fileUpload")
public class FileUploadController
{
	@Value("${uploadLocation}")
	private String uploadLocation;

	@GetMapping
	public String root(Model model)
	{
		return "uploadFile";
	}

	@PostMapping("/uploadMyFile")
	public String handleFileUpload(@RequestParam("myFile") MultipartFile file)
	{
		if (!file.isEmpty())
		{
			String name = uploadLocation + "/" + file.getOriginalFilename();
			try
			{
				byte[] bytes = file.getBytes();
				Files.write(new File(name).toPath(), bytes);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return "redirect:/fileUpload";
	}
}
