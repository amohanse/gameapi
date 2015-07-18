package fizz.buzz.game.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(glue = {"fizz.buzz.game.cucumber"}, features = {"src/test/resources/fizzbuzzgame.feature"})
public class RunCukesTest {

}
