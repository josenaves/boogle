package com.josenaves.boogle.util;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.josenaves.boogle.model.Match;
import com.josenaves.boogle.model.Matches;

public class MatchesSerializer implements JsonSerializer<Matches> {
	
	@Override
	public JsonElement serialize(Matches matches, Type typeOfSrc, JsonSerializationContext context) {

		final JsonArray array = new JsonArray();
        for (final Match match : matches.getMatches()) {
        	JsonObject json = new JsonObject();
            json.addProperty("pageId", match.getPageId());
            json.addProperty("score", match.getScore());
            array.add(json);
        }
        JsonObject root = new JsonObject();
        root.add("matches", array);
        
        return root;
	}
}