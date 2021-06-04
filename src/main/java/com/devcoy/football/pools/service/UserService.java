package com.devcoy.football.pools.service;

import com.devcoy.football.pools.model.User;

public interface UserService {
	
	public User findByEmail(String email);

}
