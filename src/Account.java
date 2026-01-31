import java.util.UUID;

/**
 * Abstract base class representing a bank account.
 * Provides common functionality for all account types including deposits, withdrawals, and transfers.
 * Subclasses must implement specific account-type functionality.
 * 
 * @author Bank Management System
 * @version 1.0
 */
public abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected String accountType;
    protected Customer customer;

    public Account(String accountType, Customer customer) {
        validateInput(accountType, "Account type cannot be empty.");
        validateCustomer(customer);

        this.accountNumber = UUID.randomUUID().toString();
        this.accountType = accountType;
        this.customer = customer;
        this.balance = 0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    /**
     * Deposits the specified amount into this account.
     * 
     * @param amount the amount to deposit (must be greater than zero)
     * @return true if deposit was successful
     * @throws IllegalArgumentException if amount is less than or equal to zero
     */
    public boolean deposit(double amount) {
        validateAmount(amount, "Deposit amount must be greater than zero.");
        balance += amount;
        return true; 
    }

    /**
     * Withdraws the specified amount from this account.
     * 
     * @param amount the amount to withdraw (must be greater than zero)
     * @return true if withdrawal was successful
     * @throws IllegalArgumentException if amount is less than or equal to zero or if balance is insufficient
     */
    public boolean withdraw(double amount) {
        validateAmount(amount, "Withdrawal amount must be greater than zero.");
        if (balance < amount) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        balance -= amount;
        return true; // Withdrawal successful
    }

    /**
     * Transfers the specified amount from this account to the target account.
     * 
     * @param targetAccount the account to transfer funds to (cannot be null)
     * @param amount the amount to transfer (must be greater than zero)
     * @return true if transfer was successful
     * @throws IllegalArgumentException if amount is invalid, target account is null, or balance is insufficient
     */
    public boolean transfer(Account targetAccount, double amount) {
        validateAmount(amount, "Transfer amount must be greater than zero.");
        if (targetAccount == null) {
            throw new IllegalArgumentException("Target account cannot be null.");
        }
        withdraw(amount);
        boolean depositSuccess = targetAccount.deposit(amount);
        return depositSuccess;
    }

    private void validateInput(String input, String errorMessage) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private void validateAmount(double amount, String errorMessage) {
        if (amount <= 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private void validateCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }
    }

    protected void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Executes account-type specific functionality.
     * Must be implemented by subclasses to define unique account behaviors.
     */
    public abstract void specificFunctionality();
    /**
     * Converts this account to CSV format for persistence.
     * 
     * @return comma-separated string representation of the account
     */
    public String toCSV() {
        return String.join(",", accountNumber, Double.toString(balance), accountType, customer.getCustomerID());
    }
    /**
     * Creates an Account object from CSV data.
     * Determines account type and delegates to appropriate subclass factory method.
     * 
     * @param csv comma-separated account data
     * @param customer the customer who owns this account
     * @return Account object (SavingsAccount or CurrentAccount)
     * @throws IllegalArgumentException if CSV data is invalid or account type is unknown
     */
    public static Account fromCSV(String csv, Customer customer) {
        String[] values = csv.split(",");
        if (values.length < 4) {
            throw new IllegalArgumentException("Invalid CSV data for Account.");
        }
        String accountType = values[2];
    
        switch (accountType) {
            case "Savings":
                return SavingsAccount.fromCSV(csv, customer);
            case "Current":
                return CurrentAccount.fromCSV(csv, customer);
            default:
                throw new IllegalArgumentException("Unknown account type: " + accountType);
        }
    }
    @Override
    public String toString() {
        return "Account Number: " + accountNumber + "\n" +
            "Account Type: " + accountType + "\n" +
            "Balance: " + balance + "\n" +
            "Customer: " + customer.getName();
    }
}
