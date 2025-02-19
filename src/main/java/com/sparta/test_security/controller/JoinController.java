package com.sparta.test_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {

	@GetMapping("/join")
	public String joinP() {

		return "join";
	}


	@PostMapping("/joinProc")
	public String joinProcess() {

		return "redirect:/login";
	}
}
