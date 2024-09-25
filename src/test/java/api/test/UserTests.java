package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endPoints.UserEndPoints;
import api.payLoad.User;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;

	User userPayload;

	@BeforeClass
	public void setUpData() {

		faker = new Faker();

		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUserName(faker.name().username());

		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());

		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

	}

	@Test(priority = 1)
	public void testPostUser() {

		Response response = UserEndPoints.createUser(userPayload);

		response.then().log().all();

		String body = response.getBody().toString();
		System.out.println(body);

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 2)
	public void testGetUserByName() {

		Response response = UserEndPoints.readUser(this.userPayload.getUserName());

		response.then().log().all();

		int code = response.statusCode();
		System.out.println("statusCode is " + code);

		Assert.assertEquals(response.getStatusCode(), 404);// Actual, Expected
	}

	@Test(priority = 3)
	public void testUpdateUser() {

		// Update data using payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());

		userPayload.setEmail(faker.internet().safeEmailAddress());

		Response response = UserEndPoints.updateUser(this.userPayload.getUserName(), userPayload);

		response.then().log().all();

		String body = response.getBody().toString();
		System.out.println(body);

		Assert.assertEquals(response.getStatusCode(), 200);

		// Checking data after update

		Response responseAfterUpdate = UserEndPoints.readUser(this.userPayload.getUserName());

		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);

	}

	
	
	public void testDeleteUserByName() {
		
		Response response =UserEndPoints.deleteUser(this.userPayload.getUserName());
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	
	
}
