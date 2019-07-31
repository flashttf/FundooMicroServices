package com.fundoomicro.noteoperations.service;

import java.util.List;

import com.fundoomicro.noteoperations.dto.NoteDto;
import com.fundoomicro.noteoperations.dto.Response;
import com.fundoomicro.noteoperations.model.Note;



public interface INoteService {
	Response createNote(NoteDto noteDto,String token);
	Response updateNote(NoteDto noteDto,String token,String noteId);
	List<Note> getAllNotes(String token);
}
