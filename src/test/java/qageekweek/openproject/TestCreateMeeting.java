package qageekweek.openproject;

import lombok.val;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class TestCreateMeeting extends AbstractTestCase {

    private final String MEETING_START_DATE = "01/12/2024";
    private final String MEETING_START_TIME = "14:05";
    private final String MEETING_DURATION_HOURS = "1";

    @Test
    public void testCreateMeeting() throws FileNotFoundException, IOException, ParseException, InterruptedException {
        Random random = new Random();

        val meetingStartDateTime = (new SimpleDateFormat("dd/MM/yyyy HH:mm")).parse(MEETING_START_DATE + " " + MEETING_START_TIME);
        val cal = Calendar.getInstance();
        cal.setTime(meetingStartDateTime);


        val meetingTitle = "Meeting " + random.nextInt();

        val mainPage = loginToOpenProjectWebsite();

        report.startLevel("Navigate to meetings page");

        val meetingsPage = mainPage.clickOnMeetingsButtonAndGoToMeetingsPage();

        report.endLevel();

        report.startLevel("Create new meeting");

        val newMeetingCreationPage = meetingsPage.clickOnCreateNewMeetingBtnAndGoToNewMeetingCreationPage();

        val meetingDetailsPage = newMeetingCreationPage.typeToMeetingTitleTb(meetingTitle)
                .clickOnClassicMeetingTypeRadioBtn()
                .clickOnProjectDropDownArrowBtn()
                .clickOnDemoProjectSelection()
                .clickOnMeetingStartDateTb()
                .clickOnMonthDropDownBtn()
                .clickOnMonthSelection(cal.get(Calendar.MONTH))
                .typeToYearTb(String.valueOf(cal.get(Calendar.YEAR)))
                .clickOnDaySelection(String.valueOf(cal.get(Calendar.DATE)))
                .typeToTimeTb((new SimpleDateFormat("hhmma")).format(cal.getTime()))
                .typeToDurationTB(MEETING_DURATION_HOURS)
                .clickOnInviteeCb()
                .clickOnAttendeeCb()
                .clickOnCreateMeetingBtnAndGoToMeetingDetailsPage();

        report.endLevel();

        report.startLevel("Check that meeting creation pop up is visible");

        assertThat(meetingDetailsPage.getTextOfMeetingCreationResultMsg()).as("Verify that pop-up for successful " +
                        "creation " +
                        "of %s is visible", meetingTitle)
                .isEqualTo("Successful creation.");


        report.endLevel();

        report.startLevel("Check that details of newly created meeting are correct");

        assertThat(meetingDetailsPage.getTextOfNewlyCreatedMeetingTitle()).as("Verify that meeting title displayed " +
                        "equals meeting title supplied")
                .isEqualTo(meetingTitle);

        assertThat(meetingDetailsPage.getTextOfDisplayedMeetingStartTime()).as("Verify that meeting start time displayed " +
                        "equals meeting start time supplied")
                .isEqualTo(MEETING_START_TIME);

        assertThat(meetingDetailsPage.getTextOfDisplayedMeetingStartDate()).as("Verify that meeting start date displayed " +
                        "equals meeting start date supplied")
                .isEqualTo(MEETING_START_DATE);


        report.endLevel();
        TimeUnit.SECONDS.sleep(4);
    }
}
