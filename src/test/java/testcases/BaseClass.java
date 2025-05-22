package testcases;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;
import routes.Routes;
import utils.ConfigReader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class BaseClass {

    ConfigReader configReader;

    //For logging
    RequestLoggingFilter requestLoggingFilter;
    ResponseLoggingFilter responseLoggingFilter;


    @BeforeClass
    public void setup() throws FileNotFoundException
    {
        RestAssured.baseURI=Routes.BASE_URL;

        configReader=new ConfigReader();


        // Setup filters for logging
        FileOutputStream fos = new FileOutputStream(".\\logs\\test_logging.log");
        PrintStream log = new PrintStream(fos, true);

        requestLoggingFilter = new RequestLoggingFilter(log);
        responseLoggingFilter = new ResponseLoggingFilter(log);

        RestAssured.filters(requestLoggingFilter, responseLoggingFilter);



    }

}
