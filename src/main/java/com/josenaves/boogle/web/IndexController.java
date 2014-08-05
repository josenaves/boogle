package com.josenaves.boogle.web;

import static spark.Spark.*;
import spark.Request;
import spark.Response;
import spark.Route;

public class IndexController implements spark.servlet.SparkApplication {

	public static void main(String[] args) {
		new IndexController().init();
	}

	@Override
	public void init() {
		get("/hello", (request, response) -> {
			response.status(200);
			return "Hello World !!!!";
		});

	}
	
}