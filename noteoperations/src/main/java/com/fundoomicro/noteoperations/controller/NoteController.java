package com.fundoomicro.noteoperations.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fundoomicro.noteoperations.dto.NoteDto;
import com.fundoomicro.noteoperations.dto.Response;
import com.fundoomicro.noteoperations.model.Note;
import com.fundoomicro.noteoperations.service.INoteService;

@RestController
@RequestMapping("/noteController")
public class NoteController {
	
	@Autowired
	private INoteService noteService;
	
	RestTemplate restTemplate=new RestTemplate();
	
	@PostMapping("/create")
	public ResponseEntity<Response> createNote(@RequestBody NoteDto noteDto,@RequestHeader String token){
		boolean isUser=restTemplate.getForObject("http://localhost:9095/usercontroller/usercheck/"+token, Boolean.class);
		System.out.println("hhhh");
		if(isUser) {
			Response response=noteService.createNote(noteDto, token);
			return new ResponseEntity<Response>(response,HttpStatus.OK);	
		}else {
			Response response=new Response();
			return new ResponseEntity<Response>(response,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/getAllNotes")
	public List<Note> getAllNotes(@RequestHeader String token){
		return noteService.getAllNotes(token);
		
	}
}
