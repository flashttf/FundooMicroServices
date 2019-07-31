package com.fundoomicro.noteoperations.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fundoomicro.noteoperations.model.Note;

public interface INoteRepository extends MongoRepository<Note, String> {
	Optional<Note> findByNoteIdAndUserId(String noteId, String userId);

	List<Note> findByUserId(String userId);
}
