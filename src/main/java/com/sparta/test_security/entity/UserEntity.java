package com.sparta.test_security.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	//가입 불가 문자 정규식 처리
	//아이디, 비밀번호에 대한 정규식 처리
	//아이디의 자리수
	//아이디의 특수문자 포함 불가
	//admin과 같은 아이디 사용 불가
	//비밀번호 자리수
	//비밀번호 특수문자 포함 필수
	@Column(unique = true, nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	private String role;
}
