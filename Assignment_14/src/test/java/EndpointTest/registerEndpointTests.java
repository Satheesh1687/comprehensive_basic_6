package EndpointTest;

import java.io.IOException;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import Endpoint.RegisterEndPoint;

public class registerEndpointTests {

	@Test()
	public static void TestvalidUserRegister() throws IOException {

		Response response = RegisterEndPoint.registerUser();
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200, "Status Code mismatch for Positive Case!");
		int id = response.jsonPath().getInt("id");
		Assert.assertTrue(id > 0, "ID is not a valid integer!");
		String token = response.jsonPath().getString("token");
		Assert.assertNotNull(token, "Token is null!");
		Assert.assertTrue(token instanceof String, "Token is not a valid string!");
	}

	@Test()
	public static void TestInvaildUserRegister() throws IOException {

		Response response = RegisterEndPoint.registerinvaildUser();
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 400, "Status Code mismatch for Negative Case!");

		String errorMessage = response.jsonPath().getString("error");
		Assert.assertEquals(errorMessage, "Missing password", "Error message mismatch!");
	}

}
