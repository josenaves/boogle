package com.josenaves.boogle.web;

import static spark.Spark.get;
import static spark.Spark.post;
import spark.servlet.SparkApplication;

public class IndexController implements SparkApplication {

	public static final IndexService service = new IndexService();
	
	public static void main(String[] args) {
		new IndexController().init();
	}

	@Override
	public void init() {
		get("/hello", "application/json", (request, response) -> service.listDocuments(), new JsonTransformer());
		
		post("/index", "application/json", (request, response) -> {
			service.indexDocument(request.body());
			response.status(201);
			return "ok";
		}, new JsonTransformer());
		
		get("/search?q=:query", "application/json", (request, response) -> {
			response.status(200);
		    return "Hello: " + request.params(":query");
		}, new JsonTransformer());

	}
	
}