CREATE TABLE expense_lists(
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    list_date DATE NOT NULL,
    total DECIMAL NOT NULL
);

ALTER TABLE expenses
    ADD COLUMN list_id INTEGER REFERENCES expense_lists (id);
