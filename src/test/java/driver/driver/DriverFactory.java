package driver.driver;

import com.thoughtworks.gauge.AfterSpec;
import com.thoughtworks.gauge.BeforeSpec;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class DriverFactory {

    // Get a new WebDriver Instance.
    // There are various implementations for this depending on browser/OS. The
    // required browser/OS can be set as an environment variable.
    // Refer
    // http://getgauge.io/documentation/user/current/managing_environments/README.html

    private static final String username = System.getenv("LT_USERNAME");
    private static final String accesskey = System.getenv("LT_ACCESS_KEY");
    public static final String gridURL = "@hub.lambdatest.com/wd/hub";
    public String status = "passed";

    private static WebDriver driver = null;

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeSpec
    public void setUp() {

        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browserVersion", "97");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("user", System.getenv("LT_USERNAME"));
        ltOptions.put("accessKey", System.getenv("LT_ACCESS_KEY"));
        ltOptions.put("build", "Selenium 4 gauge");
        ltOptions.put("name", this.getClass().getName());
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("seCdp", true);  //chrome dev tool protocol caps
        ltOptions.put("selenium_version", "4.0.0"); //Selenium 4

        capabilities.setCapability("LT:Options", ltOptions);

            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @AfterSpec
    public void tearDown() throws InterruptedException {
        if (driver != null) {
            // Clearing browser Cache after Test
            driver.manage().deleteAllCookies(); // delete all cookies
            Thread.sleep(7000); // wait 7 seconds to clear cookies.
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }

}
