package com.tests;
import com.base.BaseSuiteSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import static com.drivers.DriverManager.getDriver;

public class connectionRequest extends BaseSuiteSetup {

    @BeforeClass
    public void setupTest(){
        getDriver ().navigate ()
                .to ("https://rahulshettyacademy.com/AutomationPractice/");
    }

    @Test
    public void connectionRequestTest() throws IOException {
        WebDriver driver = getDriver();
        List<WebElement> elements = driver.findElements(By.cssSelector("table[class='gf-t'] a"));

        for(WebElement element : elements)
        {
            String link = element.getAttribute("href");
            HttpURLConnection conn = (HttpURLConnection) new URL(link).openConnection();
            conn.setRequestMethod("HEAD");
            conn.connect();
            int code = conn.getResponseCode();
            if(code>400){
                System.out.println("The link with text '"+element.getText()+"' is broken and status code is: "+code);
                conn.disconnect();
            }
            else{
                System.out.println("The link with text '"+element.getText()+"' is working perfect.");
            }
            conn.disconnect();
        }
    }
}
