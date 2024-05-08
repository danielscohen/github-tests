package qageekweek.openproject.po;

import org.openqa.selenium.By;
import qageekweek.ActionBot;
import qageekweek.DBy;
import qageekweek.po.AbstractPage;

public class MeetingsPage extends AbstractPage {

    private final static By MEETINGS_PAGE_TITLE_BY = DBy.xpath("//h2[text()=\"Meetings\"]", "Meetings page title");
    private final static By CREATE_NEW_MEETING_BTN_BY = DBy.id("add-meeting-button", "Create new meeting button");

    public MeetingsPage(ActionBot bot) {
        super(bot);
    }

    public NewMeetingCreationPage clickOnCreateNewMeetingBtnAndGoToNewMeetingCreationPage(){
        bot.waitForVisible(CREATE_NEW_MEETING_BTN_BY).clickOn(CREATE_NEW_MEETING_BTN_BY);
        return  new NewMeetingCreationPage(bot);
    }

    @Override
    protected void assertInPage() {
        bot.waitForVisible(MEETINGS_PAGE_TITLE_BY);
    }
}
