package com.josenaves.boogle.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.josenaves.boogle.model.Document;
import com.josenaves.boogle.model.Match;
import com.josenaves.boogle.model.Matches;

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

	public Matches search(String query) {
		Map<Integer, Integer> results = new HashMap<Integer, Integer>();
		
		// clean the input and split it
		String[] tokens = new TokenizerService(query)
			.removeDuplicates()
			.removePunctuation()
			.toLowerCase()
			.getTokens();
		
		// search terms in the index and compute the results
		for (String token : tokens) {
			Set<Integer> set = index.get(token);
			if (set != null) {
				for (Integer pageId : set) {
					Integer score = results.get(pageId);
					if (score != null) 
						results.put(pageId, ++score);
					else 
						results.put(pageId, 1);
				}
			}
		}
		
		List<Match> matches = new ArrayList<Match>();

		// put the results on list
		for (Integer pageId : results.keySet()) {
			matches.add(new Match(pageId, results.get(pageId)));
		}
		
		// sort the results by score
		Collections.sort(matches);
		
		return new Matches(matches);
	}
}
