
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith
import io.cucumber.junit.Cucumber
import org.junit.Test
import kotlin.test.assertEquals


@RunWith(Cucumber::class)
@CucumberOptions(
      features = [
            "C:\\Users\\KenV1\\IdeaProjects\\GameWithCucumber\\src\\test\\resources\\cucumber\\game.feature"]
)
class RunKukesTest {
      @Test
      fun testSum() {
      }
}


/*
@RunWith(javaClass<Cucumber>())]
CucumberOptions(
format = array("pretty"),
features = array("classpath:features")
)]
class CucumberTest {
}
*/