package com.qa.rahulshetty.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserRegistationPage extends BasePageObjects {

	// Constructor
	public UserRegistationPage(WebDriver driver) {
		super(driver);
	}

	// Locator
	@FindBy(xpath = "//input[@id='firstName']")
	WebElement txtFirstName;

	@FindBy(xpath = "//input[@id='lastName']")
	WebElement txtLastName;

	@FindBy(xpath = "//input[@id='userEmail']")
	WebElement txtEmail;

	@FindBy(xpath = "//input[@id='userMobile']")
	WebElement txtMobileNumber;

	@FindBy(xpath = "//select[@formcontrolname='occupation']")
	WebElement clkOccupation;

	@FindBy(xpath = "//option[normalize-space()='Engineer']")
	WebElement optionChooseForOccupation;

	@FindBy(xpath = "//input[@value='Male']")
	WebElement radioSelectMale;

	@FindBy(xpath = "//input[@value='Female']")
	WebElement radioSelectFemale;

	@FindBy(xpath = "//input[@id='userPassword']")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@id='confirmPassword']")
	WebElement txtConfirmPassword;

	@FindBy(xpath = "//input[@type='checkbox']")
	WebElement checkBox18Plus;

	@FindBy(xpath = "//input[@id='login']")
	WebElement btnSubmit;

	@FindBy(xpath = "//h1[normalize-space()='Account Created Successfully']")
	WebElement successfullyMsg;

	@FindBy(xpath = "//button[normalize-space()='Login']")
	WebElement btnLogin;

	// Action Methods
	public void setFisrtName() {
		txtFirstName.sendKeys(generateAlphabetic(1).toUpperCase() + generateAlphabetic(4));
	}

	public void setLastName() {
		txtLastName.sendKeys(generateAlphabetic(1).toUpperCase() + generateAlphabetic(4));
	}

	public void setEmail() {
		txtEmail.sendKeys(
				generateAlphabetic(1).toUpperCase() + generateAlphabetic(6) + generateNumeric(4) + "@gmail.com");
	}

	public void setMobileNumber() {
		txtMobileNumber.sendKeys(generateNumeric(10));
	}

	public void setOccupation() {
		clkOccupation.click();
	}

	public void setOccupationOptions() {
		optionChooseForOccupation.click();
	}

	public void setMaleGender() {
		radioSelectMale.click();
	}

	public void setFemaleGender() {
		radioSelectFemale.click();
	}

	public void setPassowrd() {
		txtPassword.sendKeys("Test@123");
	}

	public void setConfirmPassword() {
		txtConfirmPassword.sendKeys("Test@123");
	}

	public void tickCheckBox() {
		checkBox18Plus.click();
	}

	public void clkSubmitBtn() {
		implicitWaitElement(btnSubmit, 5);
		btnSubmit.click();
	}

	public String getConfimationMsg() {
		try {
			return (successfullyMsg.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}

	public void clkLoginBtn() {
		btnLogin.click();
	}

}
