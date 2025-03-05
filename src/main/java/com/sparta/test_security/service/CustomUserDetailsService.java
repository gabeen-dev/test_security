package com.sparta.test_security.service;

import com.sparta.test_security.dto.CustomUserDetails;
import com.sparta.test_security.entity.UserEntity;
import com.sparta.test_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;//생성자 주입 방식으로 변경


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userData = userRepository.findByUsername(username);

		if (userData != null) {
			return new CustomUserDetails(userData);
		}
		return null;
	}
}
