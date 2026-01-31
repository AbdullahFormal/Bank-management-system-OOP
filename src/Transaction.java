/**
 * Represents a banking transaction with customer, account, type, and amount information.
 * Transactions can be serialized to CSV format for logging and persistence.
 * 
 * @author Bank Management System
 * @version 1.0
 */
public class Transaction {

    private String customerID;
    private String accountNumber;
    private String type;
    private double amount;

    public Transaction(String customerID, String accountNumber, String type, double amount) {
        validateInput(customerID, "Customer ID cannot be empty.");
        validateInput(accountNumber, "Account number cannot be empty.");
        validateInput(type, "Transaction type cannot be empty.");
        validateAmount(amount, "Amount must be greater than zero.");

        this.customerID = customerID;
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
    }

    public String getCustomerID() {
        return customerID;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public String getType() {
        return type;
    }
    public double getAmount() {
        return amount;
    }

    public void validateAmount(double amount, String errorMessage) {
        if (amount <= 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public void validateInput(String input, String errorMessage) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public String toCSV() {
        return customerID + "," + accountNumber + "," + type + "," + amount;
    }

    public static Transaction fromCSV(String csv) {
        String[] parts = csv.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid CSV format for Transaction.");
        }
        String customerID = parts[0];
        String accountNumber = parts[1];
        String transactionType = parts[2];
        double amount = Double.parseDouble(parts[3]);
        
        return new Transaction(customerID, accountNumber, transactionType, amount);
    }

    @Override
    public String toString() {
        return String.format("Transaction Details:\nCustomer ID: %s\nAccount Number: %s\nType: %s\nAmount: %.2f",
                customerID, accountNumber, type, amount);
    }
}

