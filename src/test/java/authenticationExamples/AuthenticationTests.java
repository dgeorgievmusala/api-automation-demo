package authenticationExamples;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class AuthenticationTests {

    //Basic Auth
@Test
    public void verifyBasicAuth()

    {
        given().auth().basic("postman", "password").
        when().get("https://postman-echo.com/basic-auth")
                .then().statusCode(200)
                .body("authenticated", equalTo(true)).log().body();

    }
}
