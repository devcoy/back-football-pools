package com.devcoy.football.pools.repo;

import org.springframework.data.repository.CrudRepository;

import com.devcoy.football.pools.model.User;

public interface UserRepo extends CrudRepository<User, Long> {

	// El login se hará con base al correo electrónico
	public User findByEmail(String email);

}
