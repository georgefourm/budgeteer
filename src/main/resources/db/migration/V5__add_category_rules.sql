CREATE TABLE category_rules(
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    description_rule VARCHAR,
    category_rule VARCHAR,
    description_replacement VARCHAR,
    importance INTEGER DEFAULT 1,
    category_id INTEGER REFERENCES categories(id),
);