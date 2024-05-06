package qageekweek.po;

import org.openqa.selenium.By;

import qageekweek.ActionBot;
import qageekweek.DBy;

public abstract class AbstractRepositoryPage extends AbstractPage {

	private final static By ISSUES_LNK_BY = DBy.cssSelector("a[data-selected-links^='repo_issues']","Issues Link");
	
	
	public AbstractRepositoryPage(ActionBot bot) {
		super(bot);
	}

	
	public RepositoryIssuesPage clickOnIssuesLnkAndGoToIssuesPage() {
		bot.clickOn(ISSUES_LNK_BY);
		return new RepositoryIssuesPage(bot);
	}

}
