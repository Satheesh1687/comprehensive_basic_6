package Endpoint;

import java.io.IOException;

import org.json.JSONObject;

import Routes.routes;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.ExcelUtil;
import static io.restassured.RestAssured.given;

public class RegisterEndPoint {

	static String path = "./src/test/java/resources/TestData/test_data.xlsx";
	static ExcelUtil xl = new ExcelUtil(path);

	public static Response registerUser() throws IOException {

		String email = xl.getCellData("registerAcc", 1, 0);
		String password = xl.getCellData("registerAcc", 1, 1);

		JSONObject payload = new JSONObject();
		payload.put("email", email);
		payload.put("password", password);

		String registerUrl = routes.registration_url;

		Response response = given().contentType(ContentType.JSON).body(payload.toString()).when().post(registerUrl);

		return response;
	}

	public static Response registerinvaildUser() throws IOException {

		String email = xl.getCellData("registerAcc", 2, 0);
		String password = xl.getCellData("registerAcc", 2, 1);
		JSONObject payload = new JSONObject();
		payload.put("email", email);
		payload.put("password", password);
		String registerUrl = routes.registration_url;

		Response response = given().contentType(ContentType.JSON).body(payload.toString()).when().post(registerUrl);
		return response;
	}

}
