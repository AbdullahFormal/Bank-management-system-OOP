/**
 * Represents a current account with overdraft facility.
 * Extends Account class with overdraft limit and fee management.
 * 
 * @author Bank Management System
 * @version 1.0
 */
public class CurrentAccount extends Account{
    
    protected double overdraftLimit;
    protected double overdraftFee;

    public CurrentAccount(Customer customer, double overdraftLimit, double overdraftFee) {
        super("Current", customer);
        if (overdraftLimit < 0) {
            throw new IllegalArgumentException("Overdraft limit cannot be negative.");
        }
        if (overdraftFee < 0) {
            throw new IllegalArgumentException("Overdraft fee cannot be negative.");
        }
        this.overdraftLimit = overdraftLimit;
        this.overdraftFee = overdraftFee;
    }

    /**
     * Validates that the account balance does not exceed the overdraft limit.
     * 
     * @throws IllegalArgumentException if balance exceeds overdraft limit
     */
    @Override
    public void specificFunctionality(){
        if (balance < -overdraftLimit){
            throw new IllegalArgumentException("Exceeds overdraft limit.");
        }
    }

    /**
     * Withdraws the specified amount from the current account.
     * Allows withdrawals up to the overdraft limit.
     * 
     * @param amount the amount to withdraw (must be greater than zero)
     * @return true if withdrawal was successful
     * @throws IllegalArgumentException if amount is invalid or exceeds overdraft limit
     */
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        if (balance - amount < -overdraftLimit) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        balance -= amount;
        return true;
    }

    /**
     * Applies overdraft fee if account balance is negative.
     * Ensures fee application does not exceed overdraft limit.
     */
    public void applyOverdraftFee(){
        if (balance < 0) {
            double feeToApply = overdraftFee;
            double potentialNewBalance = balance - feeToApply;
            if (potentialNewBalance < -overdraftLimit) {
                feeToApply = Math.abs(balance + overdraftLimit);
            }
            balance -= feeToApply;
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
            "Overdraft Limit: " + overdraftLimit + "\n" +
            "Overdraft Fee: " + overdraftFee;
    }

    @Override
    public String toCSV() {
        return String.join(",", super.toCSV(), Double.toString(overdraftLimit), Double.toString(overdraftFee));
    }
    public static CurrentAccount fromCSV(String csv, Customer customer) {
        String[] values = csv.split(",");
        if (values.length < 6) { // Ensure there are enough values
            throw new IllegalArgumentException("Invalid CSV data for CurrentAccount.");
        }
        
        String accountNumber = values[0];
        double balance = Double.parseDouble(values[1]);
        double overdraftLimit = Double.parseDouble(values[4]); // Correct index for overdraftLimit
        double overdraftFee = Double.parseDouble(values[5]); // Correct index for overdraftFee
        
        CurrentAccount account = new CurrentAccount(customer, overdraftLimit, overdraftFee);
        account.setAccountNumber(accountNumber);
        account.setBalance(balance);
        return account;
    }
    
}