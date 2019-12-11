package driver.driver;

import com.thoughtworks.gauge.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class StepImplementation_Google {

    private final WebDriver driver;

    public StepImplementation_Google() {
        this.driver = DriverFactory.getDriver();
    }

    @Step("User is on Google home page")
    public void googlePage() throws InterruptedException {

        driver.get("https://www.google.com/");
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    @Step("User search for <loginpage>")
    public void searchLogin(String loginpage) throws InterruptedException {

        WebElement input = driver.findElement(By.name("q"));
        input.sendKeys(loginpage);

        Actions action = new Actions(driver);
        action.sendKeys(Keys.ENTER).build().perform();

        Thread.sleep(2000);

    }

    @Step("Verify the page title")
    public void loginTest() throws InterruptedException {

        String title = driver.getTitle();
        assertEquals(title,"lambdatest login - Google Search");
        Thread.sleep(2000);

    }
}
