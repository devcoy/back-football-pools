package com.devcoy.football.pools.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// Hábilitamos @Secure para aplicar seguirdad a nivel controlador
@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userService;

	// Método para encriptar la contraseña usando BCrypt
	@Bean() // con @Bean registramos el Objeto que retorna un el método
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Sobreescribímos para registrar en el Authentication Manager Builder de Spring
	// Security para authenticar
	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userService).passwordEncoder(passwordEncoder());
	}

	/**
	 * Registramos en el Authentication Manager ya que lo utilizaremos en
	 * AuthorizationServerConfig
	 */
	@Bean("authenticationManager")
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}

	/**
	 * Reglas a endpoints de Spring Security
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				// cualquier otra ruta y método http necesitará autenticación
				.anyRequest().authenticated().and()
				// Deshabilitamos CSRF
				.csrf().disable()
				// Deshabilitamos el manejo de sesiones (sin estado)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

}
