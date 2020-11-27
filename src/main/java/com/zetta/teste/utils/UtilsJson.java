package com.zetta.teste.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UtilsJson {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public static String ObjectToJson(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

}
