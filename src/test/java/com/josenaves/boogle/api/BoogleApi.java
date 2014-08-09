package com.josenaves.boogle.api;

import retrofit.http.Body;
import retrofit.http.POST;

import com.josenaves.boogle.model.Document;

public interface BoogleApi {
	@POST("/index")
	String indexDocument(@Body Document document);
}
