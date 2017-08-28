package tests;

import com.jayway.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.jayway.restassured.RestAssured.given;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Bets extends TestBase{

  @Test
  public void ZplaceABet() {
    Response response =
            given()
                    .contentType(config.getContentType())
                    .header("who-apiKey", config.getApikey())
                    .header("who-secret", config.getApiSecret())
                    .header("who-ticket", config.getAuthenticationTicket())
                    .header("Accept", "application/json")
                    .formParam("legType","W")
                    .formParam("stake", "1.00")
                    .formParam("outcomeId", "1")
                    .formParam("priceType", "L")
                    .formParam("priceNum", "1")
                    .formParam("priceDen", "4")
                    .request()
            .when()
                    .post("bets/me/");

    //Assert.assertEquals("200", response.statusCode());
    System.out.println(response.asString());
  }

  @Test
  public void getBetsHistory() {
    Response response =
            given()
                    .contentType(config.getContentType())
                    .header("who-apiKey", config.getApikey())
                    .header("who-secret", config.getApiSecret())
                    .header("who-ticket", config.getAuthenticationTicket())
                    .header("Accept", "application/json")
                    .when()
                    .get("bets/me?blockSize=5&blockNum=0&settled=Y");
    System.out.println(response.asString());
  }

  @Test
  public void getFreeBets() {
    Response response =
            given()
                    .contentType(config.getContentType())
                    .header("who-apiKey", config.getApikey())
                    .header("who-secret", config.getApiSecret())
                    .header("who-ticket", config.getAuthenticationTicket())
                    .header("Accept", "application/json")
            .when()
                    .get("bets/me/free");

    System.out.println(response.asString());
  }
}
