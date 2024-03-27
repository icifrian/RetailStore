package es.retail.store.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestUtils {


	  private static final ObjectMapper objectMapper = new ObjectMapper().configure(
	      DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
	      false);

	  public static ObjectMapper getObjectMapper() {
	    return objectMapper;
	  }

	  public static String toJson(String path) {
	    try {
	      return new String(Files.readAllBytes(Paths.get(path)));
	    } catch (IOException e) {
	      log.error("Error reading file with message: {}", e.getMessage());
	      throw new ReadingFileException();
	    }
	  }

	  public static String toJson(Object object) {

	    try {
	      return objectMapper.writeValueAsString(object);
	    } catch (IOException e) {
	      log.error("Error deserializing object with message: {}", e.getMessage());
	      throw new DeserializingException();
	    }
	  }

	  public static <T> T toObject(String path, Class<T> clazz) {

	    try {
	      return objectMapper.readValue(ResourceUtils.getURL(path).openStream(), clazz);
	    } catch (IOException e) {
	      log.error("Error deserializing object with message: {}", e.getMessage());
	      throw new DeserializingException();
	    }
	  }

	  public static <T> T toObject(String path, TypeReference<T> clazz) {
	    try {
	      return objectMapper.readValue(ResourceUtils.getURL(path).openStream(), clazz);
	    } catch (IOException e) {
	      log.error("Error deserializing object with message: {}", e.getMessage());
	      throw new DeserializingException();
	    }
	  }
}
