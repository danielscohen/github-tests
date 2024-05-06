package qageekweek.po;

import org.openqa.selenium.By;

import qageekweek.ActionBot;
import qageekweek.DBy;

public class NewIssuePage extends AbstractRepositoryPage {

	private static final By TITLE_TB_BY = DBy.id("issue_title", "Issue title");
	private static final By BODY_TB_BY = DBy.id("issue_body", "Issue body");
	private static final By SUBMIT_NEW_ISSUE_BY = DBy.cssSelector("div.timeline-comment button.btn-primary","Submit new issue button");

	
	public NewIssuePage(ActionBot bot) {
		super(bot);
	}

	
	public NewIssuePage typeToTitle(String title) {
		bot.typeTo(TITLE_TB_BY, title);
		return this;
	}
	
	public NewIssuePage typeToComment(String comment) {
		bot.typeTo(BODY_TB_BY, comment);
		return this;
	}
	
	public IssuePage clickOnSubmitIssueBtnAndGoToIssuePage() {
		bot.clickOn(SUBMIT_NEW_ISSUE_BY);
		return new IssuePage(bot);
	}
	
	@Override
	protected void assertInPage() {
		bot.waitForVisible(TITLE_TB_BY);
	}
	
	

}
