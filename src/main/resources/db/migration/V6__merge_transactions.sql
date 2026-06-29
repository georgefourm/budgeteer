-- Merge expenses and incomes into a single signed-amount transactions table.
-- Positive amounts are incomes, negative amounts are expenses.

CREATE TABLE transactions (
    id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    amount         NUMERIC(12,2) NOT NULL CHECK (amount <> 0),
    description    TEXT,
    transaction_ts TIMESTAMPTZ NOT NULL,
    category_id BIGINT REFERENCES categories (id),
    created_at  TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- Migrate expenses -> NEGATIVE amounts (stored previously as positive magnitudes)
INSERT INTO transactions (amount, description, transaction_ts, category_id, created_at)
SELECT -ABS(amount), description, bought_at, category_id, created_at
FROM expenses;

-- Migrate incomes -> POSITIVE amounts
INSERT INTO transactions (amount, description, transaction_ts, category_id, created_at)
SELECT ABS(amount), description, received_at, category_id, created_at
FROM incomes;

-- Drop obsolete tables (order respects foreign keys)
DROP TABLE expenses;
DROP TABLE expense_lists;
DROP TABLE items;
DROP TABLE incomes;

-- Drop the member/group concept (order respects foreign keys)
DROP TABLE group_members;
DROP TABLE groups;
DROP TABLE members;

CREATE INDEX idx_transactions_transaction_ts ON transactions (transaction_ts);
