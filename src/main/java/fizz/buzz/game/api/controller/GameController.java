package fizz.buzz.game.api.controller;

import fizz.buzz.game.api.exception.ControllerExceptionHandler;
import fizz.buzz.game.api.service.GameService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

	@RequestMapping(method = { RequestMethod.GET }, produces = { "application/json" })
	public ResponseEntity<String> computeFizzBuzzSequence(@RequestParam Map<String, String> requestParams) {
		String fizzBuzzJson = this.gameService
				.generateJsonFromCollection(this.gameService
						.generateFizzBuzzSequences(requestParams));
		return new ResponseEntity<String>(fizzBuzzJson, HttpStatus.OK);
	}
}