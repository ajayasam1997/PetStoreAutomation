package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endPoints.UserEndPoints;
import api.payLoad.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	
	public Logger logger;
	

	// Create multiple posts

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUseer(String userId, String username, String fName, String lName, String userEmail, String pwd,
			String phno) {
		
		logger = LogManager.getLogger(this.getClass());// Log4j
		
		logger.info("Post Started");

		// Creating payload using POJO Class

		User userpayload = new User();

		Faker faker = new Faker();

		userpayload.setId(Integer.parseInt(userId));
		userpayload.setUserName(username);
		userpayload.setFirstName(fName);
		userpayload.setLastName(lName);
		userpayload.setEmail(userEmail);
		userpayload.setPassword(pwd);
		userpayload.setPhone(phno);

		Response response = UserEndPoints.createUser(userpayload);
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("Post Ended");

	}

	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String userName) {

		Response response = UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.statusCode(), 200);

	}

}
