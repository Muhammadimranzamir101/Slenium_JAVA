package com.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class authentication_2 {
    public static void main(String[] args) {

        WebDriver Driver = new ChromeDriver();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");

        JavascriptExecutor js = (JavascriptExecutor) Driver;
        String userAgent = (String) js.executeScript("return navigator.userAgent");

        chromeOptions.addArguments("--user-agent=" + userAgent);
        WebDriver driver = new ChromeDriver(chromeOptions);

        System.out.println("User Agent: "+userAgent);

        driver.get("https://accounts.google.com/o/oauth2/auth?"
                + "client_id=770671653516-bvce40ojavd5gptqimnq4p3bob3uotog.apps.googleusercontent.com"
                + "&redirect_uri=https://localhost:8083"
                + "&scope=https://www.googleapis.com/auth/calendar.readonly"
                + "&response_type=code");

        WebElement emailInput = driver.findElement(By.id("identifierId"));
        emailInput.sendKeys("testingsep5");
        driver.findElement(By.id("identifierNext")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("Mobile@1");

        driver.findElement(By.id("passwordNext")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("submit_approve_access")));
        driver.findElement(By.id("submit_approve_access")).click();

        String currentUrl = driver.getCurrentUrl();
        String authorizationCode = currentUrl.split("code=")[1];

        driver.quit();

        // Use the obtained authorization code to request an access token
        // and make authenticated API requests as needed
        System.out.println("Authorization Code: " + authorizationCode);
    }
}
