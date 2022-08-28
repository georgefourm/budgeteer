CREATE TABLE members(
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR
);


ALTER TABLE expenses
    ADD COLUMN member_id INTEGER REFERENCES members (id);

CREATE TABLE groups(
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name varchar
);

CREATE TABLE group_members(
    group_id INTEGER REFERENCES groups (id),
    member_id INTEGER REFERENCES members (id)
);