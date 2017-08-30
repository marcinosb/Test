package tests;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.path.xml.element.Node;
import com.jayway.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static com.jayway.restassured.RestAssured.given;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Bets extends TestBase{

  @Test
  public void ZplaceABet() throws InterruptedException {

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
                    .formParam("stake", "1.00")
                    .formParam("outcomeId", config.getOutcomeId())
                    .formParam("priceType", "L")
                    .formParam("priceNum", config.getPriceNum())
                    .formParam("priceDen", config.getPriceDen())
                    .request()
            .when()
                    .post("bets/me/");

    System.out.println("**********Przed");
    System.out.println(config.getAuthenticationTicket());

    XmlPath xmlPath = response.xmlPath();
    Node whoBets = xmlPath.get("whoBets");
    String delayBetId = whoBets.getNode("betDelayed").getNode("delayBetId").value();
    config.setDelayBetId(delayBetId);
    Thread.sleep(Long.parseLong("15000"));

    /////////////////////////////////////////////////////////////////
    Response response2 =
            given()
                    .contentType("application/xml")
                    .header("who-apiKey", config.getApikey())
                    .header("who-secret", config.getApiSecret())
                    .header("who-ticket", config.getAuthenticationTicket())
                    .header("Accept", "application/xml")
                    .body("<whoBets>\n" +
                            "\t<bet>\n" +
                            "\t\t<betNum>1</betNum>\n" +
                            "\t\t<delayBetId>"+config.getDelayBetId()+"</delayBetId>\n" +
                            "\t\t<betTypeCode>SGL</betTypeCode>\n" +
                            "\t\t<stake>1</stake>\n" +
                            "\t\t<leg>\n" +
                            "\t\t\t<legType>W</legType>\n" +
                            "\t\t\t<part>\n" +
                            "\t\t\t\t<outcomeId>"+config.getOutcomeId()+"</outcomeId>\n" +
                            "\t\t\t\t<priceType>L</priceType>\n" +
                            "\t\t\t\t<priceNum>"+config.getPriceNum()+"</priceNum>\n" +
                            "\t\t\t\t<priceDen>"+config.getPriceDen()+"</priceDen>\n" +
                            "\t\t\t</part>\n" +
                            "\t\t</leg>\n" +
                            "\t</bet>\n" +
                            "</whoBets>")
            .when()
                    .post("bets/me/");

    System.out.println(response2.asString());

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
}