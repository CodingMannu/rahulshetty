package com.qa.rahulshetty.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyHomePage extends BasePageObjects {

	// constructor
	public MyHomePage(WebDriver driver) {
		super(driver);
	}

	// locator
	@FindBy(xpath = "//section[@id='sidebar']//p[1]")
	WebElement checkHomePage;

	@FindBy(xpath = "//button[normalize-space()='Sign Out']")
	WebElement clkLogoutBtn;

	// actions methods
	public boolean isMyHomePageExists() {
		try {
			return (checkHomePage.isDisplayed());
		} catch (Exception e) {
			return false;
		}
	}

	public void logoutAccount() {
		clkLogoutBtn.click();
	}

}
