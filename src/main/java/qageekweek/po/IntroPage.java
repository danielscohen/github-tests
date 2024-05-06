package qageekweek.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import qageekweek.DBy;

public class IntroPage extends AbstractPage {

	private static final By SIGNIN_BY = DBy.cssSelector("a[href='/login']","Sign-in in link");
	
	public IntroPage(WebDriver driver) {
		super(driver);
	}
	
	public SignInPage clickOnSignInlnkAndGoToSignInPage() {
		bot.clickOn(SIGNIN_BY);
		return new SignInPage(bot);
	}
	
	
	@Override
	protected void assertInPage() {
		bot.waitForVisible(SIGNIN_BY);
	}

}
