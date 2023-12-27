package com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

public class Assertion {
    public static void main(String[] args) {


        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://tutorialshut.com/");
        String ActualTitle = driver.getTitle();
        String ExpectedTitle = "Tutorials Hut -Free Tutorials and Software Testing material";

        //assertEquals
        Assert.assertEquals(ActualTitle, ExpectedTitle);

        //AssertNotEquals
        Assert.assertNotEquals(ActualTitle, ExpectedTitle+"s");

        //AssertTrue
        Assert.assertTrue(ActualTitle.equals(ExpectedTitle));

        //AssertFalse
        Assert.assertFalse(ActualTitle.equals(ExpectedTitle+" "),"Object is null");

        //AssertNull
        Object obj = new Object();
        obj = null;
        Assert.assertNull(obj);

        //AssertNotNull
        Map map = new HashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        Assert.assertNotNull(map, "Object is not null");

        //SoftAssert
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals("Selenium", "Selenium", "1st Soft assert passed");
        softAssert.assertTrue("Selenium".equals("selenium"), "2nd Soft assert failed");
        softAssert.assertTrue("Testing".equals("testingggg"), "3rd soft assert failed");

        softAssert.assertAll();

        driver.close();

    }
}
