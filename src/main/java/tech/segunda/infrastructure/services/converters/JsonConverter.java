package tech.segunda.infrastructure.services.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JsonConverter {

    public Optional<String> toJsonString(Object object) {

		if (object == null) {
			return Optional.empty();
		} else {

			ObjectMapper mapper = new ObjectMapper();
			// Registering module to make it work with "Optional" fields.
			mapper.registerModule(new Jdk8Module());

			try {
				return Optional.ofNullable(mapper.writeValueAsString(object));
			} catch (JsonProcessingException e) {
				return Optional.empty();
			}

		}

    }

}
