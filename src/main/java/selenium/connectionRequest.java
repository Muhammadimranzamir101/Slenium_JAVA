package selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class connectionRequest {
    public static void main(String[] args) throws IOException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

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
                Assert.assertFalse(true);
            }
            else{
                System.out.println("The link with text '"+element.getText()+"' is working perfect.");
            }
            conn.disconnect();
        }
        driver.close();
    }
}
