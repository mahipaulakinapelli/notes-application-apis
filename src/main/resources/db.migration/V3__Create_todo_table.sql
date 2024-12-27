-- V3__Create_todo_table.sql

CREATE TABLE todo (
    id UUID PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    date DATE,
    user_id UUID,
    CONSTRAINT fk_user_todo
        FOREIGN KEY (user_id)
        REFERENCES "user"(id)
        ON DELETE CASCADE
);
