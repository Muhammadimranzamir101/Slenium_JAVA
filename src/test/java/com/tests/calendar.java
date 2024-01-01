package com.tests;

import com.base.BaseSuiteSetup;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.drivers.DriverManager.getDriver;

public class calendar extends BaseSuiteSetup {

    @BeforeClass
    public void setupTest(){
        getDriver ().navigate ()
                .to ("https://www.booking.com");
    }
    @Test
    public void calendarTest() throws InterruptedException {
        WebDriver driver = getDriver();
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("button[data-testid*=\"start\"]")).click();

        selectRoundTrip(driver, 15,20);
        //selectReturnDateAfterNDays(driver,5);
        Thread.sleep(5000);
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
