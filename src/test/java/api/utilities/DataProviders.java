package api.utilities;

import api.utilities.XLUtility;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	// DataProvider 1

	@DataProvider(name = "Data")
	public String[][] getData() throws IOException {
		String path = ".\\testData\\RegTestData.xlsx";// taking xl file from testData

		XLUtility xlutil = new XLUtility(path);// creating an object for XLUtility

		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 1);

		String apiData[][] = new String[totalrows][totalcols];// created for two dimension array which can store the
																// data user and password

		for (int i = 1; i <= totalrows; i++) // 1 //read the data from xl storing in two deminsional array
		{
			for (int j = 0; j < totalcols; j++) // 0 i is rows j is col
			{
				apiData[i - 1][j] = xlutil.getCellData("Sheet1", i, j); // 1,0
			}
		}
		return apiData;// returning two dimension array

	}

	// DataProvider 2
	//Here only user names are returned

	@DataProvider(name = "Usernames")
	public String[] getUserNames() throws IOException {
		String path = ".\\testData\\RegTestData.xlsx";// taking xl file from testData

		XLUtility xlutil = new XLUtility(path);// creating an object for XLUtility
		int totalrows = xlutil.getRowCount("Sheet1");

		String apiData[] = new String[totalrows];// created for two dimension array which can store the
												// data user and password

		for (int i = 1; i < totalrows; i++) // 1 //read the data from xl storing in two deminsional array

		{
			apiData[i - 1] = xlutil.getCellData("Sheet1", i, 1); // 1,0
		}

		return apiData;// returning two dimension array

	}

	// DataProvider 3

	// DataProvider 4
}
