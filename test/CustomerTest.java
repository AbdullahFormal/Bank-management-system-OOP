import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Test suite for Customer class.
 * Tests customer creation, account management, and validation logic.
 */
public class CustomerTest {
    
    private Customer customer;
    
    @BeforeEach
    public void setUp() {
        customer = new Customer("C001", "Jane Smith", "456 Oak Ave", "555-5678", "12345-6789012-3");
    }
    
    @Test
    public void testCustomerCreationSuccess() {
        assertNotNull(customer);
        assertEquals("C001", customer.getCustomerID());
        assertEquals("Jane Smith", customer.getName());
    }
    
    @Test
    public void testCustomerCreationEmptyID() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Customer("", "John Doe", "123 Main St", "555-1234", "12345-6789012-3");
        });
        assertEquals("Customer ID cannot be empty.", exception.getMessage());
    }
    
    @Test
    public void testCustomerCreationNullName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Customer("C002", null, "123 Main St", "555-1234", "12345-6789012-3");
        });
        assertEquals("Name cannot be empty.", exception.getMessage());
    }
    
    @Test
    public void testCustomerCreationEmptyAddress() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Customer("C003", "John Doe", "", "555-1234", "12345-6789012-3");
        });
        assertEquals("Address cannot be empty.", exception.getMessage());
    }
    
    @Test
    public void testCustomerCreationEmptyPhone() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Customer("C004", "John Doe", "123 Main St", "", "12345-6789012-3");
        });
        assertEquals("Phone number cannot be empty.", exception.getMessage());
    }
    
    @Test
    public void testCustomerCreationEmptyCNIC() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Customer("C005", "John Doe", "123 Main St", "555-1234", "");
        });
        assertEquals("CNIC cannot be empty.", exception.getMessage());
    }
    
    @Test
    public void testAddAccountSuccess() {
        SavingsAccount account = new SavingsAccount(customer, 5.0);
        customer.addAccount(account);
        
        List<Account> accounts = customer.viewAccounts();
        assertEquals(1, accounts.size());
        assertTrue(accounts.contains(account));
    }
    
    @Test
    public void testAddNullAccount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.addAccount(null);
        });
        assertEquals("Account cannot be null.", exception.getMessage());
    }
    
    @Test
    public void testAddMultipleAccounts() {
        SavingsAccount savings = new SavingsAccount(customer, 5.0);
        CurrentAccount current = new CurrentAccount(customer, 1000.0, 50.0);
        
        customer.addAccount(savings);
        customer.addAccount(current);
        
        List<Account> accounts = customer.viewAccounts();
        assertEquals(2, accounts.size());
    }
    
    @Test
    public void testRemoveAccountSuccess() {
        SavingsAccount account = new SavingsAccount(customer, 5.0);
        customer.addAccount(account);
        customer.removeAccount(account);
        
        List<Account> accounts = customer.viewAccounts();
        assertEquals(0, accounts.size());
    }
    
    @Test
    public void testRemoveNullAccount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            customer.removeAccount(null);
        });
        assertEquals("Account cannot be null.", exception.getMessage());
    }
    
    @Test
    public void testViewAccountsReturnsDefensiveCopy() {
        SavingsAccount account = new SavingsAccount(customer, 5.0);
        customer.addAccount(account);
        
        List<Account> accounts1 = customer.viewAccounts();
        List<Account> accounts2 = customer.viewAccounts();
        
        assertNotSame(accounts1, accounts2);
        assertEquals(accounts1.size(), accounts2.size());
    }
    
    @Test
    public void testCustomerToString() {
        String result = customer.toString();
        
        assertTrue(result.contains("C001"));
        assertTrue(result.contains("Jane Smith"));
        assertTrue(result.contains("456 Oak Ave"));
        assertTrue(result.contains("555-5678"));
        assertTrue(result.contains("12345-6789012-3"));
    }
    
    @Test
    public void testCustomerToCSV() {
        SavingsAccount account = new SavingsAccount(customer, 5.0);
        customer.addAccount(account);
        
        String csv = customer.toCSV();
        
        assertTrue(csv.contains("C001"));
        assertTrue(csv.contains("Jane Smith"));
        assertTrue(csv.contains("456 Oak Ave"));
        assertTrue(csv.contains(account.getAccountNumber()));
    }
}
