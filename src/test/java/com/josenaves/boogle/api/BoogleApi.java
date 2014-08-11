package com.josenaves.boogle.api;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

import com.josenaves.boogle.model.Document;
import com.josenaves.boogle.model.Matches;

public interface BoogleApi {
	@POST("/index")
	String indexDocument(@Body Document document);
	
	@GET("/search")
	Matches search(@Query("q") String searchTerms);
}
