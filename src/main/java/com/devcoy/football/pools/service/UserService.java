package com.devcoy.football.pools.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devcoy.football.pools.model.User;
import com.devcoy.football.pools.repo.UserRepo;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserRepo userRepo;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// Obtenemos el usuario de la DB a través de email
		User user = this.userRepo.findByEmail(username);

		// Obtenemos los Authorities (roles) que tenga asignados el usuario
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				//.peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());

		// Creamos la instancia de User
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				user.getEnabled(), true, true, true, null);
	}

}
