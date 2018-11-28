package com.demo.myx.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.demo.myx.security.model.DemoUserDetails;
import com.demo.myx.security.model.User;
import com.demo.myx.security.repository.UserRepository;

public class DemoUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		
		User user = userRepository.findByUsername(username);
//	        if(user == null){
//	            throw new UsernameNotFoundException("UserName "+userName+" not found");
//	        }
//	        return new CrmUserDetails(user);

		return new DemoUserDetails(user);
	}

}
