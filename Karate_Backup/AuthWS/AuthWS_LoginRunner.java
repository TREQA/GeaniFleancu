package AthenaWS_KARATE.AuthWS;

import com.intuit.karate.junit4.Karate;
import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Karate.class)

@CucumberOptions(features = "classpath:AthenaWS/AuthWS/login.feature")

public class AuthWS_LoginRunner {

}
