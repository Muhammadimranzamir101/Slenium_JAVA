package com.tests;

import com.base.BaseSuiteSetup;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.drivers.DriverManager.getDriver;

public class scroll extends BaseSuiteSetup {
    @BeforeClass
    public void setupTest(){
        getDriver ().navigate ()
                .to ("https://www.rahulshettyacademy.com/AutomationPractice/");
    }
    @Test
    public void scrollTest() {
        WebDriver driver = getDriver();
        driver.manage().window().maximize();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,400)");
        js.executeScript("document.querySelector('.tableFixHead').scrollTop=500");

    }
}
