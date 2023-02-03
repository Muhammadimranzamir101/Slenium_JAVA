package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Alerts {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        driver.findElement(By.id("name")).sendKeys("Imran");
        driver.findElement(By.cssSelector("#alertbtn")).click();
        String alertText = driver.switchTo().alert().getText();
        System.out.println(alertText);
        driver.switchTo().alert().accept();
        driver.findElement(By.cssSelector("#confirmbtn")).click();
        String confirmText = driver.switchTo().alert().getText();
        System.out.println(confirmText);
        driver.switchTo().alert().dismiss();

        driver.close();

    }
}
