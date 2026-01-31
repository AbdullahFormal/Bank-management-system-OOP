
/**
 * Represents an Automated Teller Machine (ATM) with self-service banking capabilities.
 * Manages cash availability and provides deposit, withdrawal, and transfer services.
 * 
 * @author Bank Management System
 * @version 1.0
 */
public class ATM {
    private String atmID;
    private String location;
    private double cashAvailable;

    public ATM(String atmID, String location, double cashAvailable) {
        validateInput(atmID, "ATM ID cannot be empty.");
        validateInput(location, "Location cannot be empty.");
        if (cashAvailable < 0) {
            throw new IllegalArgumentException("Cash available cannot be negative.");
        }
        
        this.atmID = atmID;
        this.location = location;
        this.cashAvailable = cashAvailable;
    }

    /**
     * Processes a withdrawal from the specified account through the ATM.
     * Validates cash availability and logs the transaction.
     * 
     * @param account the account to withdraw from
     * @param amount the amount to withdraw
     * @throws IllegalArgumentException if amount is invalid or ATM lacks sufficient cash
     */
    public void withdraw(Account account, double amount) {
        validateAmount(amount, "Amount must be positive.");
        if (amount > cashAvailable) {
            throw new IllegalArgumentException("ATM does not have enough cash.");
        }
        if (account.withdraw(amount)) {
            cashAvailable -= amount;
            logTransaction(account, amount, "Withdrawal");
            System.out.println("Withdrawal successful. Amount: " + amount);
        }
    }

    /**
     * Processes a deposit to the specified account through the ATM.
     * Updates ATM cash availability and logs the transaction.
     * 
     * @param account the account to deposit to
     * @param amount the amount to deposit
     * @throws IllegalArgumentException if amount is invalid
     */
    public void deposit(Account account, double amount) {
        validateAmount(amount, "Amount must be positive.");
        account.deposit(amount);
        cashAvailable += amount;
        logTransaction(account, amount, "Deposit");
        System.out.println("Deposit successful. Amount: " + amount);
    }

    public void transfer(Account sourceAccount, Account targetAccount, double amount) {
        validateAmount(amount, "Amount must be positive.");
        if (sourceAccount.withdraw(amount)) {
            targetAccount.deposit(amount);
            logTransaction(sourceAccount, amount, "Transfer");
            System.out.println("Transfer successful. Amount: " + amount);
        }
    }

    public void replenishCash(double amount) {
        validateAmount(amount, "Amount must be positive.");
        cashAvailable += amount;
        System.out.println("ATM cash replenished. Amount: " + amount);
    }

    public double getCashAvailable() {
        return cashAvailable;
    }

    public double checkBalance(Account account) {
        return account.getBalance();
    }

    @Override
    public String toString() {
        return "ATM ID: " + atmID + "\n" +
            "Location: " + location + "\n" +
            "Cash Available: " + cashAvailable;
    }

    private void logTransaction(Account account, double amount, String type) {
        String customerID = account.getCustomer().getCustomerID();
        String accountNumber = account.getAccountNumber();
        Transaction transaction = new Transaction(customerID, accountNumber, type, amount);
        TransactionLogger.log(transaction);
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
}
