package tests;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

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

    System.out.println(response.asString());
    String faultCode = response
            .then()
            .contentType(ContentType.JSON)
            .extract()
            .path("whoFaults[0].faultCode");

    Assert.assertEquals(401, response.statusCode());
  }

  @Test
  public void isSessionStillActive_True(){

    Response response =
            given()
                    //.contentType(config.getContentType())
                    //.header("Content-Type","application/x-www-form-urlencoded")
                    .header("who-apiKey", config.getApikey())
                    .header("who-secret", config.getApiSecret())
                    .header("Accept", config.getAcceptedType())
            .when()
                    .contentType(config.getContentType())
                    .head("https://sandbox.whapi.com/v1/sessions/tickets/" + config.getAuthenticationTicket());

    Assert.assertEquals(204, response.statusCode());
  }
}