package com.tests;

import com.base.BaseSuiteSetup;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

import static com.drivers.DriverManager.getDriver;

public class extensions extends BaseSuiteSetup {
    @Test
    public void extensionInstallation() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized","--allow-insecure-localhost");
        options.addArguments("--remote-allow-origins=*");
        options.addExtensions(new File("Files/Google-Translate.crx")); //http://crxextractor.com/
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("ChromeOptions.CAPABILITY",options); //Key value
        options.merge(capabilities);
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://google.com");
        driver.navigate().to("chrome://extensions/");
        JavascriptExecutor js = driver;
        String title = (String) js.executeScript("return document.querySelector(\"body extensions-manager\").shadowRoot.querySelector('#items-list').shadowRoot.querySelector('#aapbdbdomjkkjkaonfhkkikfgjllcleb').shadowRoot.querySelector('#name').textContent");
        Assert.assertEquals(title, "Google Translate");
        Thread.sleep(5000);
    }
}
