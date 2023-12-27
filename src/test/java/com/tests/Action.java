package com.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Action {
    public static void main(String[] args) {
        WebDriver chrome = new ChromeDriver();
        chrome.get("https://amazon.com");
        WebElement signIn = chrome.findElement(By.cssSelector("#nav-link-accountList"));
        WebElement search = chrome.findElement(By.cssSelector("#twotabsearchtextbox"));
        Actions a = new Actions(chrome);

        //Hover
        a.moveToElement(signIn).build().perform();
        //Right Click
        a.contextClick(signIn).build().perform();
        //Enter Text in Capital
        a.click(search).keyDown(Keys.SHIFT).sendKeys("hello").doubleClick().build().perform();
    }
}
