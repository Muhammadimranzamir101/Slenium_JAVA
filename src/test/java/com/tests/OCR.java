package com.tests;

import com.base.BaseSuiteSetup;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static com.drivers.DriverManager.getDriver;

public class OCR extends BaseSuiteSetup {
    @BeforeClass
    public void setupTest(){
        getDriver ().navigate ()
                .to ("https://www.irctc.co.in/nget/train-search");
    }

    @Test
    public void OCRTest() throws InterruptedException, TesseractException, IOException {

        System.setProperty("jna.library.path", "/usr/local/lib");

        WebDriver driver = getDriver();
        driver.manage().window().maximize();

        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(Duration.ofSeconds(100))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class);

            wait.until(Driver -> Driver.findElement(By.cssSelector("a[aria-label*='Login']")));

            driver.findElement(By.cssSelector("a[aria-label*='Login']")).click();
            Thread.sleep(5000);

            File captchaImage = driver.findElement(By.cssSelector("img[class='captcha-img']")).getScreenshotAs(OutputType.FILE);
            String path = System.getProperty("user.dir") + "/screenshot/captcha.png";
            System.out.println("File Path: " + path);
            FileHandler.copy(captchaImage, new File(path));

            ITesseract img = new Tesseract();
            img.setDatapath("/Users/mimran/Downloads/Automation_Workspace/Slenium_JAVA/tessdata/");
            img.setTessVariable("preserve_interword_spaces", "1");
            img.setLanguage("eng");
            String captchaText = img.doOCR(new File(path));
            driver.findElement(By.id("captcha")).sendKeys(captchaText);
            Thread.sleep(5000);
            driver.quit();
        }
        catch (Exception e){
            e.printStackTrace();
            driver.quit();
        }

    }
}
