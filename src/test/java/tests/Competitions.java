package tests;

import com.jayway.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Competitions extends TestBase{

  @Test
  public void getEventsInplayLive(){
    Response response =
            given()
                    //.contentType()
                    .header("who-apiKey", config.getApikey())
                    .header("who-secret", config.getApiSecret())
                    .header("who-ticket", config.getAuthenticationTicket())
                    .header("Accept", "application/vnd.who.Sportsbook+xml;v=1;charset=utf-8")
                    .when()
                    .get("competitions/events/inplay/live");

    assertEquals(200, response.getStatusCode());

    getBalance();
    String event = getRandomEvent();
    getPricesOnRandomOutcome(event);

  }
}