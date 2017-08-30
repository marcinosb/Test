package tests;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import static com.jayway.restassured.RestAssured.given;

public class Accounts extends TestBase {

  @Test
  public void getAccountDetails_StatusCode(){
    Response response =
            given()
                    .contentType(config.getContentType())
                    .header("who-apiKey", config.getApikey())
                    .header("who-secret", config.getApiSecret())
                    .header("who-ticket", config.getAuthenticationTicket())
                    .header("Accept", config.getAcceptedType())
            .when()
                    .get("accounts/me");

    Assert.assertEquals("200", response.statusCode());
  }
  @Test
  public void getAccountDetails_AccountDetails_LastName(){
    Response response =
            given()
                    .contentType(config.getContentType())
                    .header("who-apiKey", config.getApikey())
                    .header("who-secret", config.getApiSecret())
                    .header("who-ticket", config.getAuthenticationTicket())
                    .header("Accept", config.getAcceptedType())
                    .when()
                    .get("accounts/me");

    String lastName
            = response.then()
            .contentType(ContentType.JSON)
            .extract()
            .path("whoAccounts.account.lastName");

    Assert.assertEquals(config.getLastName(),lastName);
  }

  @Test
  public void getAccountDetails_AccountDetails_AccountNum(){
    Response response =
            given()
                    .contentType(config.getContentType())
                    .header("who-apiKey", config.getApikey())
                    .header("who-secret", config.getApiSecret())
                    .header("who-ticket", config.getAuthenticationTicket())
                    .header("Accept", config.getAcceptedType())
                    .when()
                    .get("accounts/me");

    String accountNum
            = response.then()
            .contentType(ContentType.JSON)
            .extract()
            .path("whoAccounts.account.accountNum");

    Assert.assertEquals(config.getAccountNum(),accountNum);
  }

  @Test
  public void getAccountDetails_AccountDetails_Balance(){
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
    Assert.assertEquals(200,response.getStatusCode());
  }
}