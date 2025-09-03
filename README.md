# Expense API Simulator (Day 6)

A command-line simulator that emulates REST-style endpoints for Users and Expenses.
Designed as a backend-focused exercise to map CLI commands -> controllers -> services -> persistence,
so the transition to Spring controllers & services is natural.

## Concepts Covered

- Simulated Controller / Service layering
- Mapping HTTP verbs & paths to methods
- In-memory stores & file persistence
- Input parsing and basic validation
- Preparing for Spring Boot style architecture

## How to Run

```bash
javac -d out src/dev/ayush/expenseApiSimulator/**/*.java
java -cp out dev.ayush.expenseApiSimulator.ApiSimulatorApp
```

## Example Commands

```
POST /users 1|Ayush|ayush@example.com
GET /users
POST /users/1/expenses 101|Food|250.0|2025-08-30|Lunch
GET /users/1/expenses
GET /users/1/reports
SAVE
LOAD
EXIT
```

## Data Files

- `data/users.txt` (format: id,name,email)
- `data/expenses.txt` (format: expenseId,userId,category,amount,date,description)

## Future Improvements

- Replace CLI parser with actual HTTP server (Spring Boot)
- Add validation and error status codes
- Add authentication and sessions
