package createPostRequestsExamples;

import org.junit.Test;
import org.testng.annotations.AfterMethod;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;

public class PostRequestBodyExamples {
    String studentId;

    @Test
    public void createStudentUsingHashMap() {

        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Scott");
        requestBody.put("location", "France");
        requestBody.put("phone", "123456789");

        String[] courses = {"C", "C++"};
        requestBody.put("courses", courses);
        studentId= given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name", equalTo("Scott"))
                .body("location", equalTo("France"))
                .body("phone", equalTo("123456789"))
                .body("courses[0]", equalTo("C"))
                .body("courses[1]", equalTo("C++")).toString();
    }


@AfterMethod
    public void deleteStudentRecord() {
   given().
           when().delete("http://localhost:3000/students"+ "studentId")
           .then().statusCode(200);
 }
}