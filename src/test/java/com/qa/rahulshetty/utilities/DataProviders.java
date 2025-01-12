package com.qa.rahulshetty.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	// DataProvider 1
	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException {		//all object replace by string by reference day44
		String path = ".\\test-data\\loginData.xlsx"; // taking xl file from test-data

		ExcelUtility xlutil = new ExcelUtility(path); // creating an object for xlutility

		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 1);

		String logindata[][] = new String[totalrows][totalcols]; // created for two dimension array which can store

		for (int i = 1; i <= totalrows; i++) { // 1 //read data from xl storing in two dimensional array
			for (int j = 0; j < totalcols; j++) { // 0 //1 is rows j is cols
				logindata[i - 1][j] = xlutil.getCellData("Sheet1", i, j); // 1,0
			}
		}
		return logindata; // return two dimension array
	}

	// DataProvider 2

	// DataProvider 3

	// DataProvider 4

	// DataProvider 5
}
