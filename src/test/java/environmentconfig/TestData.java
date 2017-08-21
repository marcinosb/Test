package environmentconfig;

public class TestData {

  private static final String hostUrl = "https://sandbox.whapi.com/";
  private static final String apikey = "l7xxdbad8c0b183147f99f464f42ded229be";
  private static final String apiSecret = "35ba66acb7b64e2ea8dd895dd27616f5";
  private static final String username = "QASDET1";
  private static final String password = "QAAPI1";
  public static final String contentType = "application/x-www-form-urlencoded";
  public static final String acceptedType = "application/json";

  public static String getAcceptedType() {
    return acceptedType;
  }

  public static String getContentType() {
    return contentType;
  }

  public static String getHostUrl() {
    return hostUrl;
  }

  public static String getApikey() {
    return apikey;
  }

  public static String getApiSecret() {
    return apiSecret;
  }

  public static String getUsername() {
    return username;
  }

  public static String getPassword() {
    return password;
  }
}