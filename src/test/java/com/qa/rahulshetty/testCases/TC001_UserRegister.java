package com.qa.rahulshetty.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.rahulshetty.pageObjects.UserLoginPage;
import com.qa.rahulshetty.pageObjects.UserRegistationPage;
import com.qa.rahulshetty.testBase.BaseTestClass;

public class TC001_UserRegister extends BaseTestClass {

	@Test(groups = { "regression", "master" })
	void verifyAccountRegister() throws InterruptedException {

		logger.info("****Starting TC001_UserRegisterTest****");

		try {
			// HomePage
//			js.executeScript("document.body.style.zoom='50%'");
//			logger.info("Zooming page into 50%");

			UserLoginPage hp = new UserLoginPage(driver);
			hp.registerPage();
			logger.info("Clicked on Register button");

			// RegisterPage
//			js.executeScript("document.body.style.zoom='50%'");
//			logger.info("Zooming page into 50%");

			UserRegistationPage urp = new UserRegistationPage(driver);
			logger.info("Providing user details...");
			urp.setFisrtName();
			urp.setLastName();
			urp.setEmail();
			urp.setMobileNumber();
			urp.setOccupation();
			urp.setOccupationOptions();
			urp.setMaleGender();
			urp.setPassowrd();
			urp.setConfirmPassword();
			urp.tickCheckBox();
			logger.info("Filled all the details..");

			Thread.sleep(300);
			urp.clkSubmitBtn();
			logger.info("Registation successfully completed.");

			logger.info("Validating Expected Message...");

			String msgConfirm = urp.getConfimationMsg();
			Assert.assertEquals(msgConfirm, "Account Created Successfully");

			urp.clkLoginBtn();

		} catch (Exception e) {
			logger.error("Test Failed...");
			;
			logger.debug("Debug logs...");
			Assert.fail();
		}
		logger.info("****Finished TC001_UserRegisterTest****");
	}

}
