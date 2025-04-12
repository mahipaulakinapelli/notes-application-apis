package com.mahipaul.notes_application.dto;

import java.util.UUID;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TodoDto {

  private UUID id;
  private String title;
  private String description;
  private UserDto userDto;
}
