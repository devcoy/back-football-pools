package com.devcoy.football.pools.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devcoy.football.pools.model.User;
import com.devcoy.football.pools.repo.UserRepo;
import org.slf4j.Logger;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepo userRepo;

	@Override
	@Transactional(readOnly = true)
	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		/**
		 * Obtenemos el usuario de la DB a través de email, de igual forma podríamos hacerlo por username
		 * 
		 * Pero de lado del front, en el body siempre enviaremos username
		 */
		User user = this.userRepo.findByEmail(email);

		if (user == null) {
			logger.error("Error al iniciar sesión, no existe el usuario " + email + " en el sistema");
			throw new UsernameNotFoundException(
					"Error al iniciar sesión, no existe el usuario " + email + " en el sistema");
		}

		// Obtenemos los Authorities (roles) que tenga asignados el usuario
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				// .peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());

		// Creamos la instancia de User
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				user.getEnabled(), true, true, true, authorities);
	}

}
