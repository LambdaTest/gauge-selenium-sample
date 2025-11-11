package driver.driver;

import com.thoughtworks.gauge.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepImplementation_ToDo {

    private WebDriver driver;

    @Step("Open the todo app")
    public void gotoApp() {
        try {
            DriverFactory.initDriver();
            driver = DriverFactory.getDriver();

            driver.get("https://lambdatest.github.io/sample-todo-app/");
            String title = driver.getTitle();
            assertEquals("Modern To-Do App | LambdaTest", title, "Page title mismatch!");
        } catch (Exception e) {
            throw new RuntimeException("Failed to open the app: " + e.getMessage(), e);
        }
    }

    @Step("Select the desired items")
    public void selectItems() {
        driver = DriverFactory.getDriver();
        driver.findElement(By.name("li1")).click();
        driver.findElement(By.name("li3")).click();
    }

    @Step("Add new item <itemName>")
    public void addNewItem(String itemName) {
        driver = DriverFactory.getDriver();
        WebElement addItem = driver.findElement(By.id("sampletodotext"));
        addItem.sendKeys(itemName);
        driver.findElement(By.id("addbutton")).click();
    }
}
