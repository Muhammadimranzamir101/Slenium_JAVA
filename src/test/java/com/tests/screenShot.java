package com.tests;

import com.base.BaseSuiteSetup;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static com.drivers.DriverManager.getDriver;

public class screenShot extends BaseSuiteSetup {
    @BeforeClass
    public void setupTest(){
        getDriver ().navigate ()
                .to ("https://google.com");
    }
    @Test
    public void screenShotTest() throws IOException {
        WebDriver driver = getDriver();
        int num = (int) Math.floor(Math.random()*10000);
        String path = "/Users/mimran/Documents/Screenshots/screenshot";
        String fileName = path+ num;
        takeScreenShot(driver, fileName);
        takeScreenShot(driver, path+(int) Math.floor(Math.random()*10000));
    }

    public static void takeScreenShot(WebDriver driver, String fileName){
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        File targetFile = new File(fileName + ".png");

        try {
            FileUtils.copyFile(sourceFile, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
