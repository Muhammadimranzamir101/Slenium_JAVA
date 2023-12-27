package com.tests;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class calendar {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.booking.com");
        driver.findElement(By.cssSelector("button[data-testid*=\"start\"]")).click();

        selectRoundTrip(driver, 15,20);
        //selectReturnDateAfterNDays(driver,5);
        Thread.sleep(5000);
        driver.close();
    }

    public static void selectRoundTrip(@NotNull WebDriver driver, int startDate, int endDate)
    {
        List<WebElement> totalDays = driver.findElements(By.xpath("//td/span[not(contains(@class,\"f5774da2d4\"))]"));

        for(WebElement day : totalDays)
        {
            String  date = day.getText();
            if(Integer.parseInt(date) == startDate)
                day.click();
            if(Integer.parseInt(date) == endDate)
            {
                day.click();
                break;
            }
        }
    }

    public static void selectReturnDateAfterNDays(WebDriver driver, int day)
    {
        driver.findElement(By.xpath("(//td/span[not(contains(@class,\"f5774da2d4\"))])["+1+"]")).click();
        driver.findElement(By.xpath("(//td/span[not(contains(@class,\"f5774da2d4\"))])["+(day+1)+"]")).click();
    }
}
