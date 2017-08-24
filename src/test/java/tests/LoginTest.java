package tests;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import environmentconfig.TestData;

import static com.jayway.restassured.RestAssured.given;

public class LoginTest extends TestBase {

  @Test
  public void getAuthenticationTicket_True(){

    Response response =
            given()
                    .contentType(config.getContentType())
                    //.header("Content-Type","application/x-www-form-urlencoded")
                    .header("who-apiKey", config.getApikey())
                    .header("who-secret", config.getApiSecret())
                    .header("Accept", config.getAcceptedType())
                    .formParam("username",config.getUsername())
                    .formParam("password", config.getPassword())
                    .request()
            .when()
                    .post("https://sandbox.whapi.com/v1/sessions/tickets");
    String auth = response
            .then()
            .contentType(ContentType.JSON)
            .extract()
            .path("whoSessions.ticket");
    Assert.assertEquals(201, response.statusCode());

    System.out.println(config.getAuthenticationTicket());
    }

  @Test
  public void getAuthenticationTicket_False(){

    Response response =
            given()
                    .contentType(config.getContentType())
                    //.header("Content-Type","application/x-www-form-urlencoded")
                    .header("who-apiKey", config.getApikey())
                    .header("who-secret", config.getApiSecret())
                    .header("Accept", config.getAcceptedType())
                    .formParam("username","badusername")
                    .formParam("password", config.getPassword())
                    .request()
                    .when()
                    .post("https://sandbox.whapi.com/v1/sessions/tickets");

    Assert.assertEquals(401, response.statusCode());
    System.out.println(config.getAuthenticationTicket());
  }
}