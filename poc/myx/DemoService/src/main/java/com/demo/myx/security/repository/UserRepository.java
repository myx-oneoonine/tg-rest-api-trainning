package com.demo.myx.security.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.myx.security.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);
}
