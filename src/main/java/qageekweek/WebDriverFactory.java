package qageekweek;

import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.springframework.stereotype.Component;
@Component
public class WebDriverFactory {

	private String browser;

	private boolean headless;

	public WebDriver build() {
		WebDriver driver = null;
		switch (browser) {
			case "edge":
			case "firefox":
			case "safari":
			case "chrome":
				final ChromeOptions options = new ChromeOptions();
				if (headless) {
					options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
				}
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(options);
				break;
			case "internet explorer":
				final InternetExplorerOptions options2 = new InternetExplorerOptions();
				options2.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				options2.setCapability("requireWindowFocus", true);
				if (headless) {
					options2.addCommandSwitches("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
				}
				//WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver(options2);
				break;
			default:
				break;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		return driver;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public boolean isHeadless() {
		return headless;
	}

	public void setHeadless(boolean headless) {
		this.headless = headless;
	}


}
