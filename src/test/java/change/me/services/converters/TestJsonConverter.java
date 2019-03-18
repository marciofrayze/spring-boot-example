package change.me.services.converters;

import org.junit.jupiter.api.Test;
import change.me.infrastructure.json.converters.JsonConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJsonConverter
{

	private final JsonConverter jsonConverter = new JsonConverter();

	@Test
	public void listOfStringsConversion_shouldGenerateValidJson()
	{
		// Given
		List<String> listOfStrings = new ArrayList<>();
		listOfStrings.add("firstEntry");
		listOfStrings.add("secondEntry");
		listOfStrings.add("thirdEntry");

		// When
		Optional<String> listOfStringAsJson = jsonConverter.toJsonString(listOfStrings);

		// Then
		Optional<String> expectedJsonString = Optional.of("[\"firstEntry\",\"secondEntry\",\"thirdEntry\"]");
		assertEquals(expectedJsonString, listOfStringAsJson);
	}

	@Test
	public void nullConversion_shouldGenerateEmptyOptional()
	{
		// Given
		Object nullObject = null;

		// When
		Optional<String> nullObjectAsJson = jsonConverter.toJsonString(nullObject);

		// Then
		assertEquals(Optional.empty(), nullObjectAsJson);
	}

	@Test
	public void nonSerializableObjectConversion_shouldGenerateEmptyOptional()
	{
		// Given
		NonSerializableClass nonSerializableObject = new NonSerializableClass();

		// When
		Optional<String> objectConvertedToJson = jsonConverter.toJsonString(nonSerializableObject);

		// Then
		assertEquals(Optional.empty(), objectConvertedToJson);
	}

	private static class NonSerializableClass {}

}