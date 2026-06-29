CREATE TABLE categories (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR NOT NULL,
    parent_id BIGINT REFERENCES categories (id)
);

CREATE TABLE items (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR NOT NULL,
    default_cost NUMERIC(12,2),
    category_id BIGINT REFERENCES categories (id)
);

CREATE TABLE expenses(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    item_id BIGINT REFERENCES items (id),
    category_id BIGINT REFERENCES categories (id),
    amount NUMERIC(12,2) NOT NULL,
    description TEXT,
    item_count INTEGER,
    bought_at TIMESTAMPTZ NOT NULL,
    created_at TIMESTAMPTZ NOT NULL
);

CREATE TABLE incomes(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    amount NUMERIC(12,2) NOT NULL,
    category_id BIGINT REFERENCES categories(id),
    description VARCHAR,
    received_at TIMESTAMPTZ NOT NULL,
    created_at TIMESTAMPTZ NOT NULL
);
