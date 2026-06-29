CREATE TABLE categories (
    id        BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name      VARCHAR NOT NULL,
    parent_id BIGINT REFERENCES categories (id)
);
