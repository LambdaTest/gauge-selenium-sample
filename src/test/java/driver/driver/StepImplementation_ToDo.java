package driver.driver;

import com.thoughtworks.gauge.Step;
import driver.driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.remote.Augmenter;


import java.util.List;
import java.util.Optional;

import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v97.performance.*;
import org.openqa.selenium.devtools.v97.performance.model.Metric;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class StepImplementation_ToDo {

    private final WebDriver driver;
    public WebDriver cdpDriver;

    public StepImplementation_ToDo() {
        this.driver = DriverFactory.getDriver();
    }

  @Step("Open the todo app")
  public void gotoApp() throws InterruptedException {

        System.out.println(DriverFactory.getDriver());

        cdpDriver=driver;
        Augmenter augmenter = new Augmenter();
        cdpDriver = augmenter.augment(cdpDriver);

        DevTools devTools = ((HasDevTools) cdpDriver).getDevTools();
        devTools.createSession();

        devTools.send(Performance.enable(Optional.empty()));
        List<Metric> metricList = devTools.send(Performance.getMetrics());

        cdpDriver.get("https://lambdatest.com");

        boolean success=false;
        for (Metric m : metricList) {
            System.out.println(m.getName() + " = " + m.getValue());
            success = true;
        }
        if (success) {
            markStatus("passed", "Performance metrics successfully fetched", cdpDriver);
        } else {
            markStatus("failed", "Unable to fetch Performance metrics", cdpDriver);
        }


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

  public static void markStatus(String status, String reason, WebDriver driver) {
    JavascriptExecutor jsExecute = (JavascriptExecutor) driver;
    jsExecute.executeScript("lambda-status=" + status);
    System.out.println(reason);
}
}
