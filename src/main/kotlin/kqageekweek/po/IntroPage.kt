package kqageekweek.po

import org.openqa.selenium.WebDriver
import qageekweek.DBy
import qageekweek.po.SignInPage

class IntroPage(driver: WebDriver) : AbstractPage(driver) {

    private val SIGNIN_BY = DBy.cssSelector("a[href='/login']", "Sigin in link")

    override fun assertInPage() {
    }

    fun clickOnSignInlnk(): SignInPage {
        bot.clickOn(SIGNIN_BY)
        return SignInPage(bot)
    }


}



