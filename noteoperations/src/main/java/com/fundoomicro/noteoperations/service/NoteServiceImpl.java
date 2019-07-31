package com.fundoomicro.noteoperations.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.fundoomicro.noteoperations.dto.NoteDto;
import com.fundoomicro.noteoperations.dto.Response;
import com.fundoomicro.noteoperations.model.Note;
import com.fundoomicro.noteoperations.repository.INoteRepository;
import com.fundoomicro.noteoperations.utility.ITokenGenerator;
import com.fundoomicro.noteoperations.utility.ResponseUtility;

@Component
@PropertySource("classpath:message.properties")
public class NoteServiceImpl implements INoteService {
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private Environment environment;

	

	
	
	@Autowired
	private ITokenGenerator tokenGenerator;

	@Autowired
	private INoteRepository iNoteRepository;
	
	@Override
	public Response createNote(NoteDto noteDto, String token) {
		String userId=tokenGenerator.verifyToken(token);
		Note note=modelMapper.map(noteDto, Note.class);
		note.setUserId(userId);
		iNoteRepository.save(note);
		Response response=ResponseUtility.getResponse(200, "", environment.getProperty("note.create.success"));
		return response;
		
			
	}

	@Override
	public Response updateNote(NoteDto noteDto, String token, String noteId) {
		String id=tokenGenerator.verifyToken(token);
		Optional<Note> isNote=iNoteRepository.findByNoteIdAndUserId(noteId, id);
		if(isNote.isPresent()) {
			isNote.get().setTitle(noteDto.getTitle());
			isNote.get().setDescription(noteDto.getDescription());
			iNoteRepository.save(isNote.get());
			Response response=ResponseUtility.getResponse(200, "", environment.getProperty("note.update.success"));
			return response;
		}else {
			Response response=ResponseUtility.getResponse(204, "", environment.getProperty("note.update.failed"));
			return response;
		}
	}

	@Override
	public List<Note> getAllNotes(String token) {
		String userId = tokenGenerator.verifyToken(token);
		List<Note> notes = iNoteRepository.findByUserId(userId);
		List<Note> notesList = new ArrayList<>();
		for (Note userNotes : notes) {
			Note note = modelMapper.map(userNotes, Note.class);
			notesList.add(note);
		}
		return notesList;

	}
}
