package api.base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BaseTest {
    @BeforeClass
    public void setup(){
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
    }
}
