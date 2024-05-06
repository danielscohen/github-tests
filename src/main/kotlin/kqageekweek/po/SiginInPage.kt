package kqageekweek.po

import org.openqa.selenium.WebDriver
import qageekweek.DBy
import qageekweek.po.MainPage

class SignInPage(driver: WebDriver) : AbstractPage(driver) {

    private val LOGIN_TB_BY = DBy.id("login_field", "Login tb")
    private val PASSWORD_TB_BY = DBy.id("password", "Password tb")
    private val SIGNIN_BTN_BY = DBy.name("commit", "Siging btn")


    override fun assertInPage() {
        bot.waitForVisible(LOGIN_TB_BY)
    }

    fun typeToUsernameOrEmailTb(userNameOrEmail: String): SignInPage {
        bot.typeTo(LOGIN_TB_BY, userNameOrEmail)
        return this
    }

    fun typeToPasswordTb(password: String): SignInPage {
        bot.typeTo(PASSWORD_TB_BY, password)
        return this
    }

    fun clickOnSignInBtnAndGoToMainPage(): MainPage {
        bot.clickOn(SIGNIN_BTN_BY)
        return MainPage(bot)
    }


}

