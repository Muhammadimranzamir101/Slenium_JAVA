package selenium;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class screenShot {
    public static void main(String[] args) throws IOException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com");
        int num = (int) Math.floor(Math.random()*10000);
        String path = "/Users/mimran/Documents/Screenshots/screenshot";
        String fileName = path+ num;
        takeScreenShot(driver, fileName);
        driver.navigate().to("https://amazon.com");
        takeScreenShot(driver, path+(int) Math.floor(Math.random()*10000));
        driver.close();
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
