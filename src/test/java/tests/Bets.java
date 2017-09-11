package tests;

import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.path.xml.element.Node;
import com.jayway.restassured.response.Response;
import environmentconfig.Bet;
import environmentconfig.Leg;
import environmentconfig.Part;
import environmentconfig.WhoBets;
import org.junit.Assert;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

public class Bets extends TestBase{

  @Test
  public void randomBet() throws InterruptedException {

    getBalance();
    String event = getRandomEvent();
    getPricesOnRandomOutcome(event);
    Response response =
            given()
                    .contentType(config.getContentType())
                    .header("who-apiKey", config.getApikey())
                    .header("who-secret", config.getApiSecret())
                    .header("who-ticket", config.getAuthenticationTicket())
                    .header("Accept", "application/xml")
                    .formParam("legType","W")
                    .formParam("stake", "2.00")
                    .formParam("outcomeId", config.getOutcomeId())
                    .formParam("priceType", "L")
                    .formParam("priceNum", config.getPriceNum())
                    .formParam("priceDen", config.getPriceDen())
                    .request()
            .when()
                    .post("bets/me/");

    XmlPath xmlPath = response.xmlPath();
    Node whoBets = xmlPath.get("whoBets");
    String delayBetId = whoBets.getNode("betDelayed").getNode("delayBetId").value();

    config.setDelayBetId(delayBetId);
    Thread.sleep(Long.parseLong("10000"));

    /*Create a body for the bet*/
    WhoBets whobets = new WhoBets(
            new Bet("1", config.getDelayBetId(), "SGL", "2.00",
                    new Leg("W", new Part(config.getOutcomeId(),
                            "L", config.getPriceNum(), config.getPriceDen()))));


    Response betResponse =
            given()
                    .contentType("application/xml")
                    .header("who-apiKey", config.getApikey())
                    .header("who-secret", config.getApiSecret())
                    .header("who-ticket", config.getAuthenticationTicket())
                    .header("Accept", "application/xml")
                    .body(whobets)
                    .log().all()
            .when()
                    .post("bets/me/");
  }

  @Test
  public void betInsufficientFunds() {

    getBalance();
    String event = getRandomEvent();
    getPricesOnRandomOutcome(event);
    Response response =
            given()
                    .contentType(config.getContentType())
                    .header("who-apiKey", config.getApikey())
                    .header("who-secret", config.getApiSecret())
                    .header("who-ticket", config.getAuthenticationTicket())
                    .header("Accept", "application/xml")
                    .formParam("legType", "W")
                    .formParam("stake", String.valueOf(config.getBalance()+111.99))
                    .formParam("outcomeId", config.getOutcomeId())
                    .formParam("priceType", "L")
                    .formParam("priceNum", config.getPriceNum())
                    .formParam("priceDen", config.getPriceDen())
                    .request()
                    .when()
                    .post("bets/me/");

    XmlPath xmlPath = new XmlPath(response.asString());
    String faultCode = xmlPath.getNode("whoBets").getNode("whoFaults")
            .getNode("fault").getNode("faultCode").value();

    Assert.assertEquals("23010", faultCode);
  }

  @Test
  public void betStakeTooLow() {

    getBalance();
    String event = getRandomEvent();
    getPricesOnRandomOutcome(event);
    Response response =
            given()
                    .contentType(config.getContentType())
                    .header("who-apiKey", config.getApikey())
                    .header("who-secret", config.getApiSecret())
                    .header("who-ticket", config.getAuthenticationTicket())
                    .header("Accept", "application/xml")
                    .formParam("legType", "W")
                    .formParam("stake", "0.01")
                    .formParam("outcomeId", config.getOutcomeId())
                    .formParam("priceType", "L")
                    .formParam("priceNum", config.getPriceNum())
                    .formParam("priceDen", config.getPriceDen())
                    .request()
                    .when()
                    .post("bets/me/");

    XmlPath xmlPath = new XmlPath(response.asString());
    String faultCode = xmlPath.getNode("whoBets").getNode("whoFaults")
            .getNode("fault").getNode("faultCode").value();

    Assert.assertEquals("23011", faultCode);
  }

  @Test
  public void betStakeTooHigh(){

    getBalance();
    String event = getRandomEvent();
    getPricesOnRandomOutcome(event);
    getMaxStake();

    Response response =
            given()
                    .contentType(config.getContentType())
                    .header("who-apiKey", config.getApikey())
                    .header("who-secret", config.getApiSecret())
                    .header("who-ticket", config.getAuthenticationTicket())
                    .header("Accept", "application/xml")
                    .formParam("legType", "W")
                    .formParam("stake", String.valueOf(Double.parseDouble(config.getMaxStake())+1.01))
                    .formParam("outcomeId", config.getOutcomeId())
                    .formParam("priceType", "L")
                    .formParam("priceNum", config.getPriceNum())
                    .formParam("priceDen", config.getPriceDen())
                    .request()
                    .when()
                    .post("bets/me/");

    XmlPath xmlPath = new XmlPath(response.asString());
    String faultCode = xmlPath.getNode("whoBets").getNode("whoFaults")
            .getNode("fault").getNode("faultCode").value();

    Assert.assertEquals("23012", faultCode);
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

    /* TODO: Add verification/confirmation for bets */
  }
}