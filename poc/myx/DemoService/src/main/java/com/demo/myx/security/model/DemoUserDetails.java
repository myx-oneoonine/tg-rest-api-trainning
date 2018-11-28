package com.demo.myx.security.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class DemoUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String password;
	private String username;
	private Collection<? extends GrantedAuthority> authorities;

	public DemoUserDetails(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		setAuthorities(user.getRoles());
	}

	private void setAuthorities(List<UserRole> userRoles) {
		List<GrantedAuthority> authorityList = new ArrayList<>();

		for (UserRole userRole : userRoles) {
			String roleName = userRole.getName().toUpperCase();

			if (!roleName.startsWith("ROLE_")) {
				roleName = "ROLE_" + roleName;
			}

			authorityList.add(new SimpleGrantedAuthority(roleName));
		}

		this.authorities = authorityList;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorities;
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public String getUsername() {

		return username;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

}
