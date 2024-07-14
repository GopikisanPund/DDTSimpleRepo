package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	WebDriver driver;
	public Properties prop;

	public WebDriver openBrowser(String browserName) throws IOException {

		prop = new Properties();
		File file = new File("D:\\Selenium Projects\\DataDrivenFramework\\src\\test\\resources\\data.properties");
		try {
			FileInputStream fis = new FileInputStream(file);
			prop.load(fis);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (browserName == null) {
			throw new IllegalArgumentException("Browser name cannot be null");
		}

		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			throw new IllegalArgumentException("Unsupported browser: " + browserName);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(prop.getProperty("url"));

		return driver;
	}
}
