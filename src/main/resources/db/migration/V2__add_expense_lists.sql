CREATE TABLE expense_lists(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    list_date DATE NOT NULL,
    total DECIMAL NOT NULL
);

ALTER TABLE expenses
    ADD COLUMN list_id BIGINT REFERENCES expense_lists (id);
