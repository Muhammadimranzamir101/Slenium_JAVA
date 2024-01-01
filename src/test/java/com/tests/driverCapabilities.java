package com.tests;

import com.base.BaseSuiteSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class driverCapabilities extends BaseSuiteSetup {
    @Test
    public void driverCapabilitiesTest(String[] args) throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors",
                "--start-maximized",
                "--incognito",
                "--disable-popup-blocking",
                "----disable-notifications", //disable alerts
                "--disable-infobars");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://expired.badssl.com/");
        String title = driver.getTitle();
        String message = driver.findElement(By.tagName("h1")).getText().replace("\n","");
        Assert.assertEquals(message, title);
        Thread.sleep(1000000);
    }
}
