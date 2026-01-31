
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a bank customer with personal information and associated accounts.
 * Each customer can have multiple accounts and is identified by a unique customer ID.
 * 
 * @author Bank Management System
 * @version 1.0
 */
public class Customer {
    private String name;
    private String address;
    private String customerID;
    private String phoneNumber;
    private String CNIC;
    private List<Account> accounts;

    public Customer(String customerID, String name, String address, String phoneNumber, String CNIC) {
        
        validateInput(customerID, "Customer ID cannot be empty.");
        validateInput(name, "Name cannot be empty.");
        validateInput(address, "Address cannot be empty.");
        validateInput(phoneNumber, "Phone number cannot be empty.");
        validateInput(CNIC, "CNIC cannot be empty.");
        
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.CNIC = CNIC;
        this.accounts = new ArrayList<>();
    }

    public Customer(String customerID, String name, String address, String phoneNumber, String CNIC, List<Account> accounts) {
        
        validateInput(customerID, "Customer ID cannot be empty.");
        validateInput(name, "Name cannot be empty.");
        validateInput(address, "Address cannot be empty.");
        validateInput(phoneNumber, "Phone number cannot be empty.");
        validateInput(CNIC, "CNIC cannot be empty.");
        
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.CNIC = CNIC;
        this.accounts = accounts;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    /**
     * Adds an account to this customer's account list.
     * 
     * @param account the account to add (cannot be null)
     * @throws IllegalArgumentException if account is null
     */
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

    /**
     * Returns a copy of this customer's accounts list.
     * 
     * @return new ArrayList containing all customer accounts
     */
    public List<Account> viewAccounts() {
        return new ArrayList<>(accounts); // Return a copy to prevent modification
    }

    private void validateInput(String input, String errorMessage) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public String toCSV() {
        // Convert list of account IDs to a semicolon-separated string
        String accountIDs = accounts.stream()
                                    .map(Account::getAccountNumber)
                                    .collect(Collectors.joining(";"));
        return String.join(",", customerID, name, address, phoneNumber, CNIC, accountIDs);
    }
    public static Customer fromCSV(String csv) {
        String[] values = csv.split(",", -1); // Use -1 to handle trailing empty values
        if (values.length < 6) {
            throw new IllegalArgumentException("Invalid CSV data for Customer.");
        }

        String customerID = values[0];
        String name = values[1];
        String address = values[2];
        String phoneNumber = values[3];
        String CNIC = values[4];

        List<Account> accounts = new ArrayList<Account>();
        return new Customer(customerID, name, address, phoneNumber, CNIC, accounts);
    }

    //override default toString to get an understandable output
    @Override
    public String toString() {
        return "Customer ID: " + customerID + "\n" +
            "Name: " + name + "\n" +
            "Address: " + address + "\n" +
            "Phone Number: " + phoneNumber + "\n" +
            "CNIC: " + CNIC + "\n" +
            "Accounts: " + accounts.size();
    }
}