package tests;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

public class Accounts extends TestBase {

  @Test
  public void getAccountDetails_True(){
    Response response =
            given()
                    .contentType(config.getContentType())
                    .header("who-apiKey", config.getApikey())
                    .header("who-secret", config.getApiSecret())
                    .header("Accept", config.getAcceptedType())
                    .header("who-ticket", config.getAuthenticationTicket())

            .when()
                    .get("accounts/me");
    System.out.println(config.getAuthenticationTicket());
    System.out.println(response.asString());
  }
  @Test
  public void getAccountDetails_True2(){
    Response response =
            given()
                    .contentType(config.getContentType())
                    .header("who-apiKey", config.getApikey())
                    .header("who-secret", config.getApiSecret())
                    .header("Accept", config.getAcceptedType())
                    .header("who-ticket", config.getAuthenticationTicket())

            .when()
                    .get("accounts/me");

    System.out.println(config.getAuthenticationTicket());
    System.out.println(response.asString());
  }
}