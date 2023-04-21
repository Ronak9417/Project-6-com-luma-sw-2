package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SaleTest extends BaseTest {
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
    public void verifyTheTotalItemsDisplayedOnTheWomensJacketsPage(){
        //Find Element of sign in link and click
        WebElement signInLink = driver.findElement(By.xpath("//body//div//header//div//div//ul//li[@data-label='or']//a"));
        signInLink.click();
        //Enter Email in user field
        driver.findElement(By.id("email")).sendKeys("adminpatel01@yahoo.com");
        //Enter password in password field
        driver.findElement(By.xpath("//input[@name='login[password]']")).sendKeys("Admin123");
        //Click on sign in button
        driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
        //Click on ‘Sale’ Menu tab
        driver.findElement(By.xpath("//span[text() = 'Sale']")).click();
        //Find sale link element and  click on sale link
        driver.findElement(By.xpath("(//li[@class = 'item']//a[text() = 'Jackets'])[1]")).click();

        //Find 'Jackets' text element and verify 'Jackets' text element
        String expectedText = "Jackets";
        WebElement actualTextDisplayed = driver.findElement(By.xpath("(//h1[@class = 'page-title']" +
                "//span[text() = 'Jackets'])[1]"));
        String actualText = actualTextDisplayed.getText();
        Assert.assertEquals("Jackets text not displayed.", actualText, expectedText);

        //Count the Total Item Displayed on Page and print the name of all items into console.
        int expectedItemsDisplayed = 12;
        List<WebElement> items = driver.findElements(By.xpath("//strong[@class = " +
                "'product name product-item-name']//a"));
        int actualItemsDisplayed = items.size();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("All 12 items displayed");
        System.out.println("-----------------------------------------------------------------------");
        for (int i = 1; i <= items.size(); i++) {
            String itemNames = driver.findElement(By.xpath("(//strong[@class = " +
                    "'product name product-item-name']//a)[" + i + "]")).getText();
            System.out.println(itemNames);
        }

        Assert.assertEquals("Expected number of products are not displayed", expectedItemsDisplayed,
                actualItemsDisplayed);

    }



}
