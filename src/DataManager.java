import java.io.*;
import java.util.*;

public class DataManager {

    private static final String CUSTOMER_FILE = "customers.csv";
    private static final String ACCOUNT_FILE = "accounts.csv";

    // Load all customers from the CSV file
    public static Map<String, Customer> loadCustomers() {
        Map<String, Customer> customers = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Customer customer = Customer.fromCSV(line);
                    customers.put(customer.getCustomerID(), customer);
                } catch (IllegalArgumentException e) {
                    System.err.println("Skipping invalid customer record: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading customers from file: " + e.getMessage());
        }
        return customers;
    }

    // Load all accounts from the CSV file
    public static void loadAccounts(Map<String, Customer> customers,Bank bank) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] values = line.split(",");

                    String customerID = values[3].trim();
                    System.out.println("Looking up customer with ID: " + customerID);
                    Customer customer = customers.get(customerID);
                    if (customer == null) {
                        System.err.println("Customer not found for ID: " + customerID);
                    } else {
                        System.out.println("Customer with Customer ID " + customerID+ " found");
                    }

                    if (customer != null) {
                        Account account = Account.fromCSV(line, customer);
                        customer.addAccount(account);
                        bank.addAccount(account);
                    } else {
                        System.err.println("Customer not found for account: " + values[1]); // Assuming second value is the account number
                    }
                } catch (IllegalArgumentException e) {
                    System.err.println("Skipping invalid account record: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading accounts from file: " + e.getMessage());
        }
    }


    // Save all accounts to the CSV file
    public static void saveCustomers(Collection<Customer> customers) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(CUSTOMER_FILE))) {
        for (Customer customer : customers) {
            writer.write(customer.toCSV());
            writer.newLine();
        }
    } catch (IOException e) {
        System.err.println("Error writing customers to file: " + e.getMessage());
    }
}

// Save all accounts to the CSV file
    public static void saveAccounts(Collection<Account> accounts) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNT_FILE))) {
        for (Account account : accounts) {
            writer.write(account.toCSV());
            writer.newLine();
        }
    } catch (IOException e) {
        System.err.println("Error writing accounts to file: " + e.getMessage());
    }
}
}
