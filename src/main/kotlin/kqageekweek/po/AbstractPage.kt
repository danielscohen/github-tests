package kqageekweek.po

import il.co.topq.difido.ReportDispatcher
import il.co.topq.difido.ReportManager
import org.openqa.selenium.WebDriver
import qageekweek.ActionBot

abstract class AbstractPage(private var driver : WebDriver) {

    protected var bot : ActionBot
    protected var report: ReportDispatcher = ReportManager.getInstance()

    init {
        bot = ActionBot(driver)
        report.step("In " + this.javaClass.simpleName)
        bot.takeScreenshot("In " + this.javaClass.simpleName + " page")
        assertInPage()

    }

    abstract fun assertInPage()


}


