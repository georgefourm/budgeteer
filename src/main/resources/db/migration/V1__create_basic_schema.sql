CREATE TABLE categories (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR NOT NULL,
    parent_id INTEGER REFERENCES categories (id)
);

CREATE TABLE items (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR NOT NULL,
    default_cost NUMERIC(12,2),
    category_id INTEGER REFERENCES categories (id)
);

CREATE TABLE expenses(
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    item_id INTEGER REFERENCES items (id),
    category_id INTEGER REFERENCES categories (id),
    amount NUMERIC(12,2) NOT NULL,
    description TEXT,
    item_count INTEGER,
    bought_at TIMESTAMPTZ NOT NULL,
    created_at TIMESTAMPTZ NOT NULL
);

CREATE TABLE incomes(
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    amount NUMERIC(12,2) NOT NULL,
    category_id INTEGER REFERENCES categories(id),
    description VARCHAR,
    received_at TIMESTAMPTZ NOT NULL,
    created_at TIMESTAMPTZ NOT NULL
);
