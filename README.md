# Gauge-Selenium-Sample
![Java](https://www.lambdatest.com/support/assets/images/og-images/Gauge-Automation-Tests.jpg)

### Prerequisites
1. Install and set environment variable for java.
    * Windows - https://www.oracle.com/java/technologies/downloads/
    * Linux - ```  sudo apt-get install openjdk-8-jre  ```
    * MacOS - Java should already be present on Mac OS X by default.
2 Install and set environment varibale for Maven.
    * Windows - https://maven.apache.org/install.html
    * Linux/ MacOS -  [Homebrew](http://brew.sh/) (Easier)
    ```
     install maven
    ```
3. Install Gauge from [here](https://docs.gauge.org/getting_started/installing-gauge.html?os=windows&language=javascript&ide=vscode).
    
### Run your First Test
1. Clone the Gauge-Selenium-Sample repository. 
```
git clone https://github.com/LambdaTest/gauge-selenium-sample
```
2. Next get into Gauge-Selenium-Sample folder, and import Lamabdatest Credentials. You can get these from lambdatest automation dashboard.
   <p align="center">
   <b>For Linux/macOS:</b>:
 
```
export LT_USERNAME="YOUR_USERNAME"
export LT_ACCESS_KEY="YOUR ACCESS KEY"
```
<p align="center">
   <b>For Windows:</b>

```
set LT_USERNAME="YOUR_USERNAME"
set LT_ACCESS_KEY="YOUR ACCESS KEY"
```

Step 3. Run the test.
```
$ mvn compile 
$ mvn clean install OR $ mvn test 
```

### Result of the Test
You can see the test running on LambdaTest [Automation Dashboard](https://automation.lambdatest.com/build)
![Automation Logs of LambdaTest](https://github.com/LambdaTest/gauge-selenium-sample/blob/master/Tutorial-Images/Automation%20Logs%20of%20LambdaTest.png?raw=true)


##  Testing Locally Hosted or Privately Hosted Projects

To help you perform cross browser testing of your locally stored web pages, LambdaTest provides an SSH(Secure Shell) tunnel connection with the name Lambda Tunnel. With Lambda Tunnel, you can test your locally hosted files before you make them live over the internet. You could even perform cross browser testing from different IP addresses belonging to various geographic locations. You can also use LambdaTest Tunnel to test web-apps and websites that are permissible inside your corporate firewall.

## About LambdaTest
[LambdaTest](https://www.lambdatest.com/) is a cloud based selenium grid infrastructure that can help you run automated cross browser compatibility tests on 2000+ different browser and operating system environments. LambdaTest supports all programming languages and frameworks that are supported with Selenium, and have easy integrations with all popular CI/CD platforms. It's a perfect solution to bring your [selenium automation testing](https://www.lambdatest.com/selenium-automation) to cloud based infrastructure that not only helps you increase your test coverage over multiple desktop and mobile browsers, but also allows you to cut down your test execution time by running tests on parallel.

## Additional Resources
##### [SeleniumHQ Documentation](http://www.seleniumhq.org/docs/)
##### [Gauge Documentation](https://docs.gauge.org/)
