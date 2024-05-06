package qageekweek;

import com.automation.remarks.testng.UniversalVideoListener;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import il.co.topq.difido.ReportDispatcher;
import il.co.topq.difido.ReportManager;
import qageekweek.infra.DifidoVideoReportListener;
import qageekweek.po.IntroPage;

@Listeners({il.co.topq.difido.ReportManagerHook.class, DifidoVideoReportListener.class})
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AbstractTestCase extends AbstractTestNGSpringContextTests {

	@Value("${url}")
	private String url;
	
	@Value("${browser}")
	private String browser;
	
	@Autowired
	private WebDriverFactory factory;
	
	protected ReportDispatcher report = ReportManager.getInstance();	
	protected IntroPage introPage;
	protected WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver = factory.build();
		driver.get(url);
		introPage = new IntroPage(driver);
	}
	
	
	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			report.log("Closing driver");
			driver.quit();
		}
	}
	
	protected void step(String description) {
		report.endLevel();
		report.startLevel(description);
	}
	

	protected void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
		}
	}
	
}
