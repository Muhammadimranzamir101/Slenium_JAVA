package com.tests;

import com.base.BaseSuiteSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.drivers.DriverManager.getDriver;

public class dropdown extends BaseSuiteSetup {
    @BeforeClass
    public void setupTest(){
        getDriver ().navigate ()
                .to ("https://rahulshettyacademy.com/dropdownsPractise/");
    }

    @Test
    public void dropdownTest() throws InterruptedException {
        WebDriver driver = getDriver();

        //Dropdown with Select tag
        Select dropdown = new Select(driver.findElement(By.name("ctl00$mainContent$DropDownListCurrency")));
        dropdown.selectByIndex(1);
        Assert.assertEquals("INR",dropdown.getFirstSelectedOption().getText());
        dropdown.selectByValue("AED");
        Assert.assertEquals("AED",dropdown.getFirstSelectedOption().getText());
        dropdown.selectByVisibleText("USD");
        Assert.assertEquals("USD",dropdown.getFirstSelectedOption().getText());

        //Dropdown without select tags
        String beforePassengerStr = driver.findElement(By.id("divpaxinfo")).getText();
        driver.findElement(By.id("divpaxinfo")).click();
        Thread.sleep(1000);
        int numPassenger = (int) Math.floor(Math.random()*5)+1;
        List<WebElement> elements = driver.findElements(By.cssSelector("[id*=\"Inc\"]"));
        int num = (int) Math.floor(Math.random()*elements.size());
        for(int i=0; i<4; i++)
        {
            elements.get(num).click();
        }
        String afterPassengerStr = driver.findElement(By.id("divpaxinfo")).getText();
        Assert.assertNotEquals(beforePassengerStr, afterPassengerStr);
        Thread.sleep(2000);

        //Dynamic Dropdown

        String fromTextBefore = driver.findElement(By.cssSelector("input#ctl00_mainContent_ddl_originStation1_CTXT")).getAttribute("value");
        String toTextBefore = driver.findElement(By.cssSelector("input#ctl00_mainContent_ddl_destinationStation1_CTXT")).getAttribute("value");
        driver.findElement(By.cssSelector("input#ctl00_mainContent_ddl_originStation1_CTXT")).click();
        driver.findElement(By.xpath("//a[@value=\"AMD\"]")).click();
        String fromTextAfter = driver.findElement(By.cssSelector("input#ctl00_mainContent_ddl_originStation1_CTXT")).getAttribute("value");
        Assert.assertNotEquals(fromTextBefore, fromTextAfter);
        Thread.sleep(1000);

        driver.findElement(By.xpath("(//a[@value=\"MAA\"])[2]")).click();
        String toTextAfter = driver.findElement(By.cssSelector("input#ctl00_mainContent_ddl_destinationStation1_CTXT")).getAttribute("value");
        Assert.assertNotEquals(toTextBefore, toTextAfter);

        //Auto-Suggestive

        driver.findElement(By.cssSelector("input#autosuggest")).sendKeys("pa");
        Thread.sleep(3000);
        List <WebElement> options = driver.findElements(By.cssSelector("#ui-id-1 li"));
        Actions actions = new Actions(driver);

        for(WebElement option : options)
        {
            if(option.getText().equalsIgnoreCase("pakistan"))
            {
                actions.moveToElement(option).click().perform();
                break;
            }
        }

        Thread.sleep(5000);
    }
}