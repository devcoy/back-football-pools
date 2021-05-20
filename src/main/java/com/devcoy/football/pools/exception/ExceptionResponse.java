package com.devcoy.football.pools.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ExceptionResponse {

	static Map<String, Object> exceptionResponse = new HashMap<String, Object>();

	public static ResponseEntity<?> buildHttpResponse(TypeException typeException, Object errors) {

		switch (typeException) {

		case VALIDATION:
			exceptionResponse.put("status", Exception.buidlHtppStatus(typeException));
			exceptionResponse.put("erros", errors);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);

		case DB_EXCEPTION:
			exceptionResponse.put("status", Exception.buidlHtppStatus(typeException));
			exceptionResponse.put("erros", errors);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);

		default:
			return null;
		}
	}

}
