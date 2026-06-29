-- A single signed-amount movement of money.
-- Positive amounts are incomes, negative amounts are expenses.
CREATE TABLE transactions (
    id             BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    amount         NUMERIC(12,2) NOT NULL CHECK (amount <> 0),
    description    TEXT,
    transaction_ts TIMESTAMPTZ NOT NULL,
    account_id     BIGINT NOT NULL REFERENCES accounts (id),
    category_id    BIGINT REFERENCES categories (id),
    created_at     TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE INDEX idx_transactions_transaction_ts ON transactions (transaction_ts);
