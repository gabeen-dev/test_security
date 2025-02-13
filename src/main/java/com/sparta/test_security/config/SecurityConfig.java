package com.sparta.test_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
			// HTTP 요청에 대한 접근 권한을 설정
			.authorizeHttpRequests((auth) -> auth
				// 루트("/") 및 "/login" 경로는 모든 사용자(비인증 사용자 포함)에게 허용
				.requestMatchers("/", "/login").permitAll()

				// "/admin" 경로는 "ADMIN" 역할을 가진 사용자만 접근 가능
				.requestMatchers("/admin").hasRole("ADMIN")

				// "/my/**" 경로는 "ADMIN" 또는 "USER" 역할을 가진 사용자만 접근 가능
				.requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")

				// 위에서 설정한 요청 경로 외의 모든 요청은 인증된 사용자만 접근 가능
				.anyRequest().authenticated()
			);

		return http.build();
	}
}