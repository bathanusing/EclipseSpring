package com.firstAplication.springboot.web.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByRole(String role);

}
