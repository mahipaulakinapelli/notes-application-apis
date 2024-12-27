package com.mahipaul.notes_application.service;

import com.mahipaul.notes_application.dto.NotesDto;
import com.mahipaul.notes_application.dto.UserDto;
import com.mahipaul.notes_application.model.Notes;
import com.mahipaul.notes_application.model.User;
import com.mahipaul.notes_application.repository.NoteRepository;
import com.mahipaul.notes_application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NotesService {

    @Autowired
    private NoteRepository notesRepo;

    @Autowired
    private UserRepository userRepo;

    //create
    public NotesDto createNote(NotesDto notesDto, String email) {

        Optional<User> user=userRepo.findByEmail(email);

//        notesDto.setId(UUID.randomUUID());

        Notes notes= DtoToNotes(notesDto);

        user.ifPresent(notes::setUser);

        Notes res=this.notesRepo.save(notes);
        return this.NotesToDto(res);
    }

    //update
    public NotesDto updateNote(NotesDto notesDto, UUID notesId) {
        Notes notes= this.notesRepo.findById(notesId).orElseThrow(()->new ResourceAccessException("not found"));
        notes.setTitle(notesDto.getTitle());
        notes.setDescription(notesDto.getDescription());
        Notes res=this.notesRepo.save(notes);
        return this.NotesToDto(res);
    }

    // delete
    public void deleteNote(UUID notesId) {
        Notes notes=this.notesRepo.findById(notesId).orElseThrow();
        this.notesRepo.delete(notes);
    }

    //get
    public NotesDto getNote(UUID notesId) {
        Notes notes=this.notesRepo.findById(notesId).orElseThrow();
        return this.NotesToDto(notes);
    }

    //get by user
    public List<NotesDto> getNoteByUser(String email) {
        Optional<User> user=userRepo.findByEmail(email);
        List<Notes> notes = this.notesRepo.findByUser(user);
        System.out.println(user);
        List<NotesDto> allNotes= notes.stream().map(this::NotesToDto).collect(Collectors.toList());
        return allNotes;
    }

    //get all
    public List<NotesDto> getAllNote() {
        List<Notes> notes = this.notesRepo.findAll();
        List<NotesDto> allNotes= notes.stream().map((note)->this.NotesToDto(note)).collect(Collectors.toList());
        return allNotes;
    }

    public NotesDto NotesToDto(Notes notes ) {
        NotesDto notesDto= new NotesDto();
        notesDto.setId(notes.getId());
        notesDto.setTitle(notes.getTitle());
        notesDto.setDescription(notes.getDescription());

        return notesDto;
    }

    public Notes DtoToNotes(NotesDto notesDto ) {
        Notes notes= new Notes();
        notes.setId(notesDto.getId());
        notes.setTitle(notesDto.getTitle());
        notes.setDescription(notesDto.getDescription());
        notes.setDate(new Date());
        notes.setUser(DtoToUser(notesDto.getUserDto()));
        return notes;
    }

    public User DtoToUser(UserDto userDto ) {
        User user= new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto UserToDto(User user ) {
        UserDto userDto= new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
