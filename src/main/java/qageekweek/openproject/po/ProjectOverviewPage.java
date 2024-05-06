package qageekweek.openproject.po;

import org.openqa.selenium.By;
import qageekweek.ActionBot;
import qageekweek.DBy;
import qageekweek.po.AbstractPage;

public class ProjectOverviewPage extends AbstractPage {

	private final static By WORK_PACKAGES_BTN_BY = DBy.id("main-menu-work-packages","Work packages button");

	public ProjectOverviewPage(ActionBot bot) {
		super(bot);
	}

	public WorkPackagesPage clickOnWorkPackagesBtnAndGoToWorkPackagesPage(){
		bot.clickOn(WORK_PACKAGES_BTN_BY);
		return new WorkPackagesPage(bot);
	}

	@Override
	protected void assertInPage() {
		bot.waitForVisible(WORK_PACKAGES_BTN_BY);
	}

}
