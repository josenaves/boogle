package com.josenaves.boogle;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import retrofit.RestAdapter;
import spark.Spark;

import com.josenaves.boogle.api.BoogleApi;
import com.josenaves.boogle.model.Document;
import com.josenaves.boogle.web.IndexController;

public class IntegrationTest {
	
	private static final String ENDPOINT_URL = "http://0.0.0.0:4567";

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
		RestAdapter restAdapter = new RestAdapter.Builder()
			.setEndpoint(ENDPOINT_URL)
			.build();

		BoogleApi service = restAdapter.create(BoogleApi.class);
		String ret = service.indexDocument(new Document(1, "This is a journey into sound"));
		assertEquals(ret, "ok");
	}
}
