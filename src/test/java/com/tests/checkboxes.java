package com.tests;

import com.base.BaseSuiteSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.drivers.DriverManager.getDriver;

public class checkboxes extends BaseSuiteSetup {

    @BeforeClass
    public void setupTest(){
        getDriver ().navigate ()
                .to ("https://rahulshettyacademy.com/dropdownsPractise/");
    }

    @Test
    public void checkBoxTest() {
        WebDriver driver = getDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        System.out.println("Total checkboxes : "+driver.findElements(By.cssSelector("input[type=\"checkbox\"]")).size());

        System.out.println("Is SeniorCitizenSelected checkbox enabled ? "+driver.findElement(By.cssSelector("input[id*=\"SeniorCitizenDiscount\"]")).isEnabled());
        System.out.println("Is SeniorCitizenSelected ? "+driver.findElement(By.cssSelector("input[id*=\"SeniorCitizenDiscount\"]")).isSelected());
        driver.findElement(By.cssSelector("input[id*=\"SeniorCitizenDiscount\"]")).click();
        System.out.println("Is SeniorCitizenSelected ? "+driver.findElement(By.cssSelector("input[id*=\"SeniorCitizenDiscount\"]")).isSelected());

    }
}
