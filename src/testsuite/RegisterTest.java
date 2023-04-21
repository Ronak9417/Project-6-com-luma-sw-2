package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Random;

public class RegisterTest extends BaseTest {

    String baseUrl = "https://magento.softwaretestingboard.com/ ";
    String email;

    @Before

    public void setUp(){
        openBrowser(baseUrl);
    }

    @Test

    public void verifyThatSignInPageDisplay(){
        //Find Create an Account and click on the ‘Create an Account’ link
        driver.findElement(By.xpath("//div[@class='panel header']//a[text()='Create an Account']")).click();

        String expectMessage ="Create New Customer Account";

        //Find the text element and get the text
        WebElement actualMessageElement = driver.findElement(By.xpath("//span[@class='base']"));
        String actualMessage = actualMessageElement.getText();

        // Validate actual and expected message
        Assert.assertEquals("Expected result is not matching", expectMessage, actualMessage);

    }

    @Test
    public void userShouldRegisterAccountSuccessfully(){

        //Find Create an Account and click on the ‘Create an Account’ link
        driver.findElement(By.xpath("//div[@class='panel header']//a[text()='Create an Account']")).click();

        //Enter First Name
        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("admin");

        //Enter Last Name
        driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("patel");

        //Click on checkbox Sign Up for Newsletter
        driver.findElement(By.xpath("//input[@id='is_subscribed']")).click();

        //Enter Email
        WebElement emailField = driver.findElement(By.xpath("//input[@id='email_address']"));
        emailField.click();
        Random randomGenerator = new Random();// random generator class
        int randomInt = randomGenerator.nextInt(1000);
        email="username"+ randomInt +"@yahoo.com";
        emailField.sendKeys(email);// creating random email generator

        //Enter Password
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Admin123");

        //Enter Confirm Password
        driver.findElement(By.xpath("//input[@id='password-confirmation']")).sendKeys("Admin123");

        //Click on Create an Account button
        driver.findElement(By.xpath("//button[@title='Create an Account']")).click();

        //  verify the text Thank you for registering with Main Website Store.
        String expectMessage ="Thank you for registering with Main Website Store.";

        //Find the text element and get the text
        WebElement actualTextMessageElement = driver.findElement(By.xpath("//div[@class = 'message-success success message']"));
        String actualMessage = actualTextMessageElement.getText();

        // Validate actual and expected message
        Assert.assertEquals("Create New Customer Account message not displayed",expectMessage,actualMessage);

        //Find the down aero near 'Wel come' element and click on down aero element
        driver.findElement(By.xpath("//button[@data-action='customer-menu-toggle'][1]")).click();

        //Find the 'Sign out' link element and click on 'Sign out' link
        driver.findElement(By.xpath("//li[@class ='authorization-link'][1]")).click();


        //Find the 'You are signed out' text element and Verify the text ‘You are signed out’
        String expectedMsg = "You are signed out";
        WebElement actualMessageElement1 = driver.findElement(By.xpath("//span[@data-ui-id = " +
                "'page-title-wrapper']"));
        String actualMsg = actualMessageElement1.getText();
        Assert.assertEquals("The 'Signed out' message is not displayed", expectedMsg, actualMsg);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }


}
