import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for Account class and its subclasses (SavingsAccount, CurrentAccount).
 * Tests deposit, withdrawal, transfer operations and validation logic.
 */
public class AccountTest {
    
    private Customer testCustomer;
    private SavingsAccount savingsAccount;
    private CurrentAccount currentAccount;
    
    @BeforeEach
    public void setUp() {
        testCustomer = new Customer("C001", "John Doe", "123 Main St", "555-1234", "12345-6789012-3");
        savingsAccount = new SavingsAccount(testCustomer, 5.0);
        currentAccount = new CurrentAccount(testCustomer, 1000.0, 50.0);
    }
    
    @Test
    public void testDepositSuccess() {
        assertTrue(savingsAccount.deposit(100.0));
        assertEquals(100.0, savingsAccount.getBalance(), 0.01);
    }
    
    @Test
    public void testDepositInvalidAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            savingsAccount.deposit(-50.0);
        });
        assertEquals("Deposit amount must be greater than zero.", exception.getMessage());
    }
    
    @Test
    public void testDepositZeroAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            savingsAccount.deposit(0);
        });
        assertEquals("Deposit amount must be greater than zero.", exception.getMessage());
    }
    
    @Test
    public void testWithdrawSuccess() {
        savingsAccount.deposit(500.0);
        assertTrue(savingsAccount.withdraw(200.0));
        assertEquals(300.0, savingsAccount.getBalance(), 0.01);
    }
    
    @Test
    public void testWithdrawInsufficientBalance() {
        savingsAccount.deposit(100.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            savingsAccount.withdraw(200.0);
        });
        assertEquals("Insufficient balance.", exception.getMessage());
    }
    
    @Test
    public void testWithdrawInvalidAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            savingsAccount.withdraw(-50.0);
        });
        assertEquals("Withdrawal amount must be greater than zero.", exception.getMessage());
    }
    
    @Test
    public void testTransferSuccess() {
        savingsAccount.deposit(500.0);
        currentAccount.deposit(100.0);
        
        assertTrue(savingsAccount.transfer(currentAccount, 200.0));
        assertEquals(300.0, savingsAccount.getBalance(), 0.01);
        assertEquals(300.0, currentAccount.getBalance(), 0.01);
    }
    
    @Test
    public void testTransferInsufficientBalance() {
        savingsAccount.deposit(100.0);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            savingsAccount.transfer(currentAccount, 200.0);
        });
        assertEquals("Insufficient balance.", exception.getMessage());
    }
    
    @Test
    public void testTransferNullTarget() {
        savingsAccount.deposit(100.0);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            savingsAccount.transfer(null, 50.0);
        });
        assertEquals("Target account cannot be null.", exception.getMessage());
    }
    
    @Test
    public void testSavingsAccountInterestApplication() {
        savingsAccount.deposit(1000.0);
        savingsAccount.specificFunctionality();
        // 5% interest on 1000 = 50
        assertEquals(1050.0, savingsAccount.getBalance(), 0.01);
    }
    
    @Test
    public void testCurrentAccountOverdraft() {
        currentAccount.deposit(500.0);
        // Withdraw beyond balance but within overdraft limit
        assertTrue(currentAccount.withdraw(1200.0));
        assertEquals(-700.0, currentAccount.getBalance(), 0.01);
    }
    
    @Test
    public void testCurrentAccountExceedsOverdraftLimit() {
        currentAccount.deposit(100.0);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            currentAccount.withdraw(1200.0); // Would exceed 1000 overdraft limit
        });
        assertEquals("Insufficient balance.", exception.getMessage());
    }
    
    @Test
    public void testCurrentAccountOverdraftFee() {
        currentAccount.deposit(100.0);
        currentAccount.withdraw(150.0); // Goes to -50
        currentAccount.applyOverdraftFee();
        // Balance should be -50 - 50 = -100
        assertEquals(-100.0, currentAccount.getBalance(), 0.01);
    }
    
    @Test
    public void testAccountCreationWithNullCustomer() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SavingsAccount(null, 5.0);
        });
        assertEquals("Customer cannot be null.", exception.getMessage());
    }
    
    @Test
    public void testSavingsAccountNegativeInterestRate() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SavingsAccount(testCustomer, -5.0);
        });
        assertEquals("Interest rate cannot be negative.", exception.getMessage());
    }
    
    @Test
    public void testCurrentAccountNegativeOverdraftLimit() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CurrentAccount(testCustomer, -100.0, 50.0);
        });
        assertEquals("Overdraft limit cannot be negative.", exception.getMessage());
    }
    
    @Test
    public void testAccountGetters() {
        savingsAccount.deposit(250.0);
        
        assertNotNull(savingsAccount.getAccountNumber());
        assertEquals(250.0, savingsAccount.getBalance(), 0.01);
        assertEquals("Savings", savingsAccount.getAccountType());
        assertEquals(testCustomer, savingsAccount.getCustomer());
    }
    
    @Test
    public void testAccountToCSV() {
        savingsAccount.deposit(100.0);
        String csv = savingsAccount.toCSV();
        
        assertTrue(csv.contains(savingsAccount.getAccountNumber()));
        assertTrue(csv.contains("100.0"));
        assertTrue(csv.contains("Savings"));
        assertTrue(csv.contains("C001"));
    }
}
