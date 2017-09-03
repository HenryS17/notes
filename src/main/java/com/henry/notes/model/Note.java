package com.henry.notes.model;

public class Note {
	private long id;
	private String body;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	
	public Note(long id, String body) {
		this.id = id;
		this.body = body;
	}

	public Note() {}	

}
