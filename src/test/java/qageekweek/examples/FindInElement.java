package qageekweek.examples;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FindInElement {

    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
    }

    @Test
    public void test() throws InterruptedException {
        driver.get("http://www.google.com");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//form[@action='/search']//input[@type='text']")).sendKeys("Cheese");
        WebElement searchForm = driver.findElement(By.xpath("//form"));
        WebElement textBox = searchForm.findElement(By.xpath(".//input[type='text']"));
        textBox.sendKeys("Cheese");
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
