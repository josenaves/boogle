package com.josenaves.boogle.model;

public final class Document {
	private Integer pageId;
	private String content;

	public Document(Integer pageId, String content) {
		this.pageId = pageId;
		this.content = content;
	}
	
	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer page) {
		this.pageId = page;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((pageId == null) ? 0 : pageId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Document other = (Document) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (pageId == null) {
			if (other.pageId != null)
				return false;
		} else if (!pageId.equals(other.pageId))
			return false;
		return true;
	}

	
}
