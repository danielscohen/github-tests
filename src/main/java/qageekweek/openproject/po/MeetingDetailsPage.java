package qageekweek.openproject.po;

import lombok.val;
import org.openqa.selenium.By;
import qageekweek.ActionBot;
import qageekweek.DBy;
import qageekweek.po.AbstractPage;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MeetingDetailsPage extends AbstractPage {

    private final static By MEETING_DETAILS_PAGE_TITLE_BY = DBy.xpath("//div[@class=\"title-container\"]//a",
            "Meeting details page title");
    private final static By MEETING_CREATION_RESULT_MSG_BY = DBy.className("op-toast--content", "New meeting creation" +
            " result message");
    private final static By MEETING_TIME_DATE_DETAILS_BY = DBy.xpath("//div[@class=\"meeting details box\"]//p[" +
            "./strong[text()=\"Start time\"]]", "Meeting displayed time and date details");

    public MeetingDetailsPage(ActionBot bot) {
        super(bot);
    }

    public String getTextOfMeetingCreationResultMsg() {
        return bot.waitForVisible(MEETING_CREATION_RESULT_MSG_BY).getTextOf(MEETING_CREATION_RESULT_MSG_BY);
    }

    public String getTextOfNewlyCreatedMeetingTitle() {
        return bot.waitForVisible(MEETING_DETAILS_PAGE_TITLE_BY).getTextOf(MEETING_DETAILS_PAGE_TITLE_BY);
    }

    public String getTextOfDisplayedMeetingStartTime() throws ParseException {
        val dateTimeText = bot.waitForVisible(MEETING_TIME_DATE_DETAILS_BY).getTextOf(MEETING_TIME_DATE_DETAILS_BY);
        val displayedStartDateTime12Fmt = (new SimpleDateFormat("'Start time:' MM/dd/yyyy hh:mm a")).parse(dateTimeText);
        val displayedStartTime24Fmt = (new SimpleDateFormat("HH:mm")).format(displayedStartDateTime12Fmt);
        return displayedStartTime24Fmt;
    }

    public String getTextOfDisplayedMeetingStartDate() throws ParseException {
        val dateTimeText = bot.waitForVisible(MEETING_TIME_DATE_DETAILS_BY).getTextOf(MEETING_TIME_DATE_DETAILS_BY);
        val displayedStartDateTime = (new SimpleDateFormat("'Start time:' MM/dd/yyyy hh:mm a")).parse(dateTimeText);
        val displayedStartDate = (new SimpleDateFormat("dd/MM/yyyy")).format(displayedStartDateTime);
        return displayedStartDate;
    }

    @Override
    protected void assertInPage() {
        bot.waitForVisible(MEETING_DETAILS_PAGE_TITLE_BY);
    }
}
