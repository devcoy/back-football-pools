package com.devcoy.football.pools.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	/**
	 * Reglas de seguridad de los endpoints del Backend(Rest controllers)
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {

		// Definimos todas las rutas que será públicas, es decir, que no requieren auth
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/club").permitAll()
				// cualquier otra ruta y método http necesitará autenticación
				.anyRequest().authenticated();
	}

}
