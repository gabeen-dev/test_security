package com.sparta.test_security.repository;

import com.sparta.test_security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	boolean existsByUsername(String username);

	UserEntity findByUsername(String username);
}
