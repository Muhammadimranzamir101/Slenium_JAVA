package com.tests;

import com.base.BaseSuiteSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

import static com.drivers.DriverManager.getDriver;

public class fluentWait extends BaseSuiteSetup {
    @BeforeClass
    public void setupTest(){
        getDriver ().navigate ()
                .to ("https://the-internet.herokuapp.com/dynamic_loading/1");
    }

    @Test
    public void fluentWait() {
        WebDriver driver = getDriver();
        driver.findElement(By.cssSelector("#start>button")).click();

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

        WebElement label = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                if(driver.findElement(By.cssSelector("[id='finish'] h4")).isDisplayed())
                {
                    return driver.findElement(By.cssSelector("[id='finish'] h4"));
                }
                else {
                    return null;
                }
            }
        });

        //WebElement label = wait.until(Driver -> Driver.findElement(By.cssSelector("[id='finish'] h4")));

        System.out.println("Text: "+label.getText());
    }
}
