package com.qa.rahulshetty.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.rahulshetty.pageObjects.MyHomePage;
import com.qa.rahulshetty.pageObjects.UserLoginPage;
import com.qa.rahulshetty.testBase.BaseTestClass;
import com.qa.rahulshetty.utilities.DataProviders;

public class TC003_LoginDDT extends BaseTestClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "dataDriven") 
	// getting data provider form different
	public void verifyLoginExcel(String email, String pwd, String expctectedResult) {

		logger.info("-->Starting TC003_LoginDDT");
		try {
			UserLoginPage ulp = new UserLoginPage(driver);
			ulp.setEmail(email);
			ulp.setPassword(pwd);
			ulp.getLogin();

			MyHomePage mhp = new MyHomePage(driver);
			boolean txtVerifyOnHomePage = mhp.isMyHomePageExists();

			Assert.assertTrue(txtVerifyOnHomePage);

			if (expctectedResult.equalsIgnoreCase("Valid")) {
				if (txtVerifyOnHomePage == true) {
					mhp.logoutAccount();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}

				if (expctectedResult.equalsIgnoreCase("Invalid")) {
					if (txtVerifyOnHomePage == true) {
						mhp.logoutAccount();
						Assert.assertTrue(false);
					} else {
						Assert.assertTrue(true);
					}
				}

			}
		} catch (Exception e) {
			Assert.fail();
		}
		logger.info("-->Finished TC003_LoginDDT");
	}

}
