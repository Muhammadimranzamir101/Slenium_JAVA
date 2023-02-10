package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class miniDriver {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");

        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Number of links in the page : "+links.size());

        WebElement footerDriver = driver.findElement(By.id("gf-BIG"));
        List<WebElement> footerLinks = footerDriver.findElements(By.tagName("a"));
        System.out.println("Number of links in the footer is : "+footerLinks.size());

        Assert.assertTrue(links.size()>footerLinks.size(),"Asserting the links count in page " +
                "driver scope and footer driver scope");

        WebElement columnDriver = footerDriver.findElement(By.xpath("//table/tbody/tr/td[1]/ul"));
        List<WebElement> columnLinks = columnDriver.findElements(By.tagName("a"));

        Actions a = new Actions(driver);
        for(int i=1; i<columnLinks.size(); i++)
        {
            a.keyDown(Keys.COMMAND).build().perform();
            columnDriver.findElements(By.tagName("a")).get(i).click();
            Thread.sleep(1000);
        }

        driver.quit();
    }
}
