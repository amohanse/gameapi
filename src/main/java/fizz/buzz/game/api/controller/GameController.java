package fizz.buzz.game.api.controller;

import fizz.buzz.game.api.exception.ControllerExceptionHandler;
import fizz.buzz.game.api.service.GameService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = { "/rest-api" })
public class GameController extends ControllerExceptionHandler {
	
	@Autowired
	private GameService gameService;

	/**
	 * Implementing HTTP GET, the input is key value from query string.
	 * HTTP 200 for a successful response and the body contains JSON data
	 * HTTP 500 for any internal server errors
	 * E.g, http://localhost/rest-api?A=15&B=30
	 */
	@RequestMapping(method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> computeFizzBuzzSequence(@RequestParam Map<String, String> requestParams) {
		String fizzBuzzJson = this.gameService
				.generateJsonFromCollection(this.gameService
						.generateFizzBuzzSequences(requestParams));
		return new ResponseEntity<String>(fizzBuzzJson, HttpStatus.OK);
	}
}