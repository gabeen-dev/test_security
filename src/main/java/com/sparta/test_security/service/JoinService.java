package com.sparta.test_security.service;

import com.sparta.test_security.dto.JoinDTO;
import com.sparta.test_security.entity.UserEntity;
import com.sparta.test_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

	@Autowired
	private UserRepository userRepository;//생성자 주입방식으로 변경

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public void joinProcess(JoinDTO joinDTO) {

		UserEntity data = new UserEntity();

		//DB에 동일한 Username 있는 지 검증 필요
		data.setUsername(joinDTO.getUsername());
		data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
		data.setRole("ROLE_USER");

		userRepository.save(data);
	}
}
