package com.mahipaul.notes_application.repository;

import com.mahipaul.notes_application.model.Todo;
import com.mahipaul.notes_application.model.User;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, UUID> {
  List<Todo> findByUser(User user);

  Todo findByUserIdAndId(UUID userId, UUID id);
}
