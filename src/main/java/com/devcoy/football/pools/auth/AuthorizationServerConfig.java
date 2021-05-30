package com.devcoy.football.pools.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	@Qualifier("authenticationManager") // para estár más seguro le indicamos el nombre del @Bean
	private AuthenticationManager authenticationManager;

	/**
	 * Configurar permisos de nuestros endpoints (autenticación)
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {

		security.tokenKeyAccess("permitAll()") // enpoint para generar token
				.checkTokenAccess("isAuthenticated()"); // endpoint para válidar y refrescar el token

	}

	/**
	 * Permite configurar nuestro clientes (front) que usarán el Backend OAuth2 a
	 * parte de autenticación por usuario, permite dafinir la apps que lo podrán
	 * usar (front)
	 * 
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		/**
		 * Configuramos como se autenticaran los usuario, existen 3 formas: 1. Cliente y
		 * contraseña (esta estamos usando) 2. Obtener token por redireccionamiento 3.
		 * Solo con cliente
		 */
		clients.inMemory().withClient("angularapp") // cleinte user
				.secret(passwordEncoder.encode("12345678")) // cleinte password
				.scopes("read", "write") // permisos que tendrá el cliente en el backend
				.authorizedGrantTypes("password", "refresh_token") // auth de usuario por contraseña
				.accessTokenValiditySeconds(3600) // Caducidad de token
				.refreshTokenValiditySeconds(3600); // Cada cuanto se va la refrescar
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		// 1. Registramos el authenticationManager
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStorage())
				// se encargar de manejar datos del user (name, email, id, etc), se recomienda
				// que sea información no sensible
				.accessTokenConverter(accessTokenConverter());
	}

	@Bean
	public JwtTokenStore tokenStorage() {
		return new JwtTokenStore(accessTokenConverter());
	}

	/**
	 * Código y decodigfica la data del User
	 * 
	 */
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {

		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		return jwtAccessTokenConverter;
	}
}
