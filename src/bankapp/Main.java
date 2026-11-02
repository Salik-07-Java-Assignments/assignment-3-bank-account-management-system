package bankapp;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Account findAccount(ArrayList<Account> accounts, String number) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(number)) {
                return acc;
            }
        }
        return null;
    }

    /**
     * Processes a withdrawal for the given account.
     * <p>
     * 'throws' in a method signature declares that the method may pass certain
     * exceptions up to its caller instead of handling them itself.
     * Both exceptions below are unchecked, so declaring them is optional and
     * informational; it is written here to demonstrate the keyword.
     *
     * @throws IllegalArgumentException if the amount is zero or negative
     * @throws IllegalStateException    if the amount exceeds the available balance
     */
    public static void processWithdrawal(Account account, double amount)
            throws IllegalArgumentException, IllegalStateException {
        account.withdraw(amount);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Account> accounts = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("\n===== Bank Account Management System =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Display Account Details");
            System.out.println("5. Display Bank Information");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                continue;
            }

            switch (choice) {
                case 1: {
                    System.out.print("Enter account number: ");
                    String accNumber = scanner.nextLine();

                    if (findAccount(accounts, accNumber) != null) {
                        System.out.println("Account number already exists.");
                        break;
                    }

                    System.out.print("Enter account holder name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter initial balance: ");
                    try {
                        double initialBalance = Double.parseDouble(scanner.nextLine());
                        Account newAccount = new Account(accNumber, name, initialBalance);
                        accounts.add(newAccount);
                        System.out.println("Account created successfully.");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number entered.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 2: {
                    try {
                        System.out.print("Enter account number: ");
                        String accNumber = scanner.nextLine();
                        Account account = findAccount(accounts, accNumber);

                        if (account == null) {
                            System.out.println("Account not found.");
                        } else {
                            System.out.print("Enter deposit amount: ");
                            double amount = Double.parseDouble(scanner.nextLine());

                            account.deposit(amount);

                            // getAccountHolderName() is protected, getBalance() is default:
                            // both are accessible here because Main is in the same package
                            System.out.println("Deposit successful for " + account.getAccountHolderName());
                            System.out.println("New balance: " + account.getBalance());

                            Transaction transaction = new Transaction("Deposit", amount);
                            transaction.displayTransactionDetails();
                            System.out.println("Transaction type: " + transaction.type);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number entered.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        System.out.println("Transaction processing completed.");
                    }
                    break;
                }
                case 3: {
                    try {
                        System.out.print("Enter account number: ");
                        String accNumber = scanner.nextLine();
                        Account account = findAccount(accounts, accNumber);

                        if (account == null) {
                            System.out.println("Account not found.");
                        } else {
                            System.out.print("Enter withdrawal amount: ");
                            double amount = Double.parseDouble(scanner.nextLine());

                            processWithdrawal(account, amount); // may throw either exception

                            System.out.println("Withdrawal successful for " + account.getAccountHolderName());
                            System.out.println("New balance: " + account.getBalance());

                            Transaction transaction = new Transaction("Withdrawal", amount);
                            transaction.displayTransactionDetails();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number entered.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        System.out.println("Transaction processing completed.");
                    }
                    break;
                }
                case 4: {
                    System.out.print("Enter account number: ");
                    String accNumber = scanner.nextLine();
                    Account account = findAccount(accounts, accNumber);

                    if (account == null) {
                        System.out.println("Account not found.");
                    } else {
                        account.displayAccountDetails();
                    }
                    break;
                }
                case 5:
                    // static method: called with the class name, no object needed
                    Account.displayBankInformation();
                    System.out.println("Total Transactions : " + Transaction.getTransactionCount());
                    break;
                case 6:
                    System.out.println("Thank you for using the system. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose between 1 and 6.");
            }
        }

        scanner.close();
    }
}