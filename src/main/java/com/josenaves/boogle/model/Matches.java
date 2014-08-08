package com.josenaves.boogle.model;

import java.util.List;

public class Matches {
	private List<Match> matches;
    
	public Matches() {
	}

    public Matches(List<Match> matches) {
    	this.matches = matches;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}
}
