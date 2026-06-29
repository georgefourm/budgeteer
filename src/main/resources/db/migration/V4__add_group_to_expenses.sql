ALTER TABLE expenses
    ADD COLUMN group_id BIGINT REFERENCES groups (id);
