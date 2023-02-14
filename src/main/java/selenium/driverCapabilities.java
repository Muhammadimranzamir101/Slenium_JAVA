package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

public class driverCapabilities {
    public static void main(String[] args) throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors",
                "--start-maximized",
                "--incognito",
                "--disable-popup-blocking",
                "----disable-notifications", //disable alerts
                "--disable-infobars");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://expired.badssl.com/");
        String title = driver.getTitle();
        String message = driver.findElement(By.tagName("h1")).getText().replace("\n","");
        Assert.assertEquals(message, title);
        Thread.sleep(1000000);
        driver.quit();
    }
}
