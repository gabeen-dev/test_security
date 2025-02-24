package com.sparta.test_security.dto;


import com.sparta.test_security.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// Spring Security의 UserDetails 인터페이스를 구현한 사용자 정보 클래스
public class CustomUserDetails implements UserDetails {

	// 사용자 정보를 담고 있는 엔티티 (DB에서 가져온 사용자 정보)
	private UserEntity userEntity;

	// 생성자를 통해 UserEntity 객체를 전달받음
	public CustomUserDetails(UserEntity userData) {
		this.userEntity = userData;
	}

	/**
	 * 사용자에게 부여된 권한(ROLE)을 반환하는 메서드
	 * Spring Security에서는 사용자의 권한을 Collection<GrantedAuthority> 형태로 관리함.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// GrantedAuthority 객체를 저장할 컬렉션 생성
		Collection<GrantedAuthority> collection = new ArrayList<>();

		// 사용자 권한(Role)을 가져와 GrantedAuthority로 변환 후 컬렉션에 추가
		collection.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return userEntity.getRole(); // 예: "ROLE_USER", "ROLE_ADMIN" 등의 권한 반환
			}
		});

		return collection; // 사용자의 권한 목록 반환
	}

	/**
	 * 사용자의 비밀번호를 반환하는 메서드
	 * Spring Security가 인증할 때 사용함.
	 */
	@Override
	public String getPassword() {
		return userEntity.getPassword();
	}

	/**
	 * 사용자의 아이디(username)를 반환하는 메서드
	 * Spring Security에서 사용자 식별자로 사용됨.
	 */
	@Override
	public String getUsername() {
		return userEntity.getUsername();
	}

	/**
	 * 계정의 만료 여부를 반환하는 메서드
	 * true -> 계정이 만료되지 않음 (사용 가능)
	 * false -> 계정이 만료됨 (로그인 불가능)
	 */
	@Override
	public boolean isAccountNonExpired() {
		return UserDetails.super.isAccountNonExpired(); // 기본값 true 반환 (만료되지 않음)
	}

	/**
	 * 계정의 잠김 여부를 반환하는 메서드
	 * true -> 계정이 잠기지 않음 (사용 가능)
	 * false -> 계정이 잠김 (로그인 불가능)
	 */
	@Override
	public boolean isAccountNonLocked() {
		return UserDetails.super.isAccountNonLocked(); // 기본값 true 반환 (잠기지 않음)
	}

	/**
	 * 비밀번호(자격 증명)의 만료 여부를 반환하는 메서드
	 * true -> 비밀번호가 만료되지 않음 (사용 가능)
	 * false -> 비밀번호가 만료됨 (로그인 불가능)
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return UserDetails.super.isCredentialsNonExpired(); // 기본값 true 반환 (만료되지 않음)
	}

	/**
	 * 계정 활성화 여부를 반환하는 메서드
	 * true -> 계정이 활성화됨 (사용 가능)
	 * false -> 계정이 비활성화됨 (로그인 불가능)
	 */
	@Override
	public boolean isEnabled() {
		return UserDetails.super.isEnabled(); // 기본값 true 반환 (활성화됨)
	}
}
