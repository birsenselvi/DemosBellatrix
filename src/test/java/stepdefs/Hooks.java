package stepdefs;

import driver.Driver;
import io.cucumber.java.After;

public class Hooks {

    @After
    public void afterScenario() throws InterruptedException {
      Thread.sleep(1000);
      Driver.quit();
    }
}
