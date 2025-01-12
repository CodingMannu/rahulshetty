package com.qa.rahulshetty.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.rahulshetty.pageObjects.MyHomePage;
import com.qa.rahulshetty.pageObjects.UserLoginPage;
import com.qa.rahulshetty.testBase.BaseTestClass;

public class TC002_LoginTest extends BaseTestClass {

	@Test(groups = { "sanity", "master" })
	public void verifyLogin() {
		logger.info("-->Starting TC002_LoginTest");

		try {
			UserLoginPage ulp = new UserLoginPage(driver);
			ulp.setEmail(propertiesFile.getProperty("email"));
			ulp.setPassword(propertiesFile.getProperty("password"));
			ulp.getLogin();

			MyHomePage mhp = new MyHomePage(driver);
			boolean txtVerifyOnHomePage = mhp.isMyHomePageExists();
			logger.info("login user account...");

//		Assert.assertEquals(txtVerifyOnHomePage, true, "Login Faild");
			Assert.assertTrue(txtVerifyOnHomePage);

			mhp.logoutAccount();
			logger.info("Logout user account...");
		} catch (Exception e) {
			Assert.fail();
		}

		logger.info("-->Finished TC002_LoginTest");
	}
}
