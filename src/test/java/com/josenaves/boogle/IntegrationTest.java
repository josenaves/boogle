package com.josenaves.boogle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import spark.Spark;
import spark.utils.IOUtils;

import com.google.gson.Gson;
import com.josenaves.boogle.web.IndexController;

public class IntegrationTest {

	@BeforeClass
	public static void beforeClass() throws InterruptedException {
		IndexController.main(null);
		Thread.sleep(1000);
	}
	
	@AfterClass
	public static void afterClass() {
		Spark.stop();
	}

	@Test
	public void pageMustBeIndexed() {
		TestResponse res = request("POST", "/index", "{\"content\" : \"This is a journey into sound\", \"pageId\" : \"1\"}");

		//Map<String, String> json = res.json();
		assertEquals(201, res.status);
		//assertNotNull(json.get("id"));
	}

	private TestResponse request(String method, String path, String requestBody) {
		try {
			URL url = new URL("http://localhost:4567" + path);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);
			connection.setDoOutput(true);
			setJsonInRequestBody(connection, requestBody);
			connection.connect();

			String responseBody = IOUtils.toString(connection.getInputStream());
			return new TestResponse(connection.getResponseCode(), responseBody);
		} 
		catch (IOException e) {
			e.printStackTrace();
			fail("Sending request failed: " + e.getMessage());
			return null;
		}
	}
	
	private void setJsonInRequestBody(HttpURLConnection connection, String json) throws IOException {
		connection.setRequestProperty("Content-Type", "application/json");
		byte[] outputInBytes = json.getBytes("UTF-8");
		OutputStream os = connection.getOutputStream();
		os.write(outputInBytes);
		os.close();
	}

	private static class TestResponse {
		public final String body;
		public final int status;

		public TestResponse(int status, String body) {
			this.status = status;
			this.body = body;
		}

		public Map<String, String> json() {
			return new Gson().fromJson(body, HashMap.class);
		}
	}
}
