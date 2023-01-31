package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class checkboxes {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        System.out.println("Total checkboxes : "+driver.findElements(By.cssSelector("input[type=\"checkbox\"]")).size());

        System.out.println("Is SeniorCitizenSelected checkbox enabled ? "+driver.findElement(By.cssSelector("input[id*=\"SeniorCitizenDiscount\"]")).isEnabled());
        System.out.println("Is SeniorCitizenSelected ? "+driver.findElement(By.cssSelector("input[id*=\"SeniorCitizenDiscount\"]")).isSelected());
        driver.findElement(By.cssSelector("input[id*=\"SeniorCitizenDiscount\"]")).click();
        System.out.println("Is SeniorCitizenSelected ? "+driver.findElement(By.cssSelector("input[id*=\"SeniorCitizenDiscount\"]")).isSelected());

        driver.close();

    }
}
