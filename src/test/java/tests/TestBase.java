package tests;

import com.jayway.restassured.http.ContentType;
import org.junit.Before;
import environmentconfig.TestData;

import static com.jayway.restassured.RestAssured.given;

public class TestBase {

  public static TestData config;
  public static String ticket;

  @Before
  public static void setUp(){
    String ticket =
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
                    .post("https://sandbox.whapi.com/v1/sessions/tickets")
            .then()
                    .contentType(ContentType.JSON)
                    .extract()
                    .path("whoSessions.ticket");

    System.out.println(ticket);
  }
}