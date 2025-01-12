package com.qa.rahulshetty.testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger; //Log4j
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTestClass {

	public static WebDriver driver;
	
	public JavascriptExecutor js;
	public Logger logger;
	public Properties propertiesFile;

	@BeforeClass(groups = { "regression", "sanity", "master", "dataDriven" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {

		// Loading config.properties file
		FileReader file = new FileReader("./src//test//resource//config.properties");
		propertiesFile = new Properties();
		propertiesFile.load(file);

		logger = LogManager.getLogger(this.getClass()); // Log4j

//		if (propertiesFile.getProperty("execution_env").equalsIgnoreCase("local")) {
//			DesiredCapabilities capabilities = new DesiredCapabilities();

			// os
//			if (os.equalsIgnoreCase("windows")) {
//				capabilities.setPlatform(Platform.WIN10);
//			} else if (os.equalsIgnoreCase("mac")) {
//				capabilities.setPlatform(Platform.MAC);
//			} else {
//				System.out.println("No matching os");
//				return;
//			}
//
//			// browser
//			switch (br.toLowerCase()) {
//			case "chrome":
//				ChromeOptions chromeOptions = new ChromeOptions();
//				chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
//				driver = new ChromeDriver(chromeOptions);
//				break;
//
//			case "edge":
//				EdgeOptions edgeOptions = new EdgeOptions();
//				edgeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
//				driver = new EdgeDriver(edgeOptions);
//				break;
//
//			case "firefox":
//				EdgeOptions firefoxOptions = new EdgeOptions();
//				firefoxOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
//				driver = new EdgeDriver(firefoxOptions);
//				break;
//
//			default:
//				System.out.println("Invaild broswer name..");
//				return;
//			}

//			driver = new RemoteWebDriver(new URL("http://localhost:4444/wb/hub"), capabilities);
//		}

		switch (br.toLowerCase()) {
		case "chrome":
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
			driver = new ChromeDriver(chromeOptions);
			break;

		case "edge":
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
			driver = new EdgeDriver(edgeOptions);
			break;
			
		case "firefox":
			EdgeOptions firefoxOptions = new EdgeOptions();
			firefoxOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
			driver = new EdgeDriver(firefoxOptions);
			break;
	
			
		default:
			System.out.println("Invaild broswer name..");
			return;
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();
		driver.get(propertiesFile.getProperty("rahulshetty_true_URL"));
		driver.manage().window().maximize();
		logger.info("BeforeClass is executed...");
	}

	@AfterClass(groups = { "regression", "sanity", "master", "dataDriven"})
	public void tearDown() {
		driver.quit();
		logger.info("AfterClass is executed...");
	}

	public String captureScreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;

	}

}
