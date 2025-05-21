package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/")
public class MainController {
	
	@GetMapping(path="/client")
	public String getClientPage(){
		return "client";
	}
	
	@GetMapping(path="/backups")
	public String getBackupsPage() {
		return "server";
	}
}
