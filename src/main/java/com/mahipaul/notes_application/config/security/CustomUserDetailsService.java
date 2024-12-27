/*
 * Copyright (C) BMG - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential.
 */
package com.mahipaul.notes_application.config.security;


import com.mahipaul.notes_application.dto.UserDto;
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

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserDto userDTO = new UserDto();
            userDTO.setName(username);
            userDTO.setPassword(password);

            User user = userRepository.findByName(username).get();
            return (UserDetails) user;
        } catch (Exception e) {
            log.error("Error getting user details", e);
        }
        throw new UsernameNotFoundException("User not found with the name " + username);
    }

//    public boolean checkIfValidUser(UserDTO userDTO, String userName) {
//        if(StringUtils.equalsIgnoreCase(userDTO.getEmail(), userName) || StringUtils.equalsIgnoreCase(userDTO.getUserName(), userName)) {
//            log.info("User is valid with username: {}", userName);
//            return true;
//        }
//        return false;
//    }
}
