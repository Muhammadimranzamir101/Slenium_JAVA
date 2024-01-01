package com.tests;

import com.base.BaseSuiteSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.drivers.DriverManager.getDriver;

public class browserActions extends BaseSuiteSetup {

    @BeforeClass
    public void setupTest(){
        getDriver ().navigate ()
                .to ("https://amazon.com");
    }

    @Test
    public void browserActionsTest() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://google.com");
        driver.navigate().to("https://amazon.com");
        driver.navigate().back();
        driver.navigate().forward();
        driver.findElement(By.cssSelector("input[aria-label=\"Search Amazon\"]")).sendKeys("Books");
        driver.navigate().refresh();
    }
}
