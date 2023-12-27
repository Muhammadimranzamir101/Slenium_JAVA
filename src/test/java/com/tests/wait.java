package com.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class wait {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        WebDriverWait w = new WebDriverWait(driver,Duration.ofMillis(5000)); //Explicit Wait
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // Implicit Wait
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        driver.findElement(By.id("inputUsername")).sendKeys("imran");
        driver.findElement(By.name("inputPassword")).sendKeys("Mobile@1");
        driver.findElement(By.className("signInBtn")).click();
        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.error")));
        System.out.println(driver.findElement(By.cssSelector("p.error")).getText());
        driver.findElement(By.linkText("Forgot your password?")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@placeholder=\"Name\"]")).sendKeys("abc");
        driver.findElement(By.xpath("//input[@placeholder=\"Email\"]")).sendKeys("abc@nisum.com");
        driver.findElement(By.xpath("//input[@placeholder=\"Phone Number\"]")).sendKeys("123456789");
        driver.findElement(By.xpath("//button[text()=\"Reset Login\"]")).click();
        String msg = driver.findElement(By.cssSelector(".infoMsg")).getText();
        String strArr[] = msg.split("'");
        String password = strArr[1];
        driver.findElement(By.cssSelector("button[class*=\"login\"]")).click();
        driver.findElement(By.id("inputUsername")).sendKeys(RandomStringUtils.random(5, true, true));
        driver.findElement(By.name("inputPassword")).sendKeys(password);
        Thread.sleep(1000);
        driver.findElement(By.id("chkboxOne")).click();
        driver.findElement(By.id("chkboxTwo")).click();
        driver.findElement(By.className("signInBtn")).click();
        Thread.sleep(1000);
        System.out.println(driver.findElement(By.cssSelector("div p")).getText());
        Assert.assertEquals(driver.findElement(By.cssSelector("div p")).getText(),"You are successfully logged in.");
        Thread.sleep(5000);
        driver.close();

    }
}
