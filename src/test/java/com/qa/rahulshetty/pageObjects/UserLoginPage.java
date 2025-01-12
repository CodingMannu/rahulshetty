package com.qa.rahulshetty.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserLoginPage extends BasePageObjects {

	// Constructor
	public UserLoginPage(WebDriver driver) {
		super(driver);
	}

	// Locator 
	//register
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement clkRegister;
	
	//login
	@FindBy(xpath ="//input[@id='userEmail']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='userPassword']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='login']")
	WebElement clkLoginBtn;
	
	// Action Methods
	public void registerPage() {
		implicitWaitElement(clkRegister, 5);
		clkRegister.click();
	}
	
	//login actions
	public void setEmail(String email) {
		implicitWaitElement(txtEmail, 5);
		txtEmail.sendKeys(email);
	}
	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void getLogin() {
		clkLoginBtn.click();
	}
}
