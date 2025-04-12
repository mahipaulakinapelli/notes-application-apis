package com.mahipaul.notes_application.service;

import com.mahipaul.notes_application.dto.UserDto;
import com.mahipaul.notes_application.model.User;
import com.mahipaul.notes_application.repository.UserRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired private UserRepository userRepo;

  // Create USER
  public UserDto createUser(UserDto userDto) {
    User user = userRepo.save(DtoToUser(userDto));
    return UserToDto(user);
  }

  // Update USER
  public UserDto updateUser(UserDto userDto, UUID userId) {
    User user = this.userRepo.findById(userId).orElseThrow();
    if (userDto.getEmail() != null) {
      user.setEmail(userDto.getEmail());
    }
    if (userDto.getPassword() != null) {
      user.setPassword(userDto.getPassword());
    }
    if (userDto.getName() != null) {
      user.setName(userDto.getName());
    }
    userRepo.save(user);
    return this.UserToDto(user);
  }

  // Delete USER
  public void deleteUser(UUID userId) {
    User user = userRepo.findById(userId).orElseThrow();
    userRepo.delete(user);
  }

  // get
  public UserDto getUser(UUID userId) {
    User user = userRepo.findById(userId).orElseThrow();
    return UserToDto(user);
  }

  // Get All USERS
  public List<UserDto> getAllUser() {
    List<User> users = this.userRepo.findAll();
    List<UserDto> allNotes =
        users.stream().map((user) -> UserToDto(user)).collect(Collectors.toList());
    return allNotes;
  }

  // Login
  public UserDto userLogin(String email, String password) {
    User user = this.userRepo.findByEmailAndPassword(email, password);
    return this.UserToDto(user);
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
    userDto.setName(user.getName());
    userDto.setEmail(user.getEmail());
    userDto.setPassword(user.getPassword());
    return userDto;
  }
}
