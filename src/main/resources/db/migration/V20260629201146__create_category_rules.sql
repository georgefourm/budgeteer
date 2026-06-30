CREATE TABLE category_rules (
    id                      BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    rule_regex              VARCHAR NOT NULL,
    importance              INTEGER DEFAULT 1,
    category_id             BIGINT REFERENCES categories (id) NOT NULL
);
