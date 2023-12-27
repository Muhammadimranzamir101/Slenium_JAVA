package com.tests;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class childWindows {
    public static void main(String[] args) throws InterruptedException {

        WebDriver webkit = new FirefoxDriver();
        webkit.manage().window().maximize();
        webkit.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        webkit.get("https://rahulshettyacademy.com/loginpagePractise/");
        WebElement link = webkit.findElement(By.className("blinkingText"));
        link.click();

        //Getting All windows handles
        Set<String> windows = webkit.getWindowHandles();
        Iterator<String> id = windows.iterator();
//        for (Iterator<String> it = id; it.hasNext(); ) {
//            System.out.println(it.next());
//        }

        String parentId = id.next();
        System.out.println(parentId);
        String childId = id.next();
        System.out.println(childId);

        webkit.switchTo().window(childId);

        String  text = webkit.findElement(By.cssSelector("p[class=\"im-para red\"]")).getText();
        String username = text.split(" ")[4];
        System.out.println(username);

        webkit.switchTo().window(parentId);

        webkit.findElement(By.id("username")).sendKeys(username);

        Thread.sleep(5000);

        webkit.quit();
    }
}
