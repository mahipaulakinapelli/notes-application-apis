package com.mahipaul.notes_application.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "todo")
public class Todo {

  @Id
  @Column(name = "id", nullable = false)
  @UuidGenerator(style = UuidGenerator.Style.TIME)
  private UUID id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  @Column(name = "date")
  private Timestamp timestamp;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;
}
