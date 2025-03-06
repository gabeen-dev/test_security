package com.sparta.test_security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Iterator;

@Controller
public class MainController {

	@GetMapping("/")
	public String mainP(Model model) {

		// 서비스 단으로 옮기기
		// 현재 로그인된 사용자의 ID 가져오기
		String id = SecurityContextHolder.getContext().getAuthentication().getName();

		// 현재 로그인된 사용자의 인증 정보(Authentication) 가져오기
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// 사용자의 권한(ROLE) 정보 가져오기
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();

		// 첫 번째 권한(ROLE) 가져오기 (여러 개의 권한이 있을 경우 첫 번째만 사용)
		GrantedAuthority auth = iter.next();
		String role = auth.getAuthority(); // 권한 이름(String) 추출

//		//Iterator를 사용하여 권한을 가져오는 대신 stream()을 활용
//		String username = authentication.getName(); // 사용자 ID
//		String role = authentication.getAuthorities().stream()
//			.map(GrantedAuthority::getAuthority)
//			.findFirst()
//			.orElse("ROLE_GUEST"); // 기본값 설정 가능

		model.addAttribute("id", id);
		model.addAttribute("role", role);

		return "main"; // main.html 템플릿 반환
	}
}
