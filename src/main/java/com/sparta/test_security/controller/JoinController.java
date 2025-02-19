package com.sparta.test_security.controller;

import com.sparta.test_security.dto.JoinDTO;
import com.sparta.test_security.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JoinController {

	@Autowired
	private JoinService joinService;// 추후 생성자 주입방식으로 변경 필요

	@GetMapping("/join")
	public String joinP() {

		return "join";
	}


	@PostMapping("/joinProc")
	public String joinProcess(JoinDTO joinDTO) {

		System.out.println(joinDTO.getUsername());

		joinService.joinProcess(joinDTO);

		return "redirect:/login";
	}
}
