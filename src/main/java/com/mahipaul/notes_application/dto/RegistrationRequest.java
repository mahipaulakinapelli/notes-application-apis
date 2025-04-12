package com.mahipaul.notes_application.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Data
public class RegistrationRequest {
  private String username;
  private String password;
  private String email;
}
