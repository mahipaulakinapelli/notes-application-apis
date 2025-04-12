package com.mahipaul.notes_application.dto;

import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
  private UUID id;
  private String name;
  private String email;
  private String password;
}
