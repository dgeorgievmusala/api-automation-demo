package testcases;


import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import payloads.Payload;
import pojo.UserModel;
import routes.Routes;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class UsersTest extends BaseClass {

    //Create new user test

    @Test
    public void testPostNewUser() {
        UserModel newUser = Payload.usernamePayload();
        int userId = given().contentType(ContentType.JSON)
                .body(newUser)
                .header("x-api-key", "reqres-free-v1")
                .when().post(Routes.POST_USER)
                .then()
                .log().body()
                .statusCode(201)
                .body("id", notNullValue())
                .body("email", equalTo(newUser.getEmail()))
                .body("first_name", equalTo(newUser.getFirst_name()))
                .body("last_name", equalTo(newUser.getLast_name()))
                .body("avatar", equalTo(newUser.getAvatar()))
                .extract().jsonPath().getInt("id");
        System.out.println("User id: " + userId);

    }
}
