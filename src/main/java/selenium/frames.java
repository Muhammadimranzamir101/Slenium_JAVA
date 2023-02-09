package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.time.Duration;

public class frames {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://jqueryui.com/droppable/");
        driver.switchTo().frame(driver.findElement(By.cssSelector(".demo-frame")));
        WebElement src = driver.findElement(By.cssSelector("#draggable"));
        WebElement destination = driver.findElement(By.cssSelector("#droppable"));
        String destinationText = driver.findElement(By.cssSelector("#droppable>p")).getText();
        System.out.println(destinationText);

        Actions a = new Actions(driver);
        //a.clickAndHold(src).moveToElement(destination).release().build().perform();
        a.dragAndDrop(src,destination).perform();
        Thread.sleep(5000);
        Assert.assertNotEquals(driver.findElement(By.cssSelector("#droppable>p")).getText(),"Drop here");

        driver.close();
    }
}
