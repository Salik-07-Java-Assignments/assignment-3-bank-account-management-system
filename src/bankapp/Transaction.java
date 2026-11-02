package bankapp;

public class Transaction {

    // DEFAULT access (no modifier): accessible only within the bankapp package
    String type;

    private double amount;
    private int transactionId;

    private static int transactionCount = 0;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        transactionCount++;                 // increase the shared counter
        this.transactionId = transactionCount; // ID comes from the counter
    }

    public void displayTransactionDetails() {
        System.out.println("Transaction ID : " + transactionId);
        System.out.println("Type           : " + type);
        System.out.println("Amount         : " + amount);
    }

    // PUBLIC STATIC: call with the class name, no object needed
    public static int getTransactionCount() {
        return transactionCount;
    }
}