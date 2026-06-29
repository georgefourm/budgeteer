-- Merge expenses and incomes into a single signed-amount transactions table.
-- Positive amounts are incomes, negative amounts are expenses.

CREATE TABLE transactions (
    id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    amount         NUMERIC(12,2) NOT NULL CHECK (amount <> 0),
    description    TEXT,
    transaction_ts TIMESTAMPTZ NOT NULL,
    member_id   BIGINT REFERENCES members (id),
    group_id    BIGINT REFERENCES groups (id),
    category_id BIGINT REFERENCES categories (id),
    created_at  TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- Migrate expenses -> NEGATIVE amounts (stored previously as positive magnitudes)
INSERT INTO transactions (amount, description, transaction_ts, member_id, group_id, category_id, created_at)
SELECT -ABS(amount), description, bought_at, member_id, group_id, category_id, created_at
FROM expenses;

-- Migrate incomes -> POSITIVE amounts
INSERT INTO transactions (amount, description, transaction_ts, member_id, group_id, category_id, created_at)
SELECT ABS(amount), description, received_at, member_id, NULL, category_id, created_at
FROM incomes;

-- Drop obsolete tables (order respects foreign keys)
DROP TABLE expenses;
DROP TABLE expense_lists;
DROP TABLE items;
DROP TABLE incomes;

CREATE INDEX idx_transactions_transaction_ts ON transactions (transaction_ts);
