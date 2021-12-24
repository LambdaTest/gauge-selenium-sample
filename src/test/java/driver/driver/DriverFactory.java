package driver.driver;

import com.thoughtworks.gauge.AfterSpec;
import com.thoughtworks.gauge.BeforeSpec;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    // Get a new WebDriver Instance.
    // There are various implementations for this depending on browser/OS. The required browser/OS can be set as an environment variable.
    // Refer http://getgauge.io/documentation/user/current/managing_environments/README.html

    private static final String username = System.getenv("LT_USERNAME");
    private static final String accesskey = System.getenv("LT_ACCESS_KEY");
    public static final String gridURL = "@hub.lambdatest.com/wd/hub";
    public String status = "passed";

    private static WebDriver driver= null;

    public static WebDriver getDriver() {
        return driver;
    }


    @BeforeSpec
    public void setUp() {

    try{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", System.getenv("BROWSER"));
        capabilities.setCapability("version", System.getenv("BROWSER_VERSION"));
        capabilities.setCapability("platform", System.getenv("PLATFORM"));
        capabilities.setCapability("build", "Java Gauge Framework");
        capabilities.setCapability("name", "Sample Gauge Test");
        capabilities.setCapability("network", false); // To enable network logs
        capabilities.setCapability("visual", false); // To enable step by step screenshot
        capabilities.setCapability("console", false); // To capture console logs

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    @AfterSpec
    public void tearDown() {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }

}
