package tests;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import environmentconfig.TestData;
import org.junit.BeforeClass;

import static com.jayway.restassured.RestAssured.given;

public class TestBase {

  public static TestData config;

  @BeforeClass
  public static void setUp(){
    config = new TestData();
    RestAssured.baseURI = "https://sandbox.whapi.com";
    RestAssured.basePath = "/v1/";
  }
  @Before
  public void preconditions(){

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
                    .post("sessions/tickets");
    String auth = response
            .then()
            .contentType(ContentType.JSON)
            .extract()
            .path("whoSessions.ticket");
    config.setAuthenticationTicket(auth);
    Assert.assertEquals(201, response.statusCode());
    System.out.println(config.getAuthenticationTicket());
    }

  @AfterClass
  public static void tearDown(){
    config = null;
  }
}