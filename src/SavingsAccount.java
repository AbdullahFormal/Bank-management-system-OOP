/**
 * Represents a savings account with interest rate functionality.
 * Extends Account class with interest application and special tier benefits.
 * 
 * @author Bank Management System
 * @version 1.0
 */
public class SavingsAccount extends Account {
    private double interestRate;
    private static final double SPECIAL_TIER_THRESHOLD = 10000; 

    public SavingsAccount(Customer customer, double interestRate) {
        super("Savings", customer);
        if (interestRate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative.");
        }
        this.interestRate = interestRate;
    }

    public SavingsAccount(Customer customer, double balance, double interestRate) {
        super("Savings", customer);
        if (interestRate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative.");
        }
        this.interestRate = interestRate;
        this.balance = balance;

    }

    /**
     * Applies interest to the account balance and checks for special tier eligibility.
     * Executes savings account specific functionality.
     */
    @Override
    public void specificFunctionality() {
        applyInterest();
        checkSpecialTier();
    }

    private void applyInterest() {
        double interest = balance * (interestRate / 100);
        deposit(interest);
    }

    private void checkSpecialTier() {
        if (balance >= SPECIAL_TIER_THRESHOLD) {
            System.out.println("Congratulations! You Found an Easter Egg!");
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
            "Interest Rate: " + interestRate;
    }

    @Override
    public String toCSV() {
        return String.join(",", super.toCSV(), Double.toString(interestRate));
    }

    public static SavingsAccount fromCSV(String csv, Customer customer) {
        String[] values = csv.split(",");
        if (values.length < 5) { // Ensure there are enough values
            throw new IllegalArgumentException("Invalid CSV data for SavingsAccount.");
        }
        
        String accountNumber = values[0];
        double balance = Double.parseDouble(values[1]);
        double interestRate = Double.parseDouble(values[4]); // Correct index for interestRate
        
        SavingsAccount account = new SavingsAccount(customer, interestRate);
        account.setAccountNumber(accountNumber);
        account.setBalance(balance);
        return account;
    }
}
