ALTER TABLE expenses
    ADD COLUMN group_id INTEGER REFERENCES groups (id);
