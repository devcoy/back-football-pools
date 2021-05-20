package com.devcoy.football.pools.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HttpResponse {

	static Map<String, Object> httpResponse = new HashMap<String, Object>();

	public static ResponseEntity<?> buildHttpResponse(TypeStatus typeStatus, Object data) {

		switch (typeStatus) {

		case CREATED:
			httpResponse.put("status", Status.buidlHtppStatus(typeStatus));
			httpResponse.put("data", data);
			return ResponseEntity.status(HttpStatus.CREATED).body(httpResponse);

		case READED:
			httpResponse.put("status", Status.buidlHtppStatus(typeStatus));
			httpResponse.put("data", data);
			return ResponseEntity.status(HttpStatus.OK).body(httpResponse);

		case UPDATED:
			httpResponse.put("status", Status.buidlHtppStatus(typeStatus));
			httpResponse.put("data", data);
			return ResponseEntity.status(HttpStatus.OK).body(httpResponse);

		case DELETED:
			httpResponse.put("status", Status.buidlHtppStatus(typeStatus));
			httpResponse.put("data", data);
			return ResponseEntity.status(HttpStatus.OK).body(httpResponse);

		case OK:
			httpResponse.put("status", Status.buidlHtppStatus(typeStatus));
			httpResponse.put("data", data);
			return ResponseEntity.status(HttpStatus.OK).body(httpResponse);

		case ACCEPTED:
			httpResponse.put("status", Status.buidlHtppStatus(typeStatus));
			httpResponse.put("data", data);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(httpResponse);

		case NO_CONTENT:
			httpResponse.put("status", Status.buidlHtppStatus(typeStatus));
			httpResponse.put("data", data);
			return ResponseEntity.status(HttpStatus.OK).body(httpResponse);

		case NOT_FOUND:
			httpResponse.put("status", Status.buidlHtppStatus(typeStatus));
			httpResponse.put("data", data);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(httpResponse);

		default:
			return null;
		}
	}
}
