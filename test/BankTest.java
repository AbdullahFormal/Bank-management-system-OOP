import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Test suite for Bank class.
 * Tests bank operations including customer, account, teller, and ATM management.
 */
public class BankTest {
    
    private Bank bank;
    private Customer customer1;
    private Customer customer2;
    
    @BeforeEach
    public void setUp() {
        bank = new Bank("Test Bank");
        customer1 = new Customer("C001", "Alice Johnson", "100 First St", "555-0001", "11111-1111111-1");
        customer2 = new Customer("C002", "Bob Williams", "200 Second St", "555-0002", "22222-2222222-2");
    }
    
    @Test
    public void testBankCreation() {
        assertNotNull(bank);
        assertEquals(0, bank.getCustomers().size());
        assertEquals(0, bank.getAccounts().size());
    }
    
    @Test
    public void testAddCustomerSuccess() {
        bank.addCustomer(customer1);
        
        List<Customer> customers = bank.getCustomers();
        assertEquals(1, customers.size());
        assertTrue(customers.contains(customer1));
    }
    
    @Test
    public void testAddNullCustomer() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bank.addCustomer(null);
        });
        assertEquals("Customer cannot be null.", exception.getMessage());
    }
    
    @Test
    public void testAddMultipleCustomers() {
        bank.addCustomer(customer1);
        bank.addCustomer(customer2);
        
        assertEquals(2, bank.getCustomers().size());
    }
    
    @Test
    public void testRemoveCustomerSuccess() {
        bank.addCustomer(customer1);
        bank.removeCustomer(customer1);
        
        assertEquals(0, bank.getCustomers().size());
    }
    
    @Test
    public void testRemoveNullCustomer() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bank.removeCustomer(null);
        });
        assertEquals("Customer cannot be null.", exception.getMessage());
    }
    
    @Test
    public void testGetCustomerDetailsSuccess() {
        bank.addCustomer(customer1);
        
        Customer found = bank.getCustomerDetails("C001");
        assertNotNull(found);
        assertEquals("Alice Johnson", found.getName());
    }
    
    @Test
    public void testGetCustomerDetailsNotFound() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bank.getCustomerDetails("C999");
        });
        assertTrue(exception.getMessage().contains("not found"));
    }
    
    @Test
    public void testAddAccountSuccess() {
        SavingsAccount account = new SavingsAccount(customer1, 5.0);
        bank.addAccount(account);
        
        List<Account> accounts = bank.getAccounts();
        assertEquals(1, accounts.size());
        assertTrue(accounts.contains(account));
    }
    
    @Test
    public void testAddNullAccount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bank.addAccount(null);
        });
        assertEquals("Account cannot be null.", exception.getMessage());
    }
    
    @Test
    public void testRemoveAccountSuccess() {
        SavingsAccount account = new SavingsAccount(customer1, 5.0);
        bank.addAccount(account);
        bank.removeAccount(account);
        
        assertEquals(0, bank.getAccounts().size());
    }
    
    @Test
    public void testAddTellerSuccess() {
        Teller teller = new Teller("T001", "John Teller", "Branch01");
        bank.addTeller(teller);
        
        List<Teller> tellers = bank.getTellers();
        assertEquals(1, tellers.size());
        assertTrue(tellers.contains(teller));
    }
    
    @Test
    public void testAddNullTeller() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bank.addTeller(null);
        });
        assertEquals("Teller cannot be null.", exception.getMessage());
    }
    
    @Test
    public void testRemoveTellerSuccess() {
        Teller teller = new Teller("T001", "John Teller", "Branch01");
        bank.addTeller(teller);
        bank.removeTeller(teller);
        
        assertEquals(0, bank.getTellers().size());
    }
    
    @Test
    public void testAddATMSuccess() {
        ATM atm = new ATM("ATM001", "Downtown", 50000.0);
        bank.addATM(atm);
        
        List<ATM> atms = bank.getATMs();
        assertEquals(1, atms.size());
        assertTrue(atms.contains(atm));
    }
    
    @Test
    public void testAddNullATM() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bank.addATM(null);
        });
        assertEquals("ATM cannot be null.", exception.getMessage());
    }
    
    @Test
    public void testRemoveATMSuccess() {
        ATM atm = new ATM("ATM001", "Downtown", 50000.0);
        bank.addATM(atm);
        bank.removeATM(atm);
        
        assertEquals(0, bank.getATMs().size());
    }
    
    @Test
    public void testGetCustomersReturnsDefensiveCopy() {
        bank.addCustomer(customer1);
        
        List<Customer> list1 = bank.getCustomers();
        List<Customer> list2 = bank.getCustomers();
        
        assertNotSame(list1, list2);
        assertEquals(list1.size(), list2.size());
    }
    
    @Test
    public void testGetAccountsReturnsDefensiveCopy() {
        SavingsAccount account = new SavingsAccount(customer1, 5.0);
        bank.addAccount(account);
        
        List<Account> list1 = bank.getAccounts();
        List<Account> list2 = bank.getAccounts();
        
        assertNotSame(list1, list2);
        assertEquals(list1.size(), list2.size());
    }
    
    @Test
    public void testGetTellersReturnsDefensiveCopy() {
        Teller teller = new Teller("T001", "John Teller", "Branch01");
        bank.addTeller(teller);
        
        List<Teller> list1 = bank.getTellers();
        List<Teller> list2 = bank.getTellers();
        
        assertNotSame(list1, list2);
        assertEquals(list1.size(), list2.size());
    }
    
    @Test
    public void testGetATMsReturnsDefensiveCopy() {
        ATM atm = new ATM("ATM001", "Downtown", 50000.0);
        bank.addATM(atm);
        
        List<ATM> list1 = bank.getATMs();
        List<ATM> list2 = bank.getATMs();
        
        assertNotSame(list1, list2);
        assertEquals(list1.size(), list2.size());
    }
    
    @Test
    public void testBankToString() {
        bank.addCustomer(customer1);
        SavingsAccount account = new SavingsAccount(customer1, 5.0);
        bank.addAccount(account);
        
        String result = bank.toString();
        assertTrue(result.contains("Test Bank"));
        assertTrue(result.contains("Customers: 1"));
        assertTrue(result.contains("Accounts: 1"));
    }
}
