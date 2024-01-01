package com.tests;

import java.io.IOException;

import com.base.BaseSuiteSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import static com.drivers.DriverManager.getDriver;

public class childWindows extends BaseSuiteSetup {

    @BeforeClass
    public void setupTest(){
        getDriver ().navigate ()
                .to ("https://rahulshettyacademy.com/loginpagePractise/");
    }
    @Test
    public void childWindowsTest() throws InterruptedException {

        WebDriver driver = getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement link = driver.findElement(By.className("blinkingText"));
        link.click();

        //Getting All windows handles
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> id = windows.iterator();
//        for (Iterator<String> it = id; it.hasNext(); ) {
//            System.out.println(it.next());
//        }

        String parentId = id.next();
        System.out.println(parentId);
        String childId = id.next();
        System.out.println(childId);

        driver.switchTo().window(childId);

        String  text = driver.findElement(By.cssSelector("p[class=\"im-para red\"]")).getText();
        String username = text.split(" ")[4];
        System.out.println(username);

        driver.switchTo().window(parentId);

        driver.findElement(By.id("username")).sendKeys(username);

        Thread.sleep(5000);

    }
}
