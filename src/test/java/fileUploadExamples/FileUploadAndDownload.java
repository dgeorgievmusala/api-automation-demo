package fileUploadExamples;

import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FileUploadAndDownload {
    //single file upload

    @Test
    public void uploadSingleFile() {

        File myFile = new File("C:\\Users\\diyan.georgiev\\Downloads\\Notes.txt");
        given()
                .multiPart("file", myFile)
                .contentType("multipart/form-data")
                .when().post("http://localhost:8080/uploadFile")
                .then().statusCode(200).body("fileName", equalTo("Notes.txt"))
                .log().body();
    }

    @Test
    public void downloadFile() {
        given()
                .when().get("http://localhost:8080/downloadFile/Notes.txt")
                .then().statusCode(200)
                .log().body();
    }
}
