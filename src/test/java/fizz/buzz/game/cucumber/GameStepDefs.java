package fizz.buzz.game.cucumber;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fizz.buzz.game.api.Configuration;
import fizz.buzz.game.api.controller.GameController;
import fizz.buzz.game.api.service.GameServiceTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Configuration.class, loader = SpringApplicationContextLoader.class)
public class GameStepDefs {
	
	private String userNumber;
	
	private MockMvc mockMvc;
	
	private ResultActions resultActions;
	
	private String jsonResult;
	
	@Autowired
	private GameController gameController;
	
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
	}

	@Given("^the user has a number and wants to generate fizz buzz series$")
	public void prepareTestData() throws Throwable {
	    userNumber = "15";
	}

	@When("^the number is given to the system$")
	public void doGetGameController() throws Throwable {
		resultActions = mockMvc.perform(get("/rest-api").param("A", userNumber)).andDo(print());
	}

	@Then("^the system successfully generates the fizz buzz series$")
	public void validateResponse() throws Throwable {
	    resultActions.andExpect(status().isOk());
	    resultActions.andExpect(header().string(HttpHeaders.CONTENT_TYPE, is(MediaType.APPLICATION_JSON_VALUE)));
	    
	    jsonResult = resultActions.andReturn().getResponse().getContentAsString();
	    
	    Assert.assertThat(jsonResult, is(notNullValue()));
	    Assert.assertThat(GameServiceTest.deserializeSequenceFromJson(jsonResult).get("A")
	    		, is(notNullValue()));
	    
	    Assert.assertThat(GameServiceTest.deserializeSequenceFromJson(jsonResult).get("A").size()
	    		, is(equalTo(Integer.parseInt(userNumber))));
	}
	
	@Then("^the numbers divisible by (\\d+) are replaced with \"([^\"]*)\"$")
	public void validateFizzAndBuzzInSequence(int divisor, String word) throws Throwable {
		for(int i=1;i<=Integer.parseInt(userNumber);i++) {
			if(i % divisor == 0) {
				Assert.assertThat(GameServiceTest.deserializeSequenceFromJson(jsonResult).get("A").get(i -1 ).contains(word), is(true));
			}
		}
	}

	@Then("^the numbers divisible by (\\d+) and (\\d+) are replaced with \"([^\"]*)\"$")
	public void validateFizzBuzzInSequence(int divisor1, int divisor2, String word) throws Throwable {
		for(int i=1;i<=Integer.parseInt(userNumber);i++) {
			if(i % divisor1 == 0 && i % divisor2 == 0) {
				Assert.assertThat(GameServiceTest.deserializeSequenceFromJson(jsonResult).get("A").get(i -1 ), is(word));
			}
		}
	}
	
}
