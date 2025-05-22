package testcases;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import payloads.Payload;
import pojo.UserModel;
import routes.Routes;
import utils.DataProviders;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UsersDataDriventTest {
    @Test(dataProvider = "jsonDataProvider", dataProviderClass = DataProviders.class)
    public void testAddNewUser(Map<String, String> data) {

        String email = data.get("email");
        String first_name = data.get("first_name");
        String last_name = data.get("last_name");
        String avatar = data.get("avatar");

        UserModel newUser = new UserModel(email, first_name, last_name, avatar);
        int userId = given()
                .baseUri(Routes.BASE_URL)
                .contentType(ContentType.JSON)
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
