import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for Transaction class.
 * Tests transaction creation, validation, and CSV serialization.
 */
public class TransactionTest {
    
    private Transaction transaction;
    
    @BeforeEach
    public void setUp() {
        transaction = new Transaction("C001", "ACC123", "Deposit", 500.0);
    }
    
    @Test
    public void testTransactionCreationSuccess() {
        assertNotNull(transaction);
        assertEquals("C001", transaction.getCustomerID());
        assertEquals("ACC123", transaction.getAccountNumber());
        assertEquals("Deposit", transaction.getType());
        assertEquals(500.0, transaction.getAmount(), 0.01);
    }
    
    @Test
    public void testTransactionCreationEmptyCustomerID() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Transaction("", "ACC123", "Deposit", 500.0);
        });
        assertEquals("Customer ID cannot be empty.", exception.getMessage());
    }
    
    @Test
    public void testTransactionCreationNullCustomerID() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Transaction(null, "ACC123", "Deposit", 500.0);
        });
        assertEquals("Customer ID cannot be empty.", exception.getMessage());
    }
    
    @Test
    public void testTransactionCreationEmptyAccountNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Transaction("C001", "", "Deposit", 500.0);
        });
        assertEquals("Account number cannot be empty.", exception.getMessage());
    }
    
    @Test
    public void testTransactionCreationNullAccountNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Transaction("C001", null, "Deposit", 500.0);
        });
        assertEquals("Account number cannot be empty.", exception.getMessage());
    }
    
    @Test
    public void testTransactionCreationEmptyType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Transaction("C001", "ACC123", "", 500.0);
        });
        assertEquals("Transaction type cannot be empty.", exception.getMessage());
    }
    
    @Test
    public void testTransactionCreationNullType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Transaction("C001", "ACC123", null, 500.0);
        });
        assertEquals("Transaction type cannot be empty.", exception.getMessage());
    }
    
    @Test
    public void testTransactionCreationZeroAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Transaction("C001", "ACC123", "Deposit", 0.0);
        });
        assertEquals("Amount must be greater than zero.", exception.getMessage());
    }
    
    @Test
    public void testTransactionCreationNegativeAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Transaction("C001", "ACC123", "Deposit", -100.0);
        });
        assertEquals("Amount must be greater than zero.", exception.getMessage());
    }
    
    @Test
    public void testTransactionToCSV() {
        String csv = transaction.toCSV();
        
        assertEquals("C001,ACC123,Deposit,500.0", csv);
    }
    
    @Test
    public void testTransactionFromCSVSuccess() {
        String csv = "C002,ACC456,Withdrawal,250.0";
        Transaction t = Transaction.fromCSV(csv);
        
        assertNotNull(t);
        assertEquals("C002", t.getCustomerID());
        assertEquals("ACC456", t.getAccountNumber());
        assertEquals("Withdrawal", t.getType());
        assertEquals(250.0, t.getAmount(), 0.01);
    }
    
    @Test
    public void testTransactionFromCSVInvalidFormat() {
        String csv = "C001,ACC123,Deposit"; // Missing amount
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Transaction.fromCSV(csv);
        });
        assertEquals("Invalid CSV format for Transaction.", exception.getMessage());
    }
    
    @Test
    public void testTransactionToString() {
        String result = transaction.toString();
        
        assertTrue(result.contains("C001"));
        assertTrue(result.contains("ACC123"));
        assertTrue(result.contains("Deposit"));
        assertTrue(result.contains("500"));
    }
    
    @Test
    public void testDifferentTransactionTypes() {
        Transaction deposit = new Transaction("C001", "ACC123", "Deposit", 500.0);
        Transaction withdrawal = new Transaction("C001", "ACC123", "Withdrawal", 200.0);
        Transaction transfer = new Transaction("C001", "ACC123", "Transfer", 300.0);
        
        assertEquals("Deposit", deposit.getType());
        assertEquals("Withdrawal", withdrawal.getType());
        assertEquals("Transfer", transfer.getType());
    }
}
