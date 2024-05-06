package qageekweek.po;

import org.openqa.selenium.By;

import qageekweek.ActionBot;
import qageekweek.DBy;

public class RepositoriesWidget extends AbstractPage {

	private static final By FIND_A_REPO_TB = DBy.id("dashboard-repos-filter-left","Find a repository text box"); 
	
	public RepositoriesWidget(ActionBot bot) {
		super(bot);
	}
	
	public RepositoriesWidget typeToFindARepositoryTb(String text) {
		bot.typeTo(FIND_A_REPO_TB, text);
		return this;
	}
	
	public RepositoryCodePage clickOnRepoAndGoToRepoCodePage(String owner, String repoName) {
		bot.clickOn(By.cssSelector(String.format("a[href='/%s/%s']", owner,repoName)));
		return new RepositoryCodePage(new ActionBot(bot.getDriver()));
	}

	@Override
	protected void assertInPage() {
		// TODO Auto-generated method stub

	}

}
