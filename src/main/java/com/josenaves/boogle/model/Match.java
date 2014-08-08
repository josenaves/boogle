package com.josenaves.boogle.model;

public class Match {
	private Integer pageId;
	private Integer score;
    
    public Match() {
	}

    public Match(Integer pageId, Integer score) {
    	this.pageId = pageId;
    	this.score = score;
	}
    
	public Integer getPageId() {
		return pageId;
	}


	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}


	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}
