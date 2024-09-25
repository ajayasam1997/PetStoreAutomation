package api.endPoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payLoad.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//UserEndPoints.java
//Create to perform Create, Read, Update, Delete requests to the user api

public class UserEndPoints2 {

	static ResourceBundle getURL() {
		ResourceBundle routes = ResourceBundle.getBundle("routes");// load properties file //name of properties file
		return routes;

	}

	public static Response createUser(User payload) {
		String post_url = getURL().getString("post_url");

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload).when()
				.post(Routes.post_url);

		return response;
	}

	public static Response readUser(String userName) {

		String get_url = getURL().getString("get_url");
		Response response = given().pathParam("username", userName).when().get(Routes.get_url);

		return response;

	}

	public static Response updateUser(String userName, User payload) {
		String update_url = getURL().getString("update_url");

		Response response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.pathParam("username", userName).when().put(Routes.put_url);

		return response;

	}

	public static Response deleteUser(String userName) {
		String delete_url = getURL().getString("delete_url");

		Response response = given().pathParam("username", userName).when().delete(Routes.delete_url);

		return response;

	}

}
