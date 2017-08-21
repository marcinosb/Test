package tests;

import org.junit.Before;
import environmentconfig.TestData;

public class TestBase {

  public static TestData config;

  @Before
  public void setUp(){
    config = new TestData();
  }
}
