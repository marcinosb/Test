package tests;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.response.Response;
import com.thoughtworks.xstream.XStream;
import org.junit.Test;
import java.util.Random;

import static com.jayway.restassured.RestAssured.given;
        import static com.jayway.restassured.path.xml.XmlPath.with;

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

    Random rand = new Random();

//    XmlPath xml = new XmlPath(response.asString());
//    int eve = with(response.asString()).get("whoCompetitions.category.size()");
//    int randomCategory = rand.nextInt(eve);

    getRandomEvent(response);

  }
}