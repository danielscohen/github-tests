package qageekweek.openproject;

import il.co.topq.difido.ReportDispatcher;
import il.co.topq.difido.ReportManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(il.co.topq.difido.ReportManagerHook.class)
public class AbstractTestCase {
    protected WebDriver driver;
    protected ReportDispatcher report = ReportManager.getInstance();

    @BeforeMethod
    public void setup() {
        report.addTestProperty("User", "Daniel");
        report.log("Starting Test ...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://daniel-cohen.openproject.com/");
    }

    @AfterMethod
    public void teardown() {
        report.log("Ending Test ...");
        if (driver != null) {
            driver.quit();
        }
    }
}
