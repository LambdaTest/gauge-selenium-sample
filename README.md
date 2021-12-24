# Gauge Tutorial For Selenium Test Automation

![LambdaTest Logo](https://www.lambdatest.com/images/logo.svg)

![Guage Framework](https://github.com/LambdaTest/gauge-selenium-sample/blob/master/Tutorial-Images/Gauge%20Framework.png?raw=true)

Gauge is an open source test automation framework that takes the pain out of writing and maintaining regression or acceptance test suites. Gauge is cross-platform and open-source. It supports multiple languages including Ruby, Java, C#, Python and Javascript, and it has upcoming support for other languages — like Golang — as well.

## Prerequisites for  Gauge

### Install IntelliJ
Download Intellij (IDE)  for  writing code script or any IDE of your choice: [https://www.jetbrains.com/idea/download/](https://www.jetbrains.com/idea/download/)

### Install Gauge
You can directly install gauge framework from [here](https://docs.gauge.org/getting_started/installing-gauge.html)

#### Installing Gauge Framework in Mac:
Installing Gauge framework in macOS using Homebrew with below commands:

```
brew update
brew install gauge
```

#### Installing Gauge Framework in Linux:
Step 1: Add GPG  key through the terminal with below command:

```
sudo apt-key adv --keyserver hkp://pool.sks-keyservers.net --recv-keys 023EDB0B
```

Step 2: Add Gauge framework repository to the list.
```
echo deb https://dl.bintray.com/gauge/gauge-deb nightly main | sudo tee -a /etc/apt/sources.list
```

Step 3: Install Gauge framework with below commands:
```
sudo apt-get update
sudo apt-get install gauge
```

### Installing Gauge Framework in Windows:
To download Gauge on Windows, please use the link: [https://docs.gauge.org/getting_started/installing-gauge.html](https://docs.gauge.org/getting_started/installing-gauge.html) and select the language say Java for which you would like to install Gauge on your system.
When Gauge gets installed successfully, we’ll set the path for it in the environment variables for it like below:

![Environment Variable](https://github.com/LambdaTest/gauge-selenium-sample/blob/master/Tutorial-Images/Environment%20Variable.png?raw=true)

## Maven Installation

You can download the maven latest version using the link: [Download latest version of Maven](http://maven.apache.org/download.cgi).
After the installation is over, you can then configure maven by setting **MAVEN_HOME in the environment variables**:
Right click on MyComputer -> properties -> Advanced System Settings -> Environment variables -> click new button
Now add MAVEN_HOME in variable name and path of maven in variable value. It must be the home directory of maven i.e. outer directory of bin. For example: E:\apache-maven-3.1.1 .It is displayed below, after which, we need to click on **OK** button.

![Maven Installation](https://github.com/LambdaTest/gauge-selenium-sample/blob/master/Tutorial-Images/Maven%20Installation.png?raw=true)

**Note**: Click on the new tab if the path is not set, then set the path of the maven. If it is set, edit the path and append the path of maven.
Maven installation can be verified by running below command in the cmd/terminal:

```
mvn −version
```

Now it will display the version of maven and jdk including the maven home and java_home. Let's see the output:

![Command Prompt](https://github.com/LambdaTest/gauge-selenium-sample/blob/master/Tutorial-Images/Command%20Prompt.png?raw=true)

## Install Gauge Dependencies Through pom.xml:

Here we have installed two maven dependencies, one is for gauge java and the other one is for gauge plugin support.

```
<dependencies>
        	<dependency>
            <groupId>com.thoughtworks.gauge</groupId>
            <artifactId>gauge-java</artifactId>
            <version>0.7.2</version>
            <scope>test</scope>
  </dependency>

 	 <plugin>
                <groupId>com.thoughtworks.gauge.maven</groupId>
                <artifactId>gauge-maven-plugin</artifactId>
                <version>1.3.3</version>
	 </plugin>

```

## Setting LambdaTest Credentials:

In order to run the gauge selenium sample on the grid, you’d need to export the environment variables for the username and access key
Set LambdaTest username and access key in environment variables. It can be obtained from [LambdaTest dashboard](https://automation.lambdatest.com/).

Example:
<p align="center">
   <b>For linux/mac</b>:
   
   ```
   $export LT_USERNAME="YOUR_USERNAME"
                   $export LT_ACCESS_KEY="YOUR ACCESS KEY"
   ```
</p>
<p align="center">
   <b>For Windows</b>:
   
   ```
   $ set LT_USERNAME="YOUR_USERNAME"
                  $ set LT_ACCESS_KEY="YOUR ACCESS KEY"
   ```
</p>

## Setting up your project in Intellij IDE

We will create a project directory named gauge-selenium-sample to run our tests either through cmd or through Intellij. Alternatively, we can also import the sample code from the github repository: [https://github.com/LambdaTest/gauge-selenium-sample](https://github.com/LambdaTest/gauge-selenium-sample)

For a better coding experience we can make use of IntelliJ IDE to create our first script. Start with creating a maven project with below information.

Step1: Navigate to File and select New Project.

Step2 : Project Type as maven should be selected.

Step 3: You need to select the below mentioned name:
"com.thoughtworks.gauge.maven:gauge-maven-plugin"
This would help you to generate an archetype.

Step 4: If you don’t see the archetype mentioned above, add Archetype by entering below info.
GroupId: “Samplegauge”
ArtifactId: “SampleJavaGauge”

Step 5: After selecting the archetype, you need to click on next. By doing so, you will be providing the Artifact ID & GroupID for your respective maven project.
GroupId: com.thoughtworks.gauge.maven
ArtifactId: SampleJavaGauge

Version: 1.0-SNAPSHOT

Step 6: Before you click on next for finishing the process, make sure that you verify the Maven settings thoroughly.

Once you have successfully created a project it would look something as below.

![Project In Intellij IDE](https://github.com/LambdaTest/gauge-selenium-sample/blob/master/Tutorial-Images/Project%20In%20Intellij%20IDE.png?raw=true)

## Executing Gauge Java Selenium Sample Script

**Test Scenario**:

In the sample, we’re running the code to search a keyword on Google and check if the search results are displayed as expected. This can be done in both single or parallel execution. Parallel execution would be more convenient as it will reduce execution time for the tests as multiple tests can run simultaneously. We would have to create a specification file for searching a keyword in google and if results are displayed as expected.

Specification heading in the specs file would be denoted with ‘#’ notation as defined in our project.

Example: #Getting Started with Gauge

```
#Getting Started with Gauge
=====================
This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
   
Scenario Heading
----------------
* User is on Google home page
* User search for "lambdatest login"
* Verify the page title
```

Now, we would need to pass the capabilities and the credentials to run the test using DriverFactory. Following is the script for your reference:

```
package driver.driver;

import com.thoughtworks.gauge.AfterSpec;
import com.thoughtworks.gauge.BeforeSpec;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

   // Get a new WebDriver Instance.
   // There are various implementations for this depending on browser/OS. The required browser/OS can be set as an environment variable.
   // Refer http://getgauge.io/documentation/user/current/managing_environments/README.html

   private static final String username = "your_LT_username";
   private static final String accesskey = "your_LT_accesskey";
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
```

In the above driver factory code, we’ve used the desired capabilities. These capabilities we can generate using the [capability generator](https://www.lambdatest.com/capabilities-generator/) of LambdaTest.

The next step is to create a step implementation file, with below-mentioned code to define acceptance tests as defined in specification files.

```
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
```

The next step now is to define the configuration to run the test suite in browsers. To perform this, we would have to give the browser specification. The Browser specification could be provided in env folder’s default.properties file as

**BROWSER** = CHROME
**BROWSER_VERSION** = latest
**PLATFORM** = Windows 10

![env Folder- Browser Specification](https://github.com/LambdaTest/gauge-selenium-sample/blob/master/Tutorial-Images/env%20Folder-%20Browser%20Specification.png?raw=true)

We are all set  with the script structure, and now need to run the file from the terminal or command prompt  to initiate the test on the LambdaTest platform.

The number of browser specifications defined in the env file will define the number of tests executed in parallel. In case only one specification is defined, the test will run single, else parallel execution will occur with different configurations.

### Running the Test:

We can provide the maven command below with conditions as below. Make sure you run the command in the same path where your project is configured.

```
$ mvn compile 
$ mvn clean install OR $ mvn test 
```

Now it’s time to enter the command for executing the test on desired configuration

```
C:\Users\LenovoL430\Desktop\TestScripts\gauge-selenium-sample>mvn test
```

Once execution has been successfully completed you can verify the status of the test in the terminal.

![Terminal Of Runnig Test](https://github.com/LambdaTest/gauge-selenium-sample/blob/master/Tutorial-Images/Terminal%20Of%20Runnig%20Test.png?raw=true)

## Parallel test execution:

Gauge framework do support parallel test execution on the LambdaTest grid. This not only saves overall time for the tester to perform execution of the test, but also improves the overall turnaround time for the test on the grid. Since LambdaTest supports parallel execution of tests, it is well suited on the platform.

To perform parallel testing, more than one browser specifications need to be updated in the env folder of the project. For instance, we have defined specifications for Chrome, Firefox, Safari and Edge in the below screenshot. Once the test is executed, all parallel browser specifications will hit on the LambdaTest grid and execute simultaneously to achieve parallelism.

![Parallel Test env Folder](https://github.com/LambdaTest/gauge-selenium-sample/blob/master/Tutorial-Images/Parallel%20Test%20env%20Folder.png?raw=true)

In case you’d need to chuck out test for any browser, you would need to either delete the folder or comment out the code in it.

Here is the snapshot for the parallel tests running successfully on the platform:

![Automation Logs of LambdaTest](https://github.com/LambdaTest/gauge-selenium-sample/blob/master/Tutorial-Images/Automation%20Logs%20of%20LambdaTest.png?raw=true)

After completion of all test cases in  all the parallel tests , assertions passed and test executed successfully on .With the video provided for each test , test cases would be verified.

## Monitoring your test execution:

The gauge framework also supports report generation. The report generated can be viewed in the reports folder with graphical representation. The example for the one is mentioned below – **‘gauge-selenium-sample\reports\html-report’**.

![Gauge Sample Reports](https://github.com/LambdaTest/gauge-selenium-sample/blob/master/Tutorial-Images/Gauge%20Sample%20Reports.png?raw=true)

Alternatively, in LambdaTest automation dashboard, we can look up the test details in much details such as test pass/fail status, command logs, network logs, visual logs, video recordings etc.

## About LambdaTest

[LambdaTest](https://www.lambdatest.com/) is a cloud based selenium grid infrastructure that can help you run automated cross browser compatibility tests on 2000+ different browser and operating system environments. LambdaTest supports all programming languages and frameworks that are supported with Selenium, and have easy integrations with all popular CI/CD platforms. It's a perfect solution to bring your [selenium automation testing](https://www.lambdatest.com/selenium-automation) to cloud based infrastructure that not only helps you increase your test coverage over multiple desktop and mobile browsers, but also allows you to cut down your test execution time by running tests on parallel.

### Resources

##### [SeleniumHQ Documentation](http://www.seleniumhq.org/docs/)
##### [Gauge Documentation](https://docs.gauge.org/)
