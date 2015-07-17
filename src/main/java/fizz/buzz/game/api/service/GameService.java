package fizz.buzz.game.api.service;

import java.util.List;
import java.util.Map;

public interface GameService {
    public static final String FIZZ = "Fizz";
    public static final String BUZZ = "Buzz";
    public static final String INVALID_INPUT = "The input is invalid";

    public Map<String, List<String>> generateFizzBuzzSequences(Map<String, String> inputData);

    public String generateJsonFromCollection(Map<String, List<String>> fizzBuzzSequence);
}