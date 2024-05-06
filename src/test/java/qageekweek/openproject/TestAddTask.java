package qageekweek.openproject;

import lombok.val;
import lombok.var;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qageekweek.ActionBot;
import qageekweek.openproject.po.ProjectOverviewPage;
import qageekweek.openproject.po.MainPage;
import qageekweek.openproject.po.SignInPage;
import qageekweek.openproject.po.WorkPackagesPage;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestAddTask {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://daniel-cohen.openproject.com/");
    }

    @Test
    public void testAddTask() throws InterruptedException {
        Random random = new Random();

        val newTaskSubject = "Very Important Task " + random.nextInt();
        val newTaskDescription = "Very important description!";
        val username = "PLACE_HOLDER";
        val password = "PLACE_HOLDER";

        // Login:

        val bot = new ActionBot(driver);

        val signInPage = new SignInPage(bot);
        val mainPage = signInPage.typeToUsernameOrEmailTb(username)
                .typeToPasswordTb(password)
                .clickOnSignInBtnAndGoToMainPage();

        // Navigate to Demo project:

        val projectOverviewPage = mainPage.clickOnSelectAProjectBtn().clickOnDemoProjectBtnAndGoToProjectOverviewPage();
        val workPackagesPage = projectOverviewPage.clickOnWorkPackagesBtnAndGoToWorkPackagesPage();

        // Create new task:

        workPackagesPage.clickOnCreateNewWorkPackageBtn()
                .clickOnCreateNewTaskBtn()
                .typeToTaskSubjectTb(newTaskSubject)
                .typeToTaskDescriptionTb(newTaskDescription)
                .clickOnSaveTaskBtn();


        // Check that task creation pop up is visible:


        Assert.assertEquals(workPackagesPage.getTextOfTaskCreationResultMsg(), "Successful creation.");


        // Search for newly created task and ensure that it exists:

        workPackagesPage.clickOnFilterWorkPackagesBtn()
                        .typeToFilterByTextTb(newTaskSubject);

        Assert.assertEquals(workPackagesPage.getTextOfTaskFilterResult(), newTaskSubject);

        TimeUnit.SECONDS.sleep(4);
    }

//    @Test
//    public void testAddTaskOld() throws InterruptedException {
//        Random random = new Random();
//
//        final String newTaskSubject = "Very Important Task " + random.nextInt();
//        final String newTaskDescription = "Very important description!";
//        final String username = "dscohen93@gmail.com";
//        final String password = "caucus-comfy-commute";
//
//        // Login:
//
//        WebElement usernameField = driver.findElement(By.id("username"));
//        usernameField.sendKeys("dscohen93@gmail.com");
//        WebElement passwordField = driver.findElement(By.id("password"));
//        passwordField.sendKeys("caucus-comfy-commute");
//        driver.findElement(By.cssSelector("[data-disable-with=\"Loading...\"]")).click();
//
//        // Navigate to Demo project:
//
//        WebElement projectsDropDown = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.elementToBeClickable(By.id("projects-menu")));
//        projectsDropDown.click();
//        WebElement demoProject = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=\"Demo project\"]")));
//        demoProject.click();
//
//        // Create new task:
//
//        WebElement workPackagesButton = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.elementToBeClickable(By.id("main-menu-work-packages")));
//        workPackagesButton.click();
//        WebElement createWorkPackageDropDown = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".button.-primary.add-work-package")));
//        createWorkPackageDropDown.click();
//        WebElement createTaskSelection = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[aria-label=\"Task\"]")));
//        createTaskSelection.click();
//        WebElement taskSubjectField = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.elementToBeClickable(By.id("wp-new-inline-edit--field-subject")));
//        taskSubjectField.sendKeys(newTaskSubject);
//        WebElement taskDescriptionField = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[aria-label=\"Rich Text Editor. Editing area: main\"]")));
//        taskDescriptionField.sendKeys(newTaskDescription);
//        WebElement newTaskSaveButton = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.elementToBeClickable(By.id("work-packages--edit-actions-save")));
//        newTaskSaveButton.click();
//
//        // Check that task creation pop up is visible:
//
//        boolean taskCreationSuccessfulToastVisible = true;
//
//        try {
//            (new WebDriverWait(driver, Duration.ofSeconds(5))).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".-success.op-toast")));
//        } catch (org.openqa.selenium.TimeoutException ex) {
//            taskCreationSuccessfulToastVisible = false;
//        }
//
//        org.testng.Assert.assertTrue(taskCreationSuccessfulToastVisible, "New task creation successful toast is visible");
//
//
//        // Search for newly created task and ensure that it exists:
//
//        WebElement filterButton = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.elementToBeClickable(By.id("work-packages-filter-toggle-button")));
//        filterButton.click();
//        WebElement filterTextField = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.elementToBeClickable(By.id("filter-by-text-input")));
//        filterTextField.sendKeys(newTaskSubject);
//
//        boolean newTaskVisible = true;
//
//        try {
//            (new WebDriverWait(driver, Duration.ofSeconds(5))).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=\"" + newTaskSubject + "\" and @class=\"inline-edit--display-field subject -required -editable\"]")));
//        } catch (org.openqa.selenium.TimeoutException ex) {
//            newTaskVisible = false;
//        }
//
//        Assert.assertTrue(newTaskVisible, "New task: " + newTaskSubject + " is visible");
//
//        TimeUnit.SECONDS.sleep(4);
//    }

//    @Test
//    public void testSearchForCheeseInGoogle() throws InterruptedException {
//        WebElement searchBox = driver.findElement(By.name("q"));  // By.cssSelector("textarea[name='q']")
//        searchBox.sendKeys("Cheese");
//        WebElement buttonElement = driver.findElement(By.name("btnI"));
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeClickable(buttonElement));
//        buttonElement.click();
//        WebElement resultElement = driver.findElement(By.xpath("//h3/span[contains(.,'Cheese')]"));
//        Assert.assertTrue(resultElement.getText().contains("Cheese"));
//        TimeUnit.SECONDS.sleep(4);
//    }


    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }


}
