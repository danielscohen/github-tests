package qageekweek.openproject;

import il.co.topq.difido.ReportDispatcher;
import il.co.topq.difido.ReportManager;
import lombok.Cleanup;
import lombok.val;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import qageekweek.ActionBot;
import qageekweek.openproject.po.MainPage;
import qageekweek.openproject.po.SignInPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

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

    public MainPage loginToOpenProjectWebsite() throws FileNotFoundException, IOException {

        val openProjectCredentials = getOpenProjectCredentials();
        val username = openProjectCredentials.getProperty("username");
        val password = openProjectCredentials.getProperty("password");

        report.startLevel("Login");

        val bot = new ActionBot(driver);

        val signInPage = new SignInPage(bot);
        val mainPage = signInPage.typeToUsernameOrEmailTb(username)
                .typeToPasswordTb(password)
                .clickOnSignInBtnAndGoToMainPage();

        report.endLevel();

        return mainPage;
    }

    private static Properties getOpenProjectCredentials() throws FileNotFoundException, IOException {
        val rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        val openProjectCredentialsPath = rootPath + "/../../openProject.properties";
        val openProjectCredentials = new Properties();

        @Cleanup val is = new FileInputStream(openProjectCredentialsPath);
        openProjectCredentials.load(is);

        return openProjectCredentials;
    }
}
