package com.demo.myx.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.myx.security.model.User;
import com.demo.myx.security.repository.UserRepository;

@Service
@Transactional
public class SignupService {

	@Autowired
	UserRepository userRepository;

	public User addUser(User user) {
		user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.getPassword()));

		return userRepository.save(user);
	}
}
