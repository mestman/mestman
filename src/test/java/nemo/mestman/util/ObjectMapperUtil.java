package nemo.mestman.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ObjectMapperUtil {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static <T> String serialize(T obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("직렬화 실패", e);
		}
	}

	public static <T> T deserialize(String json, Class<T> returnType) {
		try {
			return objectMapper.readValue(json, returnType);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("역직렬화 실패", e);
		}
	}
}
