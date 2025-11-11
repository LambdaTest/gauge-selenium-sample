package driver.driver;

import com.thoughtworks.gauge.AfterSpec;
import com.thoughtworks.gauge.BeforeSpec;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final String USERNAME = System.getenv("LT_USERNAME");
    private static final String ACCESS_KEY = System.getenv("LT_ACCESS_KEY");
    private static final String GRID_URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub.lambdatest.com/wd/hub";

    public static WebDriver getDriver() {
        return driver.get();
    }

    @BeforeSpec
    public void setUp() {
        initDriver();
    }

    public static void initDriver() {
        if (driver.get() == null) {
            try {
                ChromeOptions browserOptions = new ChromeOptions();

                Map<String, Object> ltOptions = new HashMap<>();
                ltOptions.put("build", "Java Gauge Parallel Test");
                ltOptions.put("name", "Sample Gauge Test");
                ltOptions.put("platformName", System.getenv("PLATFORM") != null ? System.getenv("PLATFORM") : "Windows 10");
                ltOptions.put("browserVersion", System.getenv("BROWSER_VERSION") != null ? System.getenv("BROWSER_VERSION") : "latest");
                ltOptions.put("project", "Gauge Selenium Sample");
                ltOptions.put("w3c", true);

                browserOptions.setCapability("browserName", System.getenv("BROWSER") != null ? System.getenv("BROWSER") : "chrome");
                browserOptions.setCapability("lt:options", ltOptions);

                driver.set(new RemoteWebDriver(new URL(GRID_URL), browserOptions));

            } catch (Exception e) {
                System.err.println("Error initializing driver: " + e.getMessage());
            }
        }
    }

    @AfterSpec
    public void tearDown() {
        WebDriver currentDriver = driver.get();
        if (currentDriver != null) {
            try {
                ((JavascriptExecutor) currentDriver).executeScript("lambda-status=passed");
                currentDriver.quit();
            } catch (Exception e) {
                System.err.println("Error during teardown: " + e.getMessage());
            } finally {
                driver.remove();
            }
        }
    }
}
