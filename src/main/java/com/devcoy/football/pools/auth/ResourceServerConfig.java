package com.devcoy.football.pools.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	/**
	 * Reglas de seguridad de los endpoints del Backend(Rest controllers)
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {

		// Definimos todas las rutas que será públicas, es decir, que no requieren auth
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/club", "/api/championship/**").permitAll()
				.antMatchers(HttpMethod.DELETE, "/api/championship/**").permitAll()
				// cualquier otra ruta y método http necesitará autenticación
				.anyRequest().authenticated()
				// CORS config
				.and().cors().configurationSource(corsConfigurationSource());
	}

	// CORS config
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();

		// Dominios permitidos
		corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:4200"));

		// Métodos permitidos
		corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS"));

		corsConfig.setAllowCredentials(true);

		// Definimos todas las cabeceras que vamos a enviar desde el Front
		corsConfig.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));

		// Registrar el configCors para todas los endpoints del backend
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);

		return source;
	}

	/**
	 * Registrar un filtro en la prioridad más alta de Spring Secure para que se
	 * aplique a todo
	 */
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {

		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(
				new CorsFilter(corsConfigurationSource()));

		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

		return bean;
	}

}
