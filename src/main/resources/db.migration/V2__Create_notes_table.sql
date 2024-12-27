-- V2__Create_notes_table.sql

CREATE TABLE notes (
    id UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    date DATE,
    user_id UUID,
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
        REFERENCES "user"(id)
        ON DELETE CASCADE
);
