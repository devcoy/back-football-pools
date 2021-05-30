package com.devcoy.football.pools.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.devcoy.football.pools.model.User;
import com.devcoy.football.pools.service.UserService;

@Component
public class JwtInfo implements TokenEnhancer {

	@Autowired
	UserService userService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		User user = this.userService.findByEmail(authentication.getName());
		Map<String, Object> jwtInfo = new HashMap<String, Object>();

		jwtInfo.put("fullname", user.getFullname());
		jwtInfo.put("username", user.getUsername());

		// Agregamos la nueva info
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(jwtInfo);

		return accessToken;
	}

}
