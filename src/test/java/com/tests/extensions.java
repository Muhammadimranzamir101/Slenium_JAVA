package com.tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.io.File;
import java.time.Duration;

public class extensions {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized","--allow-insecure-localhost");
        options.addExtensions(new File("/Users/mimran/Downloads/extension_2_0_12_0.crx")); //http://crxextractor.com/
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("ChromeOptions.CAPABILITY",options); //Key value
        options.merge(capabilities);
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://google.com");
        driver.navigate().to("chrome://extensions/");
        JavascriptExecutor js = driver;
        String title = (String) js.executeScript("return document.querySelector('body extensions-manager').shadowRoot.querySelector('#items-list').shadowRoot.querySelector('#aapbdbdomjkkjkaonfhkkikfgjllcleb').shadowRoot.querySelector('#name').textContent");
        Assert.assertEquals(title, "Google Translate");
        Thread.sleep(5000);
        driver.quit();
    }
}
