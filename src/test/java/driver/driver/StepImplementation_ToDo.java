package driver.driver;

import com.thoughtworks.gauge.Step;
import driver.driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
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
        driver.get("https://lambdatest.github.io/sample-todo-app/");
        String title = driver.getTitle();
        
         /*
         * text(): A built-in method in Selenium WebDriver that is used with XPath
         * locator to locate an element based on its exact text value.
         * Example: //*[ text() = ‘5 of 5 remaining’ ]
         * contains(): Similar to the text() method, contains() is another built-in
         * method used to locate an element based on partial text match.
         * For example, if we need to locate a label that has “5 of 5 remaining” as its
         * text, it can be located using the following line of code with Xpath.
         * Example: //*[ contains (text(), ‘5 of 5’ ) ]
         */

        // Locating element with text()
        WebElement e = driver.findElement(By.xpath("//*[text()='5 of 5 remaining']"));
        System.out.println(e.getText());

        // located element with contains()
        WebElement m = driver.findElement(By.xpath("//*[contains(text(),'5 of 5')]"));
        System.out.println(m.getText());


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
