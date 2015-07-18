package fizz.buzz.game.api.controller;

import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fizz.buzz.game.api.Configuration;
import fizz.buzz.game.api.exception.GameException;
import fizz.buzz.game.api.service.GameService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Configuration.class)
public class GameControllerTest {

	private MockMvc mockMvc;
	
	@InjectMocks
	private GameController gameController;
	
	@Mock
	private GameService gameService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
	}
	
	@Test
	public void testExceptionFromGenerateJsonFromCollection() throws Exception {
		Map<String, List<String>> mockData = anyMap();
		when(gameService.generateJsonFromCollection(mockData)).thenThrow(new GameException("Runtime exception raised by mock"));
		mockMvc.perform(get("/rest-api").param("A", "--")).andExpect(status().isInternalServerError());
	}
	
}
