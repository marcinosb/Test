package tests;

import com.jayway.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

public class Competitions extends TestBase{

  @Test
  public void getPrices(){
    Response response =
            given()
                    //.contentType()
                    .header("who-apiKey", config.getApikey())
                    //.header("who-secret", config.getApiSecret())
                    //.header("who-ticket", config.getAuthenticationTicket())
                    .header("Accept", "application/vnd.who.Sportsbook+xml;v=1;charset=utf-8")
            .when()
                    .get("competitions/categories");

    System.out.println(response.asString());


  }
}
