package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Mapper {

	public <T> T readJson(String json, Class<T> type) {
		try {
			return new ObjectMapper().readValue(json,type);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public String writeToString(Object value) {
		try {
			return new ObjectMapper().writeValueAsString(value);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

}
