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
import static com.jayway.restassured.path.xml.XmlPath.with;

public class TestBase {

  public static TestData config;

  public static void getRandomEvent(Response response) {

    Random rand = new Random();
    int eventNum = with(response.asString()).get("whoCompetitions.category.size()");
    int randomCategory = rand.nextInt(eventNum);
    List<Node> events = with(response.asString()).get("whoCompetitions.category");

    XmlPath xmlPath = new XmlPath(response.asString());
    List<Node> linkNodes = xmlPath.getList("depthFirst().findAll { it.name() == 'event' } ");
    System.out.println(linkNodes.size());

  }
  public static int randInt(int max) {

    Random rand = new Random();

    int randomNum = rand.nextInt((max + 1));

    return randomNum;
  }

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
    }

  @AfterClass
  public static void tearDown(){
    config = null;
  }
}