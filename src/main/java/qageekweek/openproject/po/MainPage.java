package qageekweek.openproject.po;

import org.openqa.selenium.By;
import qageekweek.ActionBot;
import qageekweek.DBy;
import qageekweek.po.AbstractPage;

public class MainPage extends AbstractPage {

	private final static By SELECT_A_PROJECT_BTN_BY = DBy.id("projects-menu","Select a project button");
	private final static By DEMO_PROJECT_BTN_BY = DBy.xpath("//span[text()=\"Demo project\"]","Demo project button");

	public MainPage(ActionBot bot) {
		super(bot);
	}

	public MainPage clickOnSelectAProjectBtn(){
		bot.clickOn(SELECT_A_PROJECT_BTN_BY);
		return this;
	}

	public ProjectOverviewPage clickOnDemoProjectBtnAndGoToProjectOverviewPage(){
		bot.waitForVisible(DEMO_PROJECT_BTN_BY).clickOn(DEMO_PROJECT_BTN_BY);
		return new ProjectOverviewPage(bot);
	}

	@Override
	protected void assertInPage() {
		bot.waitForVisible(SELECT_A_PROJECT_BTN_BY);
	}

}
