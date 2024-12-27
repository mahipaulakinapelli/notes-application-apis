package com.mahipaul.notes_application.repository;

import com.mahipaul.notes_application.model.Notes;
import com.mahipaul.notes_application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NoteRepository extends JpaRepository<Notes, UUID> {

    List<Notes> findByUser(Optional<User> user);

}
