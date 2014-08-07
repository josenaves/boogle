package com.josenaves.boogle.model;

public class Match {
	private PageScore pageScore;

	public Match() {
	}

	public Match(PageScore pg) {
		this.pageScore = pg;
	}

	public PageScore getPageScore() {
		return pageScore;
	}

	public void setPageScore(PageScore pageScore) {
		this.pageScore = pageScore;
	}
}
