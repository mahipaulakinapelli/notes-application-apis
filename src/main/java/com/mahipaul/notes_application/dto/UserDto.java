package com.mahipaul.notes_application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private UUID id;
    private String name;
    private String email;
    private String password;

}
