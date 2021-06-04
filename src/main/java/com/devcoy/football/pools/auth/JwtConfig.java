package com.devcoy.football.pools.auth;

public class JwtConfig {

	// Al ser static se puede acceder directo, ya que es un atributo de la clase y no del objeto
	public static final Integer JWT_EXPIRATION_TIME = 3600;
	public static final Integer JWT_REFRESH_TIME = 3600;
	public static final String SECRET_KEY_STRING = "0$dq=bLJpgCjXJkE/yXOfSo4Kwq44PQ8q.alMm$JV0ikC";

}
