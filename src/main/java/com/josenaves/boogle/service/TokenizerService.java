package com.josenaves.boogle.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TokenizerService {
	
	private String token;
	
	public TokenizerService(String str) {
		this.token = str;
	}
	
	public TokenizerService removePunctuation() {
		token = token.replaceAll("[^\\w\\s]","");
		return this;
	}
	
	public TokenizerService toLowerCase() {
		token = token.toLowerCase();
		return this;
	}
 
	public TokenizerService removeDuplicates() {
		Set<String> set = new HashSet<String>(Arrays.asList(getTokens())) ;
		String tokens[] = set.toArray(new String[set.size()]);
		token = String.join(" ", tokens);
		return this;
	}
	
	public String[] getTokens() {
		return token.split("\\s+");
	}
}
