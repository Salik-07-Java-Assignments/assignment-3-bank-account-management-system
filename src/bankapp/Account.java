package bankapp;

public class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    private static String bankName = "XYZ Bank Limited";
    private static int totalAccounts = 0;

    public Account(String accountNumber, String accountHolderName, double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }

        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;

        totalAccounts++;
    }

    // PUBLIC: accessible from any class
    public String getAccountNumber() {
        return accountNumber;
    }

    // PROTECTED: accessible within the same package and by subclasses in other packages.
    // In a single package it behaves like default access; the difference only shows
    // when a subclass in a different package uses it.
    protected String getAccountHolderName() {
        return accountHolderName;
    }

    // DEFAULT (no modifier): accessible only within the same package (bankapp)
    double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient balance.");
        }
        balance -= amount;
    }

    public void displayAccountDetails() {
        System.out.println("Bank Name        : " + bankName);
        System.out.println("Account Number   : " + accountNumber);
        System.out.println("Account Holder   : " + accountHolderName);
        System.out.println("Balance          : " + balance);
    }

    public static void displayBankInformation() {
        System.out.println("Bank Name      : " + bankName);
        System.out.println("Total Accounts : " + totalAccounts);
    }
}
