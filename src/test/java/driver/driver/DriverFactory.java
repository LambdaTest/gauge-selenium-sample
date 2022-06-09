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
            capabilities.setCapability("browserName", System.getenv("BROWSER"));
            capabilities.setCapability("version", System.getenv("BROWSER_VERSION"));
            capabilities.setCapability("platform", System.getenv("PLATFORM"));
            capabilities.setCapability("build", "Java Gauge Framework");
            capabilities.setCapability("name", "Sample Gauge Test");

            ChromeOptions options = new ChromeOptions ();
            // Setting chrome flag for incognito mode
            options.addArguments("--incognito");
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);

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
