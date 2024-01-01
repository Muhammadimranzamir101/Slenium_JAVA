package com.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.drivers.DriverManager.getDriver;

public class miniDriver {
    @BeforeClass
    public void setupTest(){
        getDriver ().navigate ()
                .to ("https://www.rahulshettyacademy.com/AutomationPractice/");
    }

    @Test
    public void miniDriverTest() throws InterruptedException {
        WebDriver driver = getDriver();
        driver.manage().window().maximize();

        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Number of links in the page : "+links.size());

        WebElement footerDriver = driver.findElement(By.id("gf-BIG"));
        List<WebElement> footerLinks = footerDriver.findElements(By.tagName("a"));
        System.out.println("Number of links in the footer is : "+footerLinks.size());

        Assert.assertTrue(links.size()>footerLinks.size(),"Asserting the links count in page " +
                "driver scope and footer driver scope");

        WebElement columnDriver = footerDriver.findElement(By.xpath("//table/tbody/tr/td[1]/ul"));
        List<WebElement> columnLinks = columnDriver.findElements(By.tagName("a"));

        Actions a = new Actions(driver);
        for(int i=1; i<columnLinks.size(); i++)
        {
            a.keyDown(Keys.COMMAND).build().perform();
            columnDriver.findElements(By.tagName("a")).get(i).click();
            Thread.sleep(2000);
        }

        Set<String> windows = driver.getWindowHandles();
        Iterator<String> id = windows.iterator();

        while (id.hasNext()){
            driver.switchTo().window(id.next());
            System.out.println(driver.getTitle());
            Thread.sleep(2000);        }

        driver.quit();
    }
}
