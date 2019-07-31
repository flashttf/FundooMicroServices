package com.fundoomicro.noteoperations.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Note {
	
	@Id
	private String noteId;
	private String userId;
	private String title;
	private String description;
	
	public String getNoteId() {
		return noteId;
	}
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Note(String noteId, String userId, String title, String description) {
		
		this.noteId = noteId;
		this.userId = userId;
		this.title = title;
		this.description = description;
	}
	
	
	public Note() {
		
	}
	
	
}