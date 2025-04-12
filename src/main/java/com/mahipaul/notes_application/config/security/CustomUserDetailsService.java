/*
 * Copyright (C) BMG - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 */
package com.mahipaul.notes_application.config.security;

import com.mahipaul.notes_application.model.User;
import com.mahipaul.notes_application.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Setter
@Getter
public class CustomUserDetailsService implements UserDetailsService {

  private String password;

  @Autowired private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user =
        userRepository
            .findByName(username)
            .orElseThrow(
                () -> new UsernameNotFoundException("User not found with username: " + username));
    return new CustomUserDetails(user);
  }

  //    public boolean checkIfValidUser(UserDTO userDTO, String userName) {
  //        if(StringUtils.equalsIgnoreCase(userDTO.getEmail(), userName) ||
  // StringUtils.equalsIgnoreCase(userDTO.getUserName(), userName)) {
  //            log.info("User is valid with username: {}", userName);
  //            return true;
  //        }
  //        return false;
  //    }
}
