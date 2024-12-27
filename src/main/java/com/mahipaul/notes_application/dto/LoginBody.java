package com.mahipaul.notes_application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginBody {
    private String email;
    private String username;
    private String password;
}
