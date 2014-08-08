package com.josenaves.boogle.util;

import spark.ResponseTransformer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.josenaves.boogle.model.Matches;

public class MatchesTransformer implements ResponseTransformer {
	private Gson gson;
	
	public MatchesTransformer() {
		// Configure GSON
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Matches.class, new MatchesSerializer());
		gsonBuilder.setPrettyPrinting();
		this.gson = gsonBuilder.create();
	}

	@Override
	public String render(Object model) {
		return gson.toJson(model);
	}
}