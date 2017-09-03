package com.henry.notes.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.henry.notes.model.Note;
import com.henry.notes.model.Notes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("api/")
public class NotesController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private Notes notes;
		
	public NotesController() {
		notes = new Notes();
	}
	@RequestMapping(value="notes", method=RequestMethod.POST)
	public Note create(@RequestBody HashMap<String, String> jsonNote) {
		log.info("Post request from user");
		log.info(jsonNote.get("body").toString());
		return (notes.create(jsonNote.get("body").toString()));
	}
	
	@RequestMapping(value="notes/{id}", method=RequestMethod.GET)
	public Note get(@PathVariable Long id) {
		System.out.println(id);
      return notes.get(id.longValue());
	}
	@RequestMapping(value="notes", method=RequestMethod.GET)
	public List<Note> get(@RequestParam(value="query", defaultValue="")  String s) {
		
		log.info(s);
		if (s.isEmpty()) {
			return notes.getNotes(); 
		}
		return notes.query(s);
	}
}
