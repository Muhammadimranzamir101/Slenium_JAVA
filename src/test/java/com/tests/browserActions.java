package com.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class browserActions {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://google.com");
        driver.navigate().to("https://amazon.com");
        driver.navigate().back();
        driver.navigate().forward();
        driver.findElement(By.cssSelector("input[aria-label=\"Search Amazon\"]")).sendKeys("Books");
        driver.navigate().refresh();
        driver.close();
    }
}
