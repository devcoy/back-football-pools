package com.devcoy.football.pools.response;

import java.util.HashMap;
import java.util.Map;

public class Status implements HttpMessage {

	static Map<String, Object> httpStatus = new HashMap<String, Object>();

	public static Object buidlHtppStatus(TypeStatus typeStatus) {

		switch (typeStatus) {

		case CREATED:
			httpStatus.put("ok", true);
			httpStatus.put("status", typeStatus);
			httpStatus.put("code", 201);
			httpStatus.put("message", HttpMessage.CREATED);
			return httpStatus;

		case READED:
			httpStatus.put("ok", true);
			httpStatus.put("status", typeStatus);
			httpStatus.put("code", 200);
			httpStatus.put("message", HttpMessage.READED);
			return httpStatus;

		case UPDATED:
			httpStatus.put("ok", true);
			httpStatus.put("status", typeStatus);
			httpStatus.put("code", 200);
			httpStatus.put("message", HttpMessage.UPDATED);
			return httpStatus;

		case DELETED:
			httpStatus.put("ok", true);
			httpStatus.put("status", typeStatus);
			httpStatus.put("code", 200);
			httpStatus.put("message", HttpMessage.DELETED);
			return httpStatus;
			
		case FILE_UPLOADED:
			httpStatus.put("ok", true);
			httpStatus.put("status", typeStatus);
			httpStatus.put("code", 201);
			httpStatus.put("message", HttpMessage.FILE_UPLOADED);
			return httpStatus;

		case OK:

			httpStatus.put("ok", true);
			httpStatus.put("status", typeStatus);
			httpStatus.put("code", 200);
			httpStatus.put("message", HttpMessage.OK);
			return httpStatus;

		case ACCEPTED:
			httpStatus.put("ok", true);
			httpStatus.put("status", typeStatus);
			httpStatus.put("code", 202);
			httpStatus.put("message", HttpMessage.ACCEPTED);
			return httpStatus;

		case NO_CONTENT:

			httpStatus.put("ok", false);
			httpStatus.put("status", typeStatus);
			httpStatus.put("code", 204);
			httpStatus.put("message", HttpMessage.NO_CONTENT);
			return httpStatus;

		case NOT_FOUND:

			httpStatus.put("ok", false);
			httpStatus.put("status", typeStatus);
			httpStatus.put("code", 404);
			httpStatus.put("message", HttpMessage.NOT_FOUND);
			return httpStatus;

		default:
			break;
		}
		return null;

	}

}
