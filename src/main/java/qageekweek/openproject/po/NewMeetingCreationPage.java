package qageekweek.openproject.po;

import lombok.val;
import org.openqa.selenium.By;
import qageekweek.ActionBot;
import qageekweek.DBy;
import qageekweek.po.AbstractPage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;

public class NewMeetingCreationPage extends AbstractPage {

    private final static By NEW_MEETING_PAGE_TITLE_BY = DBy.xpath("//h2[text()=\"New Meeting\"]",
            "New meeting page " + "title");
    private final static By MEETING_TITLE_TB_BY = DBy.id("meeting_title", "Meeting title text box");
    private final static By CLASSIC_MEETING_TYPE_RADIO_BTN_BY = DBy.id("meeting_type_Meeting", "Classic meeting type "
            + "radio button");
    private final static By PROJECT_DROPDOWN_ARROW_BTN_BY =
            DBy.xpath("//ng-select[@id=\"project_id\"]//span[@class" + "=\"ng-arrow-wrapper\"]", "Project drop-down " +
                    "arrow button");
    private final static By DEMO_PROJECT_SELECTION_BY = DBy.cssSelector("div[title=\"Demo project\"]", "Demo project "
            + "selection");
    private final static By MEETING_START_DATE_TB_BY = DBy.id("meeting_start_date", "Meeting start date text box");
    private final static By MONTH_DROPDOWN_BTN_BY = DBy.className("flatpickr-monthDropdown-months", "Month drop-down "
            + "button");
    private final static By YEAR_TB_BY = DBy.cssSelector(".numInput.cur-year", "Year text box");
    private final static By TIME_TB_BY = DBy.id("meeting-form-start-time", "Time text box");
    private final static By MEETING_DURATION_TB_BY = DBy.id("meeting-form-duration", "Meeting duration text box");
    private final static By MEETING_CREATE_BTN_BY = DBy.cssSelector("button[type=\"submit\"]", "Create meeting button");
    private final static By INVITEE_CB_BY = DBy.id("checkbox_invited_4", "Meeting invitee checkbox");
    private final static By ATTENDEE_CB_BY = DBy.id("checkbox_attended_4", "Meeting attendee checkbox");

    public NewMeetingCreationPage(ActionBot bot) {
        super(bot);
    }

    public NewMeetingCreationPage typeToMeetingTitleTb(String meetingTitle) {
        bot.waitForVisible(MEETING_TITLE_TB_BY).typeTo(MEETING_TITLE_TB_BY, meetingTitle);
        return this;
    }

    public NewMeetingCreationPage typeToYearTb(String year) {
        bot.waitForVisible(YEAR_TB_BY).clickOn(YEAR_TB_BY).typeTo(YEAR_TB_BY, year);
        return this;
    }

    public NewMeetingCreationPage typeToTimeTb(String startTime) {
        bot.waitForVisible(TIME_TB_BY).typeTo(TIME_TB_BY, startTime);
        return this;
    }

    public NewMeetingCreationPage typeToDurationTB(String duration) {
        bot.waitForVisible(MEETING_DURATION_TB_BY).typeTo(MEETING_DURATION_TB_BY, duration, true);
        return this;
    }

    public NewMeetingCreationPage clickOnClassicMeetingTypeRadioBtn() {
        bot.waitForVisible(CLASSIC_MEETING_TYPE_RADIO_BTN_BY).clickOn(CLASSIC_MEETING_TYPE_RADIO_BTN_BY);
        return this;
    }

    public NewMeetingCreationPage clickOnProjectDropDownArrowBtn() {
        bot.waitForVisible(PROJECT_DROPDOWN_ARROW_BTN_BY).clickOn(PROJECT_DROPDOWN_ARROW_BTN_BY);
        return this;
    }

    public NewMeetingCreationPage clickOnDemoProjectSelection() {
        bot.waitForVisible(DEMO_PROJECT_SELECTION_BY).clickOn(DEMO_PROJECT_SELECTION_BY);
        return this;
    }

    public NewMeetingCreationPage clickOnMeetingStartDateTb() {
        bot.waitForVisible(MEETING_START_DATE_TB_BY).clickOn(MEETING_START_DATE_TB_BY);
        return this;
    }

    public NewMeetingCreationPage clickOnMonthDropDownBtn() {
        bot.waitForVisible(MONTH_DROPDOWN_BTN_BY).clickOn(MONTH_DROPDOWN_BTN_BY);
        return this;
    }

    public NewMeetingCreationPage clickOnMonthSelection(int monthVal) {
        String selectorVal = String.format("option.flatpickr-monthDropdown-month[value=\"%s\"]", monthVal);
        By monthSelectionBy = DBy.cssSelector(selectorVal, Month.of(monthVal) + " selection");
        bot.waitForVisible(monthSelectionBy).clickOn(monthSelectionBy);
        return this;
    }

    public NewMeetingCreationPage clickOnDaySelection(String day) {
        String selectorVal = String.format("//div[@class=\"dayContainer\"]//span[text()=\"%s\"]", day);
        By daySelectionBy = DBy.xpath(selectorVal, day + " selection");
        bot.waitForVisible(daySelectionBy).clickOn(daySelectionBy);
        return this;
    }

    public NewMeetingCreationPage clickOnInviteeCb() {
        bot.waitForVisible(INVITEE_CB_BY).clickOn(INVITEE_CB_BY);
        return this;
    }

    public NewMeetingCreationPage clickOnAttendeeCb() {
        bot.waitForVisible(ATTENDEE_CB_BY).clickOn(ATTENDEE_CB_BY);
        return this;
    }

    public MeetingDetailsPage clickOnCreateMeetingBtnAndGoToMeetingDetailsPage() {
        bot.waitForVisible(MEETING_CREATE_BTN_BY).clickOn(MEETING_CREATE_BTN_BY);
        return new MeetingDetailsPage(bot);
    }

    @Override
    protected void assertInPage() {
        bot.waitForVisible(NEW_MEETING_PAGE_TITLE_BY);
    }
}
