package com.devcoy.football.pools.exception;

import java.util.HashMap;
import java.util.Map;

public class Exception implements ExceptionMessage {

	static Map<String, Object> httpStatus = new HashMap<String, Object>();

	public static Object buidlHtppStatus(TypeException typeException) {

		switch (typeException) {

		case VALIDATION:

			httpStatus.put("ok", false);
			httpStatus.put("status", typeException);
			httpStatus.put("code", 400);
			httpStatus.put("message", VALIDATION);
			return httpStatus;

		case DB_EXCEPTION:

			httpStatus.put("ok", false);
			httpStatus.put("status", typeException);
			httpStatus.put("code", 500);
			httpStatus.put("message", DB_EXCEPTION);
			return httpStatus;

		default:
			break;
		}
		return null;

	}

}
