package com.josenaves.boogle.web;

import static spark.Spark.get;
import static spark.Spark.post;
import spark.servlet.SparkApplication;

import com.josenaves.boogle.service.IndexService;
import com.josenaves.boogle.util.JsonTransformer;
import com.josenaves.boogle.util.MatchesTransformer;

public class IndexController implements SparkApplication {

	public static final IndexService service = new IndexService();

	public static void main(String[] args) {
		new IndexController().init();
	}

	@Override
	public void init() {
		get("/hello", "application/json",
			(request, response) -> service.listDocuments(),
			new JsonTransformer());

		post("/index", "application/json", 
			(request, response) -> {
				service.indexDocument(request.body());
				response.status(201);
				return "ok";
			}, new JsonTransformer());

		get("/search", "application/json",
			(request, response) -> service.search(request.queryParams("q")),
			new MatchesTransformer());

	}
}