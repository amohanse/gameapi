package fizz.buzz.game.api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fizz.buzz.game.api.exception.GameException;

@Service
public class GameServiceImpl implements GameService {
	@Override
	public Map<String, List<String>> generateFizzBuzzSequences(
			Map<String, String> requestParams) {
		HashMap<String, List<String>> fizzBuzzSequence = new HashMap<String, List<String>>();
		requestParams.forEach((k, v) -> {
			fizzBuzzSequence.put(k, this.getSequence(v));
		});
		return fizzBuzzSequence;
	}

	private List<String> getSequence(String value) {
		if (value == null || this.tryParse(value) == null) {
			return Arrays.asList("The input is invalid");
		}
		ArrayList<String> sequences = new ArrayList<String>();
		for (int i = 1; i <= this.tryParse(value); ++i) {
			if (i % 5 == 0 && i % 3 == 0) {
				sequences.add(String.format("%s %s", "Fizz", "Buzz"));
			} else if (i % 5 == 0) {
				sequences.add("Buzz");
			} else if (i % 3 == 0) {
				sequences.add("Fizz");
			} else {
				sequences.add(Integer.toString(i));
			}
		}
		return sequences;
	}

	private Integer tryParse(String value) {
		try {
			return Integer.valueOf(value);
		} catch (NumberFormatException nfe) {
			return null;
		}
	}

	@Override
	public String generateJsonFromCollection(Map<String, List<String>> map) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			throw new GameException("Unable to process the input");
		}
	}
}
