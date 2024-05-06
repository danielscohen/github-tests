package qageekweek.infra;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import il.co.topq.difido.model.Enums;
import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;

import com.automation.remarks.testng.UniversalVideoListener;
import com.automation.remarks.video.recorder.VideoRecorder;

import il.co.topq.difido.ReportDispatcher;
import il.co.topq.difido.ReportManager;

public class DifidoVideoReportListener extends UniversalVideoListener {

    private ReportDispatcher report = ReportManager.getInstance();

    @Override
    public void onTestStart(ITestResult result) {
        if (!VideoRecorder.conf().videoEnabled()) {
            super.onTestStart(result);
            return;
        }
        final String folder = VideoRecorder.conf().folder();
        try {
            FileUtils.deleteDirectory(new File(folder));
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onTestStart(result);

    }

    @Override
    public void onTestFailure(ITestResult result) {
        super.onTestFailure(result);
        if (!VideoRecorder.conf().videoEnabled()) {
            return;
        }
        final String folder = VideoRecorder.conf().folder();
        if (!(new File(folder).exists())) {
            report.log("Video folder " + folder + " was not created as expected", Enums.Status.warning);
            return;
        }
        try (Stream<Path> paths = Files.walk(Paths.get(folder))) {
            paths
                    .filter(Files::isRegularFile)
                    .filter(f -> !f.toString().startsWith("null"))
                    .forEach(f -> report.addFile(f.toFile(), "Video"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
