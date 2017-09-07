package tests;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.path.xml.element.Node;
import com.jayway.restassured.response.Response;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import environmentconfig.TestData;
import org.junit.BeforeClass;
import java.util.List;
import java.util.Random;
import static com.jayway.restassured.RestAssured.given;

public class TestBase {

  public static TestData config;

  /* Get random event */
  public static String getRandomEvent() {

    Response response =
            given()
                    //.contentType()
                    .header("who-apiKey", config.getApikey())
                    .header("who-secret", config.getApiSecret())
                    .header("who-ticket", config.getAuthenticationTicket())
                    .header("Accept", "application/vnd.who.Sportsbook+xml;v=1;charset=utf-8")
                    .when()
                    .get("competitions/events/inplay/live");

    Random rand = new Random();
    XmlPath xmlPath = new XmlPath(response.asString());

    List<Node> linkNodes = xmlPath.getList("depthFirst().findAll { it.name() == 'event' }");
    int randomEvent = rand.nextInt(linkNodes.size());

    return linkNodes.get(randomEvent).getAttribute("id");
  }

  /*Get prices on the random outcome*/
  public static void getPricesOnRandomOutcome(String event){

    Random rand = new Random();
    Response response =
            given()
                    .header("who-apiKey", config.getApikey())
                    .header("who-secret", config.getApiSecret())
                    .header("who-ticket", config.getAuthenticationTicket())
                    .header("Accept", "application/vnd.who.Sportsbook+xml;v=1;charset=utf-8")
                    .when()
                    .get("competitions/events/"+event+"/markets/outcomes");

    XmlPath xmlPath = new XmlPath(response.asString());
    List<Node> outcomes = xmlPath.getList("depthFirst().findAll { it.name() == 'outcome' }");
    int randomEvent = rand.nextInt(outcomes.size());

    config.setOutcomeId(outcomes.get(randomEvent).getAttribute("id"));
    config.setPriceNum(outcomes.get(randomEvent).getNode("odds").getNode("livePrice").getNode("priceNum").value());
    config.setPriceDen(outcomes.get(randomEvent).getNode("odds").getNode("livePrice").getNode("priceDen").value());
    config.setPriceFrac(outcomes.get(randomEvent).getNode("odds").getNode("livePrice").getNode("priceFrac").value());
    config.setPriceDec(outcomes.get(randomEvent).getNode("odds").getNode("livePrice").getNode("priceDec").value());
//
//    System.out.println(outcomes.size());
//    System.out.println(outcomes.get(0));

  }

  /* Get actual user balance */
  public static void getBalance(){
    Response response =
            given()
                    .contentType(config.getContentType())
                    .header("who-apiKey", config.getApikey())
                    .header("who-secret", config.getApiSecret())
                    .header("who-ticket", config.getAuthenticationTicket())
                    .header("Accept", config.getAcceptedType())
                    .when()
                    .get("accounts/me/balance");
    String balance
            = response.then()
            .contentType(ContentType.JSON)
            .extract()
            .path("whoAccounts.account.balance");
    config.setBalance(balance);
  }

  @BeforeClass
  /* Setup a environment defaults */
  public static void setUp(){
    config = new TestData();
    RestAssured.baseURI = "https://sandbox.whapi.com";
    RestAssured.basePath = "/v1/";
  }

  @Before
  /* Get a authentication ticket */
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
  }

  @AfterClass
  /* Remove a config */
  public static void tearDown(){
    config = null;
  }
}