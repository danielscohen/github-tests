package qageekweek.po;

import org.openqa.selenium.WebDriver;

import il.co.topq.difido.ReportDispatcher;
import il.co.topq.difido.ReportManager;
import qageekweek.ActionBot;

public abstract class AbstractPage {

	protected ReportDispatcher report = ReportManager.getInstance();
	protected final ActionBot bot;

	public AbstractPage(WebDriver driver) {
		super();
		bot = new ActionBot(driver);
		init(bot);
	}

	public AbstractPage(ActionBot bot) {
		super();
		this.bot = bot;
		init(bot);
	}

	private void init(ActionBot bot) {
		report.step("In " + this.getClass().getSimpleName());
		bot.takeScreenshot("In " + this.getClass().getSimpleName() + " page");
		assertInPage();

	}

	protected abstract void assertInPage();

}
