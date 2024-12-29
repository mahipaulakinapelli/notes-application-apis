package com.mahipaul.notes_application.controller;

import com.mahipaul.notes_application.dto.ApiRes;
import com.mahipaul.notes_application.dto.NotesDto;
import com.mahipaul.notes_application.dto.UserDto;
import com.mahipaul.notes_application.repository.NoteRepository;
import com.mahipaul.notes_application.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private NotesService notesService;

    //create
    @PostMapping("/add-notes")
    public ResponseEntity<NotesDto> createNote(@RequestBody NotesDto notes, @RequestParam("email") String email) {
        NotesDto note = notesService.createNote(notes, email);
        return new ResponseEntity<>(note, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/update-notes")
    public ResponseEntity<NotesDto> updateNote(@RequestBody NotesDto notes, @RequestParam("noteId") UUID notesId) {
        UserDto userDto = notes.getUserDto();
        UUID userId = userDto.getId();
        NotesDto updatedNote = notesService.updateNote(notes, notesId, userId);

        return new ResponseEntity<>(updatedNote, HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/delete-note/{notesId}")
    public ResponseEntity<ApiRes> deleteNote(@PathVariable UUID notesId) {
        notesService.deleteNote(notesId);
        return new ResponseEntity<>(new ApiRes("Note deleted", true), HttpStatus.OK);
    }

    //get by user
    @GetMapping("/{userId}")
    public ResponseEntity<List<NotesDto>> getNotesByUser(@PathVariable String email) {
        List<NotesDto> Note = notesService.getNoteByUser(email);
        return new ResponseEntity<>(Note, HttpStatus.OK);
    }

    //getAll
    @GetMapping("/get-all-notes")
    public ResponseEntity<List<NotesDto>> getNotes() {
        List<NotesDto> Note = notesService.getAllNote();
        return new ResponseEntity<>(Note, HttpStatus.OK);
    }
}