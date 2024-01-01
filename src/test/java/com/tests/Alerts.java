package com.tests;

import com.base.BaseSuiteSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.drivers.DriverManager.getDriver;

public class Alerts extends BaseSuiteSetup {

    @BeforeClass
    public void setupTest(){
        getDriver ().navigate ()
                .to ("https://rahulshettyacademy.com/AutomationPractice/");
    }

    @Test
    public void alertTest() {
        WebDriver driver = getDriver();

        driver.findElement(By.id("name")).sendKeys("Imran");
        driver.findElement(By.cssSelector("#alertbtn")).click();
        String alertText = driver.switchTo().alert().getText();
        System.out.println(alertText);
        driver.switchTo().alert().accept();
        driver.findElement(By.cssSelector("#confirmbtn")).click();
        String confirmText = driver.switchTo().alert().getText();
        System.out.println(confirmText);
        driver.switchTo().alert().dismiss();

    }
}
