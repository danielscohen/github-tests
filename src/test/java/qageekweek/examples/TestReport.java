package qageekweek.examples;

import il.co.topq.difido.ReportDispatcher;
import il.co.topq.difido.ReportManager;
import il.co.topq.difido.model.Enums;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({il.co.topq.difido.ReportManagerHook.class})
public class TestReport {

    protected ReportDispatcher report = ReportManager.getInstance();

    @Test
    public void testUtf() {
        report.log("אני כותב בשפת הקודש");
        report.log("Я пишу на языке Достоевского");
    }

    @Test(description = "Test with steps")
    public void testWithSteps() {
        report.step("Step 1");
        report.log("Simple log message");
        report.step("Step 2");
        report.step("Step 3");
    }

    @Test(description = "Test with levels")
    public void testWithLevels() {
        report.startLevel("Click me to see the messages");
        report.log("Message in level");
        report.log("Message in level");
        report.log("Message in level");
        report.log("Message in level");
        report.endLevel();
    }

    @Test(description = "Test with multiple levels")
    public void testWithMultipleLevels() {
        report.startLevel("Level 1");
        try {
            report.log("Message in level 1");
            report.startLevel("Level 2");
            try {
                report.log("Message in level 2");
                report.startLevel("Level 3");
                try {
                    report.log("Message in level 3");
                } finally {
                    report.endLevel();
                }
            } finally {
                report.endLevel();
            }
            report.log("Message in level 1");

        } finally {
            report.endLevel();

        }

    }

    @Test(description = "Test with multiple levels with failures")
    public void testWithMultipleLevelsWithFailures() {
        report.startLevel("Level 1");
        try {
            report.log("Message in level 1");
            report.startLevel("Level 2");
            try {
                report.log("Message in level 2");
                report.startLevel("Level 3");
                try {
                    report.log("Message in level 3");
                    report.log("Failure", Enums.Status.error);
                } finally {
                    report.endLevel();
                }

                report.startLevel("Level 3 (again)");
                try {
                    report.log("Message in level 3");
                    report.log("Success");
                } finally {
                    report.endLevel();
                }

            } finally {
                report.endLevel();
            }
            try {
                report.startLevel("Level 2 (again)");
                report.log("Message in level 2");
            }finally {
                report.endLevel();
            }

            report.log("Message in level 1");

        } finally {
            report.endLevel();

        }
        report.startLevel("Level 1 (2)");
        try {
            report.log("In level 1 (2)");
            report.startLevel("Level 2 (2)");
            try {
                report.log("In level 2 (2)");
            } finally {
                report.endLevel();
            }

        } finally {
            report.endLevel();
        }

    }

    @Test(description = "Test with various log messages")
    public void testWithVariousLogMessages() throws Exception {
        report.step("This is the first step");
        report.startLevel("Starting level");
        report.log("Message inside level");
        report.log("This is title", "this is message");
        report.log("Message inside level", "Inside level");
        report.log("Message inside level", "Inside level");
        report.endLevel();

        report.step("This is the second step");
        report.startLevel("Level with failure");
        report.log("Something wrong happened", Enums.Status.failure);
        report.endLevel();
    }


}
