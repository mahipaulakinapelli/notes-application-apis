package com.mahipaul.notes_application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.UUID;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Data
public class NotesDto {

  private UUID id;

  private String title;

  private String description;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date date;

  private UserDto userDto;
}
