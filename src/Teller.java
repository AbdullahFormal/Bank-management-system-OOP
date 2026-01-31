/**
 * Represents a bank teller who assists customers with banking operations.
 * Provides deposit, withdrawal, and transfer assistance with transaction logging.
 * 
 * @author Bank Management System
 * @version 1.0
 */
public class Teller {

    private String tellerID;
    private String name;
    private String branchID;

    public Teller(String tellerID, String name, String branchID) {
        validateInput(tellerID,"Teller ID can not be empty");
        validateInput(name,"Name can not be empty");
        validateInput(branchID,"Branch ID can not be empty");
        
        this.tellerID = tellerID;
        this.name = name;
        this.branchID = branchID;
    }

    public String getTellerID() {
        return tellerID;
    }
    public String getName() {
        return name;
    }
    public String getBranchID() {
        return branchID;
    }

    /**
     * Assists a customer with depositing funds into their account.
     * Validates input and logs the transaction upon success.
     * 
     * @param customer the customer making the deposit
     * @param account the account to deposit to
     * @param amount the amount to deposit
     * @throws IllegalArgumentException if customer, account is null, or amount is invalid
     */
    public void assistDeposit(Customer customer, Account account, double amount){
        if (customer==null || account==null ) {
            throw new IllegalArgumentException("Customer or Account cannot be null");   
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        boolean success = account.deposit(amount);
        if (success) {
            logTransaction(customer, account, amount, "Deposit");
        }
    }


    /**
     * Assists a customer with withdrawing funds from their account.
     * Validates balance and logs the transaction upon success.
     * 
     * @param customer the customer making the withdrawal
     * @param account the account to withdraw from
     * @param amount the amount to withdraw
     * @throws IllegalArgumentException if customer, account is null, amount is invalid, or balance is insufficient
     */
    public void assistWithdrawal(Customer customer, Account account, double amount) {
        if (customer == null || account == null) {
            throw new IllegalArgumentException("Customer or account cannot be null.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        if (amount > account.getBalance()) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        boolean success = account.withdraw(amount);
        if (success) {
            logTransaction(customer, account, amount, "Withdrawal");
        }
    }

    public void assistTransfer(Customer customer, Account sourceAccount, Account targetAccount, double amount) {
        if (customer == null || sourceAccount == null || targetAccount == null) {
            throw new IllegalArgumentException("Customer, source account, or target account cannot be null.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        if (amount > sourceAccount.getBalance()) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        boolean success = sourceAccount.transfer(targetAccount, amount);
        if (success) {
            logTransaction(customer, sourceAccount, amount, "Transfer");
        }
    }

    public void provideAccountDetails(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }
        System.out.println(customer);
    }

    private void logTransaction(Customer customer, Account account, double amount, String type) {
        Transaction transaction = new Transaction(customer.getCustomerID(), account.getAccountNumber(), type, amount);
        TransactionLogger.log(transaction);
    }

    private void validateInput(String input, String errorMessage) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    @Override
    public String toString() {
        return "Teller{" +
                "tellerID='" + tellerID + '\n' +
                ", name='" + name + '\n' +
                ", branchID='" + branchID  +
                '}';
    }
}    