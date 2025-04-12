package com.mahipaul.notes_application.controller;

import com.mahipaul.notes_application.dto.ApiRes;
import com.mahipaul.notes_application.dto.TodoDto;
import com.mahipaul.notes_application.service.TodoService;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/todos")
public class TodoController {

  @Autowired private TodoService todoService;

  // Create ToDo
  @PostMapping("/add-todo")
  public ResponseEntity<TodoDto> createToDo(
      @RequestBody TodoDto toDoDto, @RequestParam("email") String email) {
    TodoDto createdToDo = todoService.createToDo(toDoDto, email);
    return new ResponseEntity<>(createdToDo, HttpStatus.CREATED);
  }

  // Update ToDo
  @PutMapping("/update-todo")
  public ResponseEntity<TodoDto> updateToDo(
      @RequestBody TodoDto toDoDto, @RequestParam("todoId") UUID toDoId) {
    UUID userId = toDoDto.getUserDto().getId();
    TodoDto updatedToDo = todoService.updateToDo(toDoDto, toDoId, userId);
    return new ResponseEntity<>(updatedToDo, HttpStatus.OK);
  }

  // Delete ToDo
  @DeleteMapping("/delete-todo/{todoId}")
  public ResponseEntity<ApiRes> deleteToDo(@PathVariable UUID todoId) {
    todoService.deleteToDo(todoId);
    return new ResponseEntity<>(new ApiRes("ToDo deleted", true), HttpStatus.OK);
  }

  // Get ToDo by ID
  @GetMapping("/{todoId}")
  public ResponseEntity<TodoDto> getToDoById(@PathVariable UUID todoId) {
    TodoDto toDo = todoService.getToDoById(todoId);
    return new ResponseEntity<>(toDo, HttpStatus.OK);
  }

  // Get ToDo by User
  @GetMapping("/user/{email}")
  public ResponseEntity<List<TodoDto>> getToDoByUser(@PathVariable String email) {
    List<TodoDto> toDos = todoService.getToDoByUser(email);
    return new ResponseEntity<>(toDos, HttpStatus.OK);
  }

  // Get all ToDos
  @GetMapping("/get-all-todos")
  public ResponseEntity<List<TodoDto>> getAllToDos() {
    List<TodoDto> toDos = todoService.getAllToDos();
    return new ResponseEntity<>(toDos, HttpStatus.OK);
  }
}
