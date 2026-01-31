import java.util.ArrayList;
import java.util.List;

/**
 * Represents a bank with customers, accounts, tellers, and ATMs.
 * Serves as the central management system for all banking operations.
 * 
 * @author Bank Management System
 * @version 1.0
 */
public class Bank {

    private String name;
    private List<Customer> customers;
    private List<Teller> tellers;
    private List<Account> accounts;
    private List<ATM> atms;

    public Bank(String name) {
        this.name = name;
        this.customers = new ArrayList<>();
        this.tellers = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.atms = new ArrayList<>();
    }

    /**
     * Adds a customer to the bank's customer registry.
     * 
     * @param customer the customer to add (cannot be null)
     * @throws IllegalArgumentException if customer is null
     */
    public void addCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }
        customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null.");
        }
        customers.remove(customer);
    }

    /**
     * Retrieves customer details by customer ID.
     * 
     * @param customerID the unique identifier of the customer
     * @return Customer object with matching ID
     * @throws IllegalArgumentException if customer with given ID is not found
     */
    public Customer getCustomerDetails(String customerID) {
        for (Customer customer : customers) {
            if (customer.getCustomerID().equals(customerID)) {
                return customer;
            }
        }
        throw new IllegalArgumentException("Customer with ID " + customerID + " not found.");
    }

    public List<Customer> getCustomers(){
        return new ArrayList<>(customers);
    }

    public void addTeller(Teller teller) {
        if (teller == null) {
            throw new IllegalArgumentException("Teller cannot be null.");
        }
        tellers.add(teller);
    }

    public void removeTeller(Teller teller) {
        if (teller == null) {
            throw new IllegalArgumentException("Teller cannot be null.");
        }
        tellers.remove(teller);
    }

    public List<Teller> getTellers() {
        return new ArrayList<>(tellers);
    }

    public void addAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null.");
        }
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null.");
        }
        accounts.remove(account);
    }

    public List<Account> getAccounts() {
        return new ArrayList<>(accounts);
    }

    public void addATM(ATM atm) {
        if (atm == null) {
            throw new IllegalArgumentException("ATM cannot be null.");
        }
        atms.add(atm);
    }

    public void removeATM(ATM atm) {
        if (atm == null) {
            throw new IllegalArgumentException("ATM cannot be null.");
        }
        atms.remove(atm);
    }

    public List<ATM> getATMs() {
        return new ArrayList<>(atms);
    }

    public void deposit(Customer customer, Account account, double amount) {
        if (customer == null || account == null) {
            throw new IllegalArgumentException("Customer or Account cannot be null.");
        }
        account.deposit(amount);
        // Log transaction
        Transaction transaction = new Transaction(customer.getCustomerID(), account.getAccountNumber(), "Deposit", amount);
        TransactionLogger.log(transaction);
    }

    public void withdraw(Customer customer, Account account, double amount) {
        if (customer == null || account == null) {
            throw new IllegalArgumentException("Customer or Account cannot be null.");
        }
        boolean success = account.withdraw(amount);
        if (success) {
            // Log transaction
            Transaction transaction = new Transaction(customer.getCustomerID(), account.getAccountNumber(), "Withdrawal", amount);
            TransactionLogger.log(transaction);
        }
    }

    public void transfer(Customer customer, Account sourceAccount, Account targetAccount, double amount) {
        if (customer == null || sourceAccount == null || targetAccount == null) {
            throw new IllegalArgumentException("Customer or Account cannot be null.");
        }
        sourceAccount.transfer(targetAccount, amount);
        // Log transaction
        Transaction transaction = new Transaction(customer.getCustomerID(), sourceAccount.getAccountNumber(), "Transfer", amount);
        TransactionLogger.log(transaction);
    }

    public List<Transaction> getTransactionHistory(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null.");
        }
        return TransactionLogger.getTransactions();
    }

    @Override
    public String toString() {
        return "Bank Name: " + name + "\n" +
            "Customers: " + customers.size() + "\n" +
            "Tellers: " + tellers.size() + "\n" +
            "Accounts: " + accounts.size() + "\n" +
            "ATMs: " + atms.size();
    }
}
