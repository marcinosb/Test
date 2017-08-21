package tests;

import com.jayway.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import environmentconfig.TestData;

import static com.jayway.restassured.RestAssured.given;

public class LoginTest extends TestBase {

  @Test
  public void loginTest(){

    Response response =
            given()
                    .contentType(TestData.getContentType())
                    //.header("Content-Type","application/x-www-form-urlencoded")
                    .header("who-apiKey", TestData.getApikey())
                    .header("who-secret", TestData.getApiSecret())
                    .header("Accept", TestData.getAcceptedType())
                    .formParam("username",TestData.getUsername())
                    .formParam("password", TestData.getPassword())
                    .request()
        .when()
        .post("https://sandbox.whapi.com/v1/sessions/tickets");

        Assert.assertEquals(201, response.getStatusCode());

        }
}