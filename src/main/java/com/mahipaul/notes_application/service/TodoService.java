package com.mahipaul.notes_application.service;

import com.mahipaul.notes_application.dto.TodoDto;
import com.mahipaul.notes_application.dto.UserDto;
import com.mahipaul.notes_application.model.Todo;
import com.mahipaul.notes_application.model.User;
import com.mahipaul.notes_application.repository.TodoRepository;
import com.mahipaul.notes_application.repository.UserRepository;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TodoService {

  @Autowired private TodoRepository toDoRepo;

  @Autowired private UserRepository userRepo;

  // Create ToDo
  public TodoDto createToDo(TodoDto toDoDto, String email) {
    Optional<User> user = userRepo.findByEmail(email);
    Todo toDo = DtoToToDo(toDoDto);
    user.ifPresent(toDo::setUser);
    Todo res = toDoRepo.save(toDo);
    return ToDoToDto(res);
  }

  // Update ToDo
  public TodoDto updateToDo(TodoDto toDoDto, UUID toDoId, UUID userId) {
    Todo toDo = toDoRepo.findByUserIdAndId(userId, toDoId);
    toDo.setTitle(toDoDto.getTitle());
    toDo.setDescription(toDoDto.getDescription());
    Todo res = toDoRepo.save(toDo);
    return ToDoToDto(res);
  }

  // Delete ToDo
  public void deleteToDo(UUID toDoId) {
    Todo toDo = toDoRepo.findById(toDoId).orElseThrow();
    toDoRepo.delete(toDo);
  }

  // Get ToDo by ID
  public TodoDto getToDoById(UUID toDoId) {
    Todo toDo = toDoRepo.findById(toDoId).orElseThrow();
    return ToDoToDto(toDo);
  }

  // Get ToDo by User
  public List<TodoDto> getToDoByUser(String email) {
    Optional<User> user = userRepo.findByEmail(email);
    List<Todo> toDos = toDoRepo.findByUser(user.get());
    return toDos.stream().map(this::ToDoToDto).collect(Collectors.toList());
  }

  // Get all ToDos
  public List<TodoDto> getAllToDos() {
    List<Todo> toDos = toDoRepo.findAll();
    return toDos.stream().map(this::ToDoToDto).collect(Collectors.toList());
  }

  // Mapping methods
  public TodoDto ToDoToDto(Todo toDo) {
    TodoDto toDoDto = new TodoDto();
    toDoDto.setId(toDo.getId());
    toDoDto.setTitle(toDo.getTitle());
    toDoDto.setDescription(toDo.getDescription());
    return toDoDto;
  }

  public Todo DtoToToDo(TodoDto toDoDto) {
    Todo toDo = new Todo();
    toDo.setId(toDoDto.getId());
    toDo.setTitle(toDoDto.getTitle());
    toDo.setDescription(toDoDto.getDescription());
    toDo.setTimestamp(new Timestamp(System.currentTimeMillis()));
    return toDo;
  }

  public User DtoToUser(UserDto userDto) {
    User user = new User();
    user.setName(userDto.getName());
    user.setEmail(userDto.getEmail());
    user.setPassword(userDto.getPassword());
    return user;
  }

  public UserDto UserToDto(User user) {
    UserDto userDto = new UserDto();
    userDto.setId(user.getId());
    userDto.setName(user.getName());
    userDto.setEmail(user.getEmail());
    userDto.setPassword(user.getPassword());
    return userDto;
  }
}
