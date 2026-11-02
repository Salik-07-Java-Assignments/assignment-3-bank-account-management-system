# Assignment #3: Bank Account Management System

A console-based Java application for creating and managing bank accounts. Account and transaction data is kept in memory for the duration of the program.

## Features

- Create accounts with a unique account number and non-negative initial balance
- Deposit and withdraw money with amount and balance validation
- Display account details and bank information
- Track the total number of accounts and transactions during the session
- Handle invalid menu input and missing accounts

## Requirements

- Java Development Kit (JDK)

## Compile and run

From the project directory, compile the source files:

```powershell
javac -d out src/bankapp/Account.java src/bankapp/Transaction.java src/bankapp/Main.java
```

Then start the application:

```powershell
java -cp out bankapp.Main
```
