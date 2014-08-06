package com.josenaves.boogle.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

public class IndexService {
	
	private static final HashMap<String, Integer> index = new HashMap<String, Integer>();

	public void indexDocument(String body) {
        Document doc = new Gson().fromJson(body, Document.class);
        // TODO must tokenize content and put each token on index
        index.put(doc.getContent(), doc.getPageId());
		return;
	}
	
	public List<Document> listDocuments() {
		List<Document> docs = new ArrayList<Document>();
		for (String content : index.keySet()) {
			docs.add(new Document(index.get(content), content));
		}
        return docs;
	}
}
