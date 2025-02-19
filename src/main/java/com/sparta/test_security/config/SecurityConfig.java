package com.sparta.test_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
			.authorizeHttpRequests((auth) -> auth
				// "/"(홈), "/login", "/loginProc", "/join", "/joinProc" 경로는 인증 없이 접근 가능
				.requestMatchers("/", "/login", "/loginProc", "/join", "/joinProc").permitAll()

				// "/admin" 경로는 "ADMIN" 역할(ROLE_ADMIN)을 가진 사용자만 접근 가능
				.requestMatchers("/admin").hasRole("ADMIN")

				// "/my/**" 경로는 "ADMIN" 또는 "USER" 역할을 가진 사용자만 접근 가능
				.requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")

				// 그 외 모든 요청은 인증된 사용자만 접근 가능
				.anyRequest().authenticated()
			);

		http
			.formLogin((auth) -> auth
				// 로그인 페이지를 "/login"으로 설정
				.loginPage("/login")

				// 로그인 처리를 수행할 URL 설정 ("/loginProc"으로 요청이 오면 로그인 처리)
				.loginProcessingUrl("/loginProc")

				// 로그인 페이지 접근은 누구나 가능하도록 설정
				.permitAll()
			);

		http
			// CSRF(Cross-Site Request Forgery) 보호 기능 비활성화
			.csrf((auth) -> auth.disable());

		// 필터 체인을 빌드하여 반환
		return http.build();
	}

}