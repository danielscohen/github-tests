package qageekweek.openproject.po;

import org.openqa.selenium.By;
import qageekweek.ActionBot;
import qageekweek.DBy;
import qageekweek.po.AbstractPage;

public class WorkPackagesPage extends AbstractPage {

	private final static By CREATE_NEW_WORK_PACKAGE_BTN_BY = DBy.cssSelector(".button.-primary.add-work-package","Create new Work package button");
	private final static By CREATE_NEW_TASK_BTN_BY = DBy.cssSelector("a[aria-label=\"Task\"]","Create new task button");
	private final static By SAVE_TASK_BTN_BY = DBy.id("work-packages--edit-actions-save","Save new task button");
	private final static By FILTER_WORK_PACKAGES_BTN_BY = DBy.id("work-packages-filter-toggle-button","Filter work packages button");
	private final static By TASK_SUBJECT_TB_BY = DBy.id("wp-new-inline-edit--field-subject","New task subject text box");
	private final static By TASK_DESCRIPTION_TB_BY = DBy.cssSelector("div[aria-label=\"Rich Text Editor. Editing area: main\"]","New task description text box");
	private final static By FILTER_BY_TEXT_TB_BY = DBy.id("filter-by-text-input","Filter by text text box");
	private final static By TASK_CREATION_RESULT_MSG_BY = DBy.xpath("//div[@class=\"op-toast--content\"]/p/span[1]","New task creation result message");
	private final static By TASK_FILTER_RESULT_BY = DBy.xpath("//tbody[@class=\"results-tbody work-package--results-tbody\"]//span[@class=\"inline-edit--display-field subject -required -editable\"][1]","Task filter result");

	public WorkPackagesPage(ActionBot bot) {
		super(bot);
	}

	public WorkPackagesPage clickOnCreateNewWorkPackageBtn(){
		bot.clickOn(CREATE_NEW_WORK_PACKAGE_BTN_BY);
		return this;
	}

	public WorkPackagesPage clickOnCreateNewTaskBtn(){
		bot.waitForVisible(CREATE_NEW_TASK_BTN_BY).clickOn(CREATE_NEW_TASK_BTN_BY);
		return this;
	}

	public WorkPackagesPage clickOnSaveTaskBtn(){
		bot.waitForVisible(SAVE_TASK_BTN_BY).clickOn(SAVE_TASK_BTN_BY);
		return this;
	}

	public WorkPackagesPage clickOnFilterWorkPackagesBtn(){
		bot.waitForVisible(FILTER_WORK_PACKAGES_BTN_BY).clickOn(FILTER_WORK_PACKAGES_BTN_BY);
		return this;
	}

	public WorkPackagesPage typeToTaskSubjectTb(String taskSubject){
		bot.waitForVisible(TASK_SUBJECT_TB_BY).typeTo(TASK_SUBJECT_TB_BY, taskSubject);
		return this;
	}

	public WorkPackagesPage typeToTaskDescriptionTb(String taskDescription){
		bot.waitForVisible(TASK_DESCRIPTION_TB_BY).typeTo(TASK_DESCRIPTION_TB_BY, taskDescription);
		return this;
	}

	public WorkPackagesPage typeToFilterByTextTb(String filterText){
		bot.waitForVisible(FILTER_BY_TEXT_TB_BY).typeTo(FILTER_BY_TEXT_TB_BY, filterText);
		return this;
	}

	public String getTextOfTaskCreationResultMsg(){
		return bot.waitForVisible(TASK_CREATION_RESULT_MSG_BY).getTextOf(TASK_CREATION_RESULT_MSG_BY);
	}

	public String getTextOfTaskFilterResult(){
		while(bot.count(TASK_FILTER_RESULT_BY) > 1);
		return bot.waitForVisible(TASK_FILTER_RESULT_BY).getTextOf(TASK_FILTER_RESULT_BY);
	}

	@Override
	protected void assertInPage() {
		bot.waitForVisible(CREATE_NEW_WORK_PACKAGE_BTN_BY);
	}

}
