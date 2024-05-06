package qageekweek.po;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import qageekweek.ActionBot;
import qageekweek.DBy;

public class RepositoryIssuesPage extends AbstractRepositoryPage{

	private final static By NEW_ISSUE_BTN_BY = DBy.partialLinkText("New issue","New issue button");
	private final static By SEARCH_ISSUE_TB_BY = DBy.id("js-issues-search", "Search issue text box");
	private final static By NUMBER_OF_ISSUES_BY = DBy.cssSelector("div[aria-label='Issues'] div.js-issue-row","Issues");
	
	public RepositoryIssuesPage(ActionBot bot) {
		super(bot);
	}
	
	public NewIssuePage clickOnNewIssueBtnAndGoToNewIssuePage() {
		bot.clickOn(NEW_ISSUE_BTN_BY);
		return new NewIssuePage(bot);
	}
	
	public RepositoryIssuesPage typeToSearchTb(String query) {
		bot.typeTo(SEARCH_ISSUE_TB_BY, query);
		bot.typeTo(SEARCH_ISSUE_TB_BY, Keys.ENTER);
		return this;
	}
	
	public int getNumberOfIssues() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		return bot.count(NUMBER_OF_ISSUES_BY);
	}
	
	@Override
	protected void assertInPage() {
		bot.waitForVisible(NEW_ISSUE_BTN_BY);
	}
	

}
