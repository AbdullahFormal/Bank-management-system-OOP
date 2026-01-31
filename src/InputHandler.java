import java.util.Scanner;
import java.util.Map;

public class InputHandler {

    private static final Bank bank = new Bank("Toy Alfalah Bank");
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

            // Load customers and initialize the bank
            Map<String, Customer> customers = DataManager.loadCustomers();

            //Load accounts and add customers to the bank
            DataManager.loadAccounts(customers, bank);
            for (Customer customer : customers.values()) {
                bank.addCustomer(customer);
            }

            
        while (true) {
            showMenu();
            int choice = getInputInt();
            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    removeCustomer();
                    break;
                case 3:
                    viewCustomerDetails();
                    break;
                case 4:
                    addAccount();
                    break;
                case 5:
                    removeAccount();
                    break;
                case 6:
                    deposit();
                    break;
                case 7:
                    withdraw();
                    break;
                case 8:
                    transfer();
                    break;
                case 9:
                    viewTransactionHistory();
                    break;
                case 10:
                    showAllCustomers();
                    break;
                case 11:
                    showAllAccounts();
                    break;
                case 12:
                    showAllTellers();
                    break;
                case 13:
                    showAllATMs();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    saveData();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n--- Bank System Menu ---");
        System.out.println("1. Add Customer");
        System.out.println("2. Remove Customer");
        System.out.println("3. View Customer Details");
        System.out.println("4. Add Account");
        System.out.println("5. Remove Account");
        System.out.println("6. Deposit");
        System.out.println("7. Withdraw");
        System.out.println("8. Transfer");
        System.out.println("9. View Transaction History");
        System.out.println("10. Show All Customers");
        System.out.println("11. Show All Accounts");
        System.out.println("12. Show All Tellers");
        System.out.println("13. Show All ATMs");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addCustomer() {
        System.out.print("Enter Customer ID: ");
        String customerID = getInputString().trim();
        System.out.print("Enter Name: ");
        String name = getInputString();
        System.out.print("Enter Address: ");
        String address = getInputString();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = getInputString();
        System.out.print("Enter CNIC: ");
        String cnic = getInputString();

        Customer customer = new Customer(customerID, name, address, phoneNumber, cnic);
        bank.addCustomer(customer);
        System.out.println("Customer added successfully.");
    }

    private static void removeCustomer() {
        System.out.print("Enter Customer ID to remove: ");
        String customerID = getInputString();
        Customer customer = bank.getCustomerDetails(customerID);
        if (customer != null) {
            bank.removeCustomer(customer);
            System.out.println("Customer removed successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void viewCustomerDetails() {
        System.out.print("Enter Customer ID to view: ");
        String customerID = getInputString();
        Customer customer = bank.getCustomerDetails(customerID);
        if (customer != null) {
            System.out.println(customer);
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void addAccount() {
        System.out.print("Enter Customer ID for the account: ");
        String customerID = getInputString();
        Customer customer = bank.getCustomerDetails(customerID);
        if (customer != null) {
            System.out.print("Enter Account Type (Savings/Current): ");
            String accountType = getInputString();
            System.out.print("Enter Balance: ");
            double balance = getInputDouble();

            Account account;
            if ("Savings".equalsIgnoreCase(accountType)) {
                System.out.print("Enter Interest Rate: ");
                double interestRate = getInputDouble();
                account = new SavingsAccount(customer, balance, interestRate);
            } else if ("Current".equalsIgnoreCase(accountType)) {
                System.out.print("Enter Overdraft Limit: ");
                double overdraftLimit = getInputDouble();
                account = new CurrentAccount(customer, balance, overdraftLimit);
            } else {
                System.out.println("Invalid account type.");
                return;
            }

            bank.addAccount(account);
            System.out.println("Account added successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void removeAccount() {
        System.out.print("Enter Account Number to remove: ");
        String accountNumber = getInputString();
        Account account = bank.getAccounts().stream()
                .filter(acc -> acc.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElse(null);
        if (account != null) {
            bank.removeAccount(account);
            System.out.println("Account removed successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void deposit() {
        String accountNumber;
        Account account;
        
        while (true) {
            System.out.print("Enter Account Number to deposit into: ");
            accountNumber = getInputString();
            
            // Check if the account exists
            final String finalAccountNumber = accountNumber;
            account = bank.getAccounts().stream()
                    .filter(acc -> acc.getAccountNumber().equals(finalAccountNumber))
                    .findFirst()
                    .orElse(null);
            
            if (account != null) {
                break; // Exit the loop if account is found
            } else {
                System.out.println("Account not found. Please enter a valid account number.");
            }
        }
        
        System.out.print("Enter Amount to Deposit: ");
        double amount = getInputDouble();
        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than zero.");
            return;
        }
        
        account.deposit(amount);
        Transaction transaction = new Transaction(account.getCustomer().getCustomerID(), "Deposit", account.getAccountNumber(), amount);
        TransactionLogger.log(transaction);
        System.out.println("Deposit successful.");
    }
    
    private static void withdraw() {
        String accountNumber;
        Account account;
    
        while (true) {
            System.out.print("Enter Account Number to withdraw from: ");
            accountNumber = getInputString();
    
            // Check if the account exists
            final String finalAccountNumber = accountNumber;
            account = bank.getAccounts().stream()
                    .filter(acc -> acc.getAccountNumber().equals(finalAccountNumber))
                    .findFirst()
                    .orElse(null);
    
            if (account != null) {
                break; // Exit the loop if account is found
            } else {
                System.out.println("Account not found. Please enter a valid account number.");
            }
        }
    
        System.out.print("Enter Amount to Withdraw: ");
        double amount = getInputDouble();
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than zero.");
            return;
        }
    
        try {
            if (account.withdraw(amount)) {
                Transaction transaction = new Transaction(account.getCustomer().getCustomerID(), "Withdrawal", account.getAccountNumber(), amount);
                TransactionLogger.log(transaction);
                System.out.println("Withdrawal successful.");
            } else {
                System.out.println("Withdrawal failed due to insufficient funds.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error during withdrawal: " + e.getMessage());
        }
    }
    
    private static void transfer() {
        String sourceAccountNumber;
        String targetAccountNumber;
        Account sourceAccount;
        Account targetAccount;
    
        // Validate source account
        while (true) {
            System.out.print("Enter Source Account Number: ");
            sourceAccountNumber = getInputString();
            final String finalAccountNumber = sourceAccountNumber;
            sourceAccount = bank.getAccounts().stream()
                    .filter(acc -> acc.getAccountNumber().equals(finalAccountNumber))
                    .findFirst()
                    .orElse(null);
    
            if (sourceAccount != null) {
                break; // Exit the loop if source account is found
            } else {
                System.out.println("Source account not found. Please enter a valid account number.");
            }
        }
    
        // Validate target account
        while (true) {
            System.out.print("Enter Target Account Number: ");
            targetAccountNumber = getInputString();

            final String finalAccountNumber = targetAccountNumber;
            targetAccount = bank.getAccounts().stream()
                    .filter(acc -> acc.getAccountNumber().equals(finalAccountNumber))
                    .findFirst()
                    .orElse(null);
    
            if (targetAccount != null) {
                break; // Exit the loop if target account is found
            } else {
                System.out.println("Target account not found. Please enter a valid account number.");
            }
        }
    
        System.out.print("Enter Amount to Transfer: ");
        double amount = getInputDouble();
        if (amount <= 0) {
            System.out.println("Transfer amount must be greater than zero.");
            return;
        }
    
        try {
            if (sourceAccount.transfer(targetAccount, amount)) {
                Transaction transaction = new Transaction(sourceAccount.getCustomer().getCustomerID(), "Transfer", sourceAccount.getAccountNumber(), amount);
                TransactionLogger.log(transaction);
                System.out.println("Transfer successful.");
            } else {
                System.out.println("Transfer failed due to insufficient funds.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error during transfer: " + e.getMessage());
        }
    }
    
    private static void viewTransactionHistory() {
        System.out.print("Enter Account Number to view history: ");
        String accountNumber = getInputString();
        Account account = bank.getAccounts().stream()
                .filter(acc -> acc.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElse(null);
        if (account != null) {
            bank.getTransactionHistory(account).forEach(System.out::println);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void showAllCustomers() {
        bank.getCustomers().forEach(System.out::println);
    }

    private static void showAllAccounts() {
        bank.getAccounts().forEach(System.out::println);
    }

    private static void showAllTellers() {
        bank.getTellers().forEach(System.out::println);
    }

    private static void showAllATMs() {
        bank.getATMs().forEach(System.out::println);
    }

    private static void saveData() {

            // Save customers
            DataManager.saveCustomers(bank.getCustomers());
            // Save accounts
            DataManager.saveAccounts(bank.getAccounts());

    }

    // Utility methods for input handling
    public static String getInputString() {
        return scanner.nextLine();
    }

    public static int getInputInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a number: ");
            scanner.next(); // Clear invalid input
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // Clear the newline character left in the buffer
        return value;
    }
    
    public static double getInputDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Enter a number: ");
            scanner.next(); // Clear invalid input
        }
        double value = scanner.nextDouble();
        scanner.nextLine(); // Clear the newline character left in the buffer
        return value;
    }

    public static void validateInput(Object input, String errorMessage) {
        if (input == null || input.toString().trim().isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
