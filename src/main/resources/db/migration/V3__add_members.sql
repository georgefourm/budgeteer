CREATE TABLE members(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR
);

ALTER TABLE expenses
    ADD COLUMN member_id BIGINT REFERENCES members (id);

ALTER TABLE incomes
    ADD COLUMN member_id BIGINT REFERENCES members(id);

CREATE TABLE groups(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name varchar
);

CREATE TABLE group_members(
    group_id BIGINT REFERENCES groups (id),
    member_id BIGINT REFERENCES members (id)
);
