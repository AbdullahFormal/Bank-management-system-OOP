import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles logging and retrieval of banking transactions.
 * Persists transactions to CSV file for audit trail and history tracking.
 * 
 * @author Bank Management System
 * @version 1.0
 */
public class TransactionLogger {

    private static final String FILE_NAME = "transactions.csv";

    /**
     * Logs a transaction to the persistent storage file.
     * 
     * @param transaction the transaction to log (cannot be null)
     * @throws IllegalArgumentException if transaction is null
     */
    public static void log(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null.");
        }
        writeTransactionToFile(transaction);
    }

    /**
     * Retrieves all logged transactions from the storage file.
     * 
     * @return List of all transactions
     */
    public static List<Transaction> getTransactions() {
        return readTransactionsFromFile();
    }

    private static void writeTransactionToFile(Transaction transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(transaction.toCSV());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing transaction to file: " + e.getMessage());
        }
    }

    private static List<Transaction> readTransactionsFromFile() {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Transaction transaction = Transaction.fromCSV(line);
                transactions.add(transaction);
            }
        } catch (IOException e) {
            System.err.println("Error reading transactions from file: " + e.getMessage());
        }
        return transactions;
    }
}
