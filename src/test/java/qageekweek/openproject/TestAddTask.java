package qageekweek.openproject;

import lombok.Cleanup;
import lombok.val;
import org.testng.annotations.Test;
import qageekweek.openproject.AbstractTestCase;
import qageekweek.ActionBot;
import qageekweek.openproject.po.SignInPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

public class TestAddTask extends AbstractTestCase {

    @Test
    public void testAddTask() throws InterruptedException, FileNotFoundException, IOException {

        Random random = new Random();

        val openProjectCredentials = getOpenProjectCredentials();

        val newTaskSubject = "Very Important Task " + random.nextInt();
        val newTaskDescription = "Very important description!";
        val username = openProjectCredentials.getProperty("username");
        val password = openProjectCredentials.getProperty("password");

        // Login:

        report.startLevel("Login");

        val bot = new ActionBot(driver);

        val signInPage = new SignInPage(bot);
        val mainPage = signInPage.typeToUsernameOrEmailTb(username)
                .typeToPasswordTb(password)
                .clickOnSignInBtnAndGoToMainPage();

        report.endLevel();

        // Navigate to Demo project:
        report.startLevel("Navigate to Demo project");

        val projectOverviewPage = mainPage.clickOnSelectAProjectBtn().clickOnDemoProjectBtnAndGoToProjectOverviewPage();
        val workPackagesPage = projectOverviewPage.clickOnWorkPackagesBtnAndGoToWorkPackagesPage();

        report.endLevel();

        // Create new task:
        report.startLevel("Create new task");

        workPackagesPage.clickOnCreateNewWorkPackageBtn()
                .clickOnCreateNewTaskBtn()
                .typeToTaskSubjectTb(newTaskSubject)
                .typeToTaskDescriptionTb(newTaskDescription)
                .clickOnSaveTaskBtn();

        report.endLevel();

        // Check that task creation pop up is visible:
        report.startLevel("Check that task creation pop up is visible");

        assertThat(workPackagesPage.getTextOfTaskCreationResultMsg()).as("Verify that pop-up for successful creation " +
                        "of %s is visible", newTaskSubject)
                .isEqualTo("Successful creation.");

        report.endLevel();
        // Search for newly created task and ensure that it exists:
        report.startLevel("Search for newly created task and ensure that it exists");

        workPackagesPage.clickOnFilterWorkPackagesBtn()
                .typeToFilterByTextTb(newTaskSubject);

        assertThat(workPackagesPage.getTextOfTaskFilterResult()).as("Verify that %s is the filter result",
                        newTaskDescription)
                .isEqualTo(newTaskSubject);

        TimeUnit.SECONDS.sleep(4);
        report.endLevel();
    }

    private static Properties getOpenProjectCredentials() throws FileNotFoundException, IOException {
        val rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        val openProjectCredentialsPath = rootPath + "/../../openProject.properties";
        val openProjectCredentials = new Properties();

        @Cleanup val is = new FileInputStream(openProjectCredentialsPath);
        openProjectCredentials.load(is);

        return openProjectCredentials;
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
//        WebElement projectsDropDown = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions
//        .elementToBeClickable(By.id("projects-menu")));
//        projectsDropDown.click();
//        WebElement demoProject = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions
//        .elementToBeClickable(By.xpath("//span[text()=\"Demo project\"]")));
//        demoProject.click();
//
//        // Create new task:
//
//        WebElement workPackagesButton = (new WebDriverWait(driver, Duration.ofSeconds(10))).until
//        (ExpectedConditions.elementToBeClickable(By.id("main-menu-work-packages")));
//        workPackagesButton.click();
//        WebElement createWorkPackageDropDown = (new WebDriverWait(driver, Duration.ofSeconds(10))).until
//        (ExpectedConditions.elementToBeClickable(By.cssSelector(".button.-primary.add-work-package")));
//        createWorkPackageDropDown.click();
//        WebElement createTaskSelection = (new WebDriverWait(driver, Duration.ofSeconds(10))).until
//        (ExpectedConditions.elementToBeClickable(By.cssSelector("a[aria-label=\"Task\"]")));
//        createTaskSelection.click();
//        WebElement taskSubjectField = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions
//        .elementToBeClickable(By.id("wp-new-inline-edit--field-subject")));
//        taskSubjectField.sendKeys(newTaskSubject);
//        WebElement taskDescriptionField = (new WebDriverWait(driver, Duration.ofSeconds(10))).until
//        (ExpectedConditions.elementToBeClickable(By.cssSelector("div[aria-label=\"Rich Text Editor. Editing area:
//        main\"]")));
//        taskDescriptionField.sendKeys(newTaskDescription);
//        WebElement newTaskSaveButton = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions
//        .elementToBeClickable(By.id("work-packages--edit-actions-save")));
//        newTaskSaveButton.click();
//
//        // Check that task creation pop up is visible:
//
//        boolean taskCreationSuccessfulToastVisible = true;
//
//        try {
//            (new WebDriverWait(driver, Duration.ofSeconds(5))).until(ExpectedConditions.visibilityOfElementLocated
//            (By.cssSelector(".-success.op-toast")));
//        } catch (org.openqa.selenium.TimeoutException ex) {
//            taskCreationSuccessfulToastVisible = false;
//        }
//
//        org.testng.Assert.assertTrue(taskCreationSuccessfulToastVisible, "New task creation successful toast is
//        visible");
//
//
//        // Search for newly created task and ensure that it exists:
//
//        WebElement filterButton = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions
//        .elementToBeClickable(By.id("work-packages-filter-toggle-button")));
//        filterButton.click();
//        WebElement filterTextField = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions
//        .elementToBeClickable(By.id("filter-by-text-input")));
//        filterTextField.sendKeys(newTaskSubject);
//
//        boolean newTaskVisible = true;
//
//        try {
//            (new WebDriverWait(driver, Duration.ofSeconds(5))).until(ExpectedConditions.visibilityOfElementLocated
//            (By.xpath("//span[text()=\"" + newTaskSubject + "\" and @class=\"inline-edit--display-field subject
//            -required -editable\"]")));
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


}
