package driver.driver;

import com.thoughtworks.gauge.Step;
import driver.driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class StepImplementation_ToDo {

    private final WebDriver driver;

    public StepImplementation_ToDo() {
        this.driver = DriverFactory.getDriver();
    }

  @Step("Open the todo app")
  public void gotoApp() throws InterruptedException {

        System.out.println(DriverFactory.getDriver());
        driver.get("https://lambdatest.com");

        // Locating element by link text 
        WebElement Element = driver.findElement(By.linkText("Book a Demo"));

        // Scrolling down the page till the element is found
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", Element);
        Thread.sleep(1500);

        // Scrolling down by pixels
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-500)", "");

        Thread.sleep(1500);

        // Scrolling up by pixels
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)", "");

        Thread.sleep(1500);


        driver.get("https://lambdatest.github.io/sample-todo-app/");
        String title = driver.getTitle();

        
        assertEquals(title,"Sample page - lambdatest.com");
  }

  @Step("Select the desired items")
  public void selectItems() throws InterruptedException {

      WebElement itemOne = driver.findElement(By.name("li1"));
      itemOne.click();

      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

      WebElement itemThird = driver.findElement(By.name("li3"));
      itemThird.click();
  }

  @Step("Add new item <itemName>")
  public void addNewItem(String itemName) throws InterruptedException {

      WebElement addItem  = driver.findElement(By.id("sampletodotext"));
      addItem.sendKeys(itemName);
      Thread.sleep(2000);

      WebElement addButton = driver.findElement(By.id("addbutton"));
      addButton.click();
  }
}
