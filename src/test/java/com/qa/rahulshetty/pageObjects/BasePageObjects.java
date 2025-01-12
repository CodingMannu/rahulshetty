package com.qa.rahulshetty.pageObjects;

import java.security.SecureRandom;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObjects {

	WebDriver driver;
	WebDriverWait wait;

	public BasePageObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void implicitWaitElement(WebElement webElement, int seconds) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}

	public String ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	public String NUMBERS = "123456789";
	public String ALPHANUMERIC = ALPHABETS + NUMBERS;
	public SecureRandom RANDOM = new SecureRandom();

	// Generate a random alphabetic string
	public String generateAlphabetic(int length) {
		return generateRandomString(ALPHABETS, length);
	}

	// Generate a random numeric string
	public String generateNumeric(int length) {
		return generateRandomString(NUMBERS, length);
	}

	// Generate a random alphanumeric string
	public String generateAlphanumeric(int length) {
		return generateRandomString(ALPHANUMERIC, length);
	}

	// Generic method to generate random strings
	public String generateRandomString(String characterSet, int length) {
		StringBuilder result = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int randomIndex = RANDOM.nextInt(characterSet.length());
			result.append(characterSet.charAt(randomIndex));
		}
		return result.toString();
	}

}
