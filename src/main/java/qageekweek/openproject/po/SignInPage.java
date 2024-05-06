package qageekweek.openproject.po;

import org.openqa.selenium.By;
import qageekweek.ActionBot;
import qageekweek.po.AbstractPage;
import qageekweek.openproject.po.MainPage;

import static qageekweek.DBy.*;

public class SignInPage extends AbstractPage {

	private static final By LOGIN_TB_BY = id("username", "Login tb");
	private static final By PASSWORD_TB_BY = id("password", "Password tb");
	private static final By SIGNIN_BTN_BY = cssSelector("[data-disable-with=\"Loading...\"]", "Sign-in btn");

	public SignInPage(ActionBot bot) {
		super(bot);
	}

	@Override
	protected void assertInPage() {
		bot.waitForVisible(LOGIN_TB_BY);
	}

	public SignInPage typeToUsernameOrEmailTb(String userNameOrEmail) {
		bot.typeTo(LOGIN_TB_BY, userNameOrEmail);
		return this;
	}

	public SignInPage typeToPasswordTb(String password) {
		bot.typeTo(PASSWORD_TB_BY, password);
		return this;
	}

	public MainPage clickOnSignInBtnAndGoToMainPage() {
		bot.clickOn(SIGNIN_BTN_BY);
		return new MainPage(bot);
	}


}
