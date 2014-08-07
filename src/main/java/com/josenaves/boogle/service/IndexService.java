package com.josenaves.boogle.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.josenaves.boogle.model.Document;
import com.josenaves.boogle.model.Match;

public final class IndexService {
	
	private static final Map<String, Set<Integer>> index = new HashMap<String, Set<Integer>>();

	public void indexDocument(String body) {
        Document doc = new Gson().fromJson(body, Document.class);
        
        String[] tokens = new TokenizerService(doc.getContent())
        	.removePunctuation()
        	.toLowerCase()
        	.removeDuplicates()
        	.getTokens();
        
        for (String token : tokens) {
    		Set<Integer> pages;
        	if (index.get(token) == null) {
        		pages = new HashSet<Integer>();
        	}
        	else {
        		pages = index.get(token);
        	}
    		pages.add(doc.getPageId());
    		index.put(token, pages);
        }
		return;
	}
	
	public Map<String, Set<Integer>> listDocuments() {
        return index;
	}

	public List<Match> search(String body) {
		List<Match> list = new ArrayList<Match>();
		list.add(new Match());
		
		return null;
	}
}
