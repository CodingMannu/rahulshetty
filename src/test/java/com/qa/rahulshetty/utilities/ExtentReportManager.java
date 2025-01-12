package com.qa.rahulshetty.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.rahulshetty.testBase.BaseTestClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String repName;

	public void onStart(ITestContext testContext) {

//		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
//		Date dt = new Date();
//		String currentdatetimestamp = df.format(dt);

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		repName = "Test-Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

//		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/myreports.html");

		sparkReporter.config().setDocumentTitle("rahulshetty Automation Report"); // Title of the report
		sparkReporter.config().setReportName("rahulshetty Functional Testing"); // Name of the report
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "rahulshetty");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customer");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environement", "Software QA");

		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}

	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName()); // Create new entry in the report
		test.assignCategory(result.getMethod().getGroups()); // To display groups in report
		test.log(Status.PASS, result.getName() + " got successfully executed"); // Update status p/f/s
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName() + " got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());

		try {
			String imgPath = new BaseTestClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());

	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
		File extentReport = new File(pathOfExtentReport);

		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
/*
		try {
			URL url = new URL("file:///" + System.getProperty("user.dir") + "\\reports\\" + repName);
			// Create the email message
			ImageHtmlEmail email = new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com", "password"));
			email.setSSLOnConnect(true);
			email.setFrom("pavanoltraining@gmail.com"); // Sender
			email.setSubject("Test Results");
			email.setMsg("Please find Attached report....");
			email.addTo("pavankumar.busyqa@gmail.com"); // Receiver
			email.attach(url, "extent report", "please check report...");
			email.send(); // send the email
		} catch (Exception e) {
			e.printStackTrace();
		}
*/
	}

}
