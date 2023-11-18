package com.cipitech.samples.spring.blog.jdbc.web.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.nio.file.Files;

@Slf4j
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
	public String handleFileUpload(@RequestParam("myFile") MultipartFile file, RedirectAttributes redirectAttributes)
	{
		if (!file.isEmpty())
		{
			String name = uploadLocation + "/" + file.getOriginalFilename();
			try
			{
				File uploadingDir = new File(uploadLocation);
				if (!uploadingDir.exists())
				{
					uploadingDir.mkdirs();
				}

				Files.write(new File(name).toPath(), file.getBytes());

				redirectAttributes.addFlashAttribute("msg", "File " + name + " uploaded successfully");
			}
			catch (Exception e)
			{
				log.error("Failed to upload file", e);

				redirectAttributes.addFlashAttribute("msg", "Failed to upload file" + name + ". Cause: " + e.getMessage());
			}
		}
		return "redirect:/fileUpload";
	}
}
