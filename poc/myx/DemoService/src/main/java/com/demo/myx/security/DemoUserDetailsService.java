package com.demo.myx.security;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.myx.security.model.DemoUserDetails;
import com.demo.myx.security.model.User;
import com.demo.myx.security.repository.UserRepository;

@Service
public class DemoUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);
		if (Objects.isNull(user)) {
			throw new UsernameNotFoundException("User " + username + " not found");
		}

		return new DemoUserDetails(user);
	}

}
