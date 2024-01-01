package com.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.base.BaseSuiteSetup;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.drivers.DriverManager.getDriver;

public class Action extends BaseSuiteSetup{

    @BeforeClass
    public void setupTest(){
        getDriver ().navigate ()
                .to ("https://amazon.com");
    }

    @Test
    public void actionsTest() {
        WebDriver driver = getDriver();
        WebElement signIn = driver.findElement(By.cssSelector("#nav-link-accountList"));
        WebElement search = driver.findElement(By.cssSelector("#twotabsearchtextbox"));
        Actions a = new Actions(driver);

        //Hover
        a.moveToElement(signIn).build().perform();
        //Right Click
        a.contextClick(signIn).build().perform();
        //Enter Text in Capital
        a.click(search).keyDown(Keys.SHIFT).sendKeys("hello").doubleClick().build().perform();
    }
}
