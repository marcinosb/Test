package environmentconfig;

public class TestData {

  private String hostUrl = "https://sandbox.whapi.com/";
  private String apikey = "l7xxdbad8c0b183147f99f464f42ded229be";
  private String apiSecret = "35ba66acb7b64e2ea8dd895dd27616f5";
  private String username = "QASDET1";
  private String password = "QAAPI1";
  private String contentType = "application/x-www-form-urlencoded";
  private String acceptedType = "application/json";
  private String authenticationTicket = null;

  private String firstName = "Cue";
  private String lastName = "Ae";
  private String country = "United Kingdom";
  private String accountId = "118";
  private String accountNum = "41535YV";
  private String city = "";
  private String contactable = "Y";
  private String countryCode = "GB";
  private String customerId = "118";

  public String getAuthenticationTicket() {
    return authenticationTicket;
  }

  public void setAuthenticationTicket(String authenticationTicket) {
    this.authenticationTicket = authenticationTicket;
  }

  public  String getAcceptedType() {
    return acceptedType;
  }

  public  String getContentType() {
    return contentType;
  }

  public  String getHostUrl() {
    return hostUrl;
  }

  public  String getApikey() {
    return apikey;
  }

  public  String getApiSecret() {
    return apiSecret;
  }

  public  String getUsername() {
    return username;
  }

  public  String getPassword() {
    return password;
  }
}