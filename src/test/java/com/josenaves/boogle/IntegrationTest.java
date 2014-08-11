package com.josenaves.boogle;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import retrofit.RestAdapter;
import spark.Spark;

import com.josenaves.boogle.api.BoogleApi;
import com.josenaves.boogle.model.Document;
import com.josenaves.boogle.model.Match;
import com.josenaves.boogle.model.Matches;
import com.josenaves.boogle.web.IndexController;

public class IntegrationTest {
	
	private static final String ENDPOINT_URL = "http://localhost:4567";

	private static RestAdapter restAdapter;
	
	private static BoogleApi api; 
	
	@BeforeClass
	public static void beforeClass() throws InterruptedException {
		IndexController.main(null);
		Thread.sleep(1000);
		
		restAdapter = new RestAdapter.Builder()
			.setEndpoint(ENDPOINT_URL)
			.build();
		
		api = restAdapter.create(BoogleApi.class);
	}

	@AfterClass
	public static void afterClass() {
		Spark.stop();
	}

	@Test
	public void pageMustBeIndexed() {
		String ret = api.indexDocument(new Document(1, "The quick brown fox jumped over the lazy dog."));
		assertEquals("ok", ret);
		
		ret = api.indexDocument(new Document(12, "Elementary is a famous tv serie"));
		assertEquals("ok", ret);

		ret = api.indexDocument(new Document(300, "Elementary, Dear Watson"));
		assertEquals("ok", ret);
	}
	
	@Test
	public void searchTest() {
		Matches matches = api.search("Elementary, dear watson");
		
		List<Match> result = matches.getMatches();
		assertEquals(2, result.size());
		
		Match m1 = result.get(0);
		Match m2 = result.get(1);
		
		assertEquals(new Integer(300), m1.getPageId());
		assertEquals(new Integer(3), m1.getScore());
		
		assertEquals(new Integer(12), m2.getPageId());
		assertEquals(new Integer(1), m2.getScore());
	}
}
