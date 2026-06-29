# Budgeteer
A budgeting app backend, used to record income and expenses.

## Modeling
The following concepts were used to model the data of the application:

- Transaction : A single movement of money. The sign of its amount captures the
  direction — a positive amount is an income, a negative amount is an expense.
  A transaction always belongs to a single account and optionally to a category.
- Account : A named place where money is held (e.g. a bank account). Every transaction belongs to exactly one account.
- Category : A label, optionally arranged in a hierarchy, used to classify transactions.
