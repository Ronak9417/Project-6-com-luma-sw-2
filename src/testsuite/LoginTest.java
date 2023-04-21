package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Random;

public class LoginTest extends BaseTest {

    String baseUrl = "https://magento.softwaretestingboard.com";

    @Before
    public void setup() {
        openBrowser(baseUrl);
    }

    @After
    public void teardown() {
        driver.close();
    }


    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() throws InterruptedException {

        //Find Element of sign in link and click
        WebElement signInLink = driver.findElement(By.xpath("//body//div//header//div//div//ul//li[@data-label='or']//a"));
        signInLink.click();
        //Enter Email in user field
        driver.findElement(By.id("email")).sendKeys("adminpatel01@yahoo.com");
        //Enter password in password field
        driver.findElement(By.xpath("//input[@name='login[password]']")).sendKeys("Admin123");
        //Click on sign in button
        driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
        //Verify the welcome text
        Thread.sleep(2000);
        String expectedSuccessFullLoginMessage = "Welcome";
        WebElement actualSuccessFullLoginElement = driver.findElement(By.xpath("(//span[@class='logged-in'])[1]"));
        String actualSuccessFullLoginMessage = actualSuccessFullLoginElement.getText();
        String[] actuleMessage = actualSuccessFullLoginMessage.split(",");

        Assert.assertEquals("Error Message",expectedSuccessFullLoginMessage,actuleMessage[0]);

    }

    @Test
    public void verifyTheErrorMessageWithInvalidCredentials() {
        //Click on sign in link
        WebElement signInLink = driver.findElement(By.linkText("Sign In"));
        signInLink.click();
        //Create everytime a different email while executing the test
        Random randomGenerator = new Random();// random generator class
        int randomNum = randomGenerator.nextInt(1000);

        //Find the 'Email' field element and enter 'Email' into field
        String userName = "adminpatel01";
        driver.findElement(By.id("email")).sendKeys(userName +
                randomNum + "@yahoo.com");
        //Enter password in password field
        driver.findElement(By.id("pass")).sendKeys("Admin123");
        //Click on sign in button
        driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
        //Verify the Welcome text
        String expectedErrorMessage = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
        WebElement actualErrorMessageElement = driver.findElement(By.xpath("//div[@class='message-error error message']"));
        String actualErrorMessage = actualErrorMessageElement.getText();
        Assert.assertEquals("Error Message", expectedErrorMessage, actualErrorMessage);

    }

    @Test
    public void userShouldLogOutSuccessfully() throws InterruptedException {

        //Find Element of sign in link and click
        WebElement signInLink = driver.findElement(By.xpath("//body//div//header//div//div//ul//li[@data-label='or']//a"));
        signInLink.click();
        //Enter Email in user field
        driver.findElement(By.id("email")).sendKeys("adminpatel01@yahoo.com");
        //Enter password in password field
        driver.findElement(By.xpath("//input[@name='login[password]']")).sendKeys("Admin123");
        //Click on sign in button
        driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
        //Verify the welcome text
        Thread.sleep(2000);
        String expectedSuccessFullLoginMessage = "Welcome";
        WebElement actualSuccessFullLoginElement = driver.findElement(By.xpath("(//span[@class='logged-in'])[1]"));
        String actualSuccessFullLoginMessage = actualSuccessFullLoginElement.getText();
        String[] actuleMessage = actualSuccessFullLoginMessage.split(",");

        Assert.assertEquals("Error Message",expectedSuccessFullLoginMessage,actuleMessage[0]);

        // click on down aero near welcome
        driver.findElement(By.xpath("(//button[@class='action switch'])[1]")).click();
        //Click on sign-out Button
        driver.findElement(By.xpath("//li[@class='authorization-link'][1]")).click();
        //Verify the text 'You are signed out'

        String expectedMsg = "You are signed out";
        WebElement actualMessageElement1 = driver.findElement(By.xpath("//span[@data-ui-id = " +
                "'page-title-wrapper']"));
        String actualMsg = actualMessageElement1.getText();
        Assert.assertEquals("The 'Signed out' message is not displayed", expectedMsg, actualMsg);

    }

}
