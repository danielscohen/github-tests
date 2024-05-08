package qageekweek;

import java.io.File;
import java.time.Duration;

import lombok.val;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import il.co.topq.difido.ReportDispatcher;
import il.co.topq.difido.ReportManager;

public class ActionBot {

    private final static int DEFAULT_EXPLICIT_WAIT_TIME = 25;

    protected ReportDispatcher report = ReportManager.getInstance();

    private int explicitTimeoutInSeconds = DEFAULT_EXPLICIT_WAIT_TIME;

    private WebDriver driver;

    private By rootElementBy;

    private WebDriverWait wait;

    private SearchContext searcher;

    public ActionBot(WebDriver driver, By root) {
        init(driver, driver.findElement(root), root);
    }

    public ActionBot(ActionBot actionBot, By root) {
        init(actionBot.driver, actionBot.driver.findElement(root), root);
    }

    private ActionBot(ActionBot actionBot, WebElement root) {
        init(actionBot.driver, root, null);
    }

    public ActionBot(WebDriver driver) {
        init(driver, driver, null);
    }

    private void init(WebDriver driver, SearchContext root, By rootElementBy) {
        this.driver = driver;
        this.searcher = root;
        this.rootElementBy = rootElementBy;
        initWebDriverWait();
    }

    private void initWebDriverWait() {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitTimeoutInSeconds));
    }

    public ActionBot clickOn(By by) {
        report("click on", by);
        searcher.findElement(by).click();
        return this;
    }

    public ActionBot typeTo(By by, String keys, boolean clearFirst) {
        val element = searcher.findElement(by);

        if(clearFirst){
            report("clear", by);
            element.clear();
        }

        report("type to", by);
        element.sendKeys(keys);
        return this;
    }

    public ActionBot typeTo(By by, String keys) {
        return this.typeTo(by, keys, false);
    }

    public ActionBot typeTo(By by, Keys keys) {
        report("type to" + keys, by);
        searcher.findElement(by).sendKeys(keys);
        return this;
    }

    public String getTextOf(By by) {
        report("get text of", by);
        return searcher.findElement(by).getText();
    }

    public int count(By by) {
        report("count", by);
        return searcher.findElements(by).size();
    }

    public ActionBot waitForVisible(By by) {
        report("wait for visibility of", by);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return this;
    }

    public ActionBot takeScreenshot(String description) {
        File scrFile = ((TakesScreenshot) searcher).getScreenshotAs(OutputType.FILE);
        report.addImage(scrFile, description);
        return this;
    }

    public ActionBot waitForVisible(By by, int timeoutInSec) {
        return this;
    }

    private void report(String action, By by) {
        if (by instanceof DescriptiveBy) {
            report.log("About to " + action + " " + ((DescriptiveBy) by).getDescription());
        }
    }

    public WebDriver getDriver() {
        return this.driver;
    }

}
