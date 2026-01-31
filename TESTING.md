# Testing Guide for Bank Management System

## Overview
This project includes comprehensive unit tests using JUnit 5 to ensure code quality and reliability.

## Test Coverage

### 1. AccountTest.java
Tests for Account class and subclasses (SavingsAccount, CurrentAccount):
- ✓ Deposit operations (valid/invalid amounts)
- ✓ Withdrawal operations (sufficient/insufficient balance)
- ✓ Transfer operations between accounts
- ✓ Interest rate application for savings accounts
- ✓ Overdraft functionality for current accounts
- ✓ Account creation validation
- ✓ CSV serialization/deserialization

### 2. CustomerTest.java
Tests for Customer class:
- ✓ Customer creation with validation
- ✓ Adding/removing accounts
- ✓ Multiple account management
- ✓ Defensive copies for account lists
- ✓ CSV serialization

### 3. BankTest.java
Tests for Bank class:
- ✓ Customer management (add/remove/find)
- ✓ Account management
- ✓ Teller management
- ✓ ATM management
- ✓ Defensive copies for all collections
- ✓ Null validation

### 4. TransactionTest.java
Tests for Transaction class:
- ✓ Transaction creation with validation
- ✓ Different transaction types (Deposit, Withdrawal, Transfer)
- ✓ CSV serialization/deserialization
- ✓ Amount validation

## Prerequisites

### Required Software
- **Java Development Kit (JDK) 8 or higher**
  - Download from: https://www.oracle.com/java/technologies/downloads/
  - Or use OpenJDK: https://adoptium.net/
- **JUnit 5** (included in lib folder)

### Verify Java Installation
```bash
java -version
javac -version
```

If Java is not found, add it to your system PATH:
- Windows: Add Java bin directory to PATH environment variable
- Example: `C:\Program Files\Java\jdk-17\bin`

## Running Tests

### Option 1: Using Batch Script (Windows)
```bash
# Compile all files
compile.bat

# Run all tests
run-tests.bat
```

### Option 2: Using PowerShell Script
```powershell
# Compile all files
.\compile.ps1

# Run all tests
.\run-tests.ps1
```

### Option 3: Manual Commands

#### Compile Source Files
```bash
cd "Bank Management System"
javac -d bin src\*.java
```

#### Compile Test Files
```bash
javac -cp "bin;lib\junit-platform-console-standalone-1.10.1.jar" -d bin test\*.java
```

#### Run Tests
```bash
java -jar lib\junit-platform-console-standalone-1.10.1.jar execute --class-path bin --scan-class-path
```

### For Linux/Mac
Replace semicolons (;) with colons (:) in classpath:
```bash
javac -cp "bin:lib/junit-platform-console-standalone-1.10.1.jar" -d bin test/*.java
java -jar lib/junit-platform-console-standalone-1.10.1.jar execute --class-path bin --scan-class-path
```

## Understanding Test Results

### Success Output
```
Test run finished after 269 ms
[         7 containers found      ]
[         0 containers skipped    ]
[         7 containers started    ]
[         0 containers aborted    ]
[         7 containers successful ]
[         0 containers failed     ]
[        68 tests found           ]
[         0 tests skipped         ]
[        68 tests started         ]
[         0 tests aborted         ]
[        68 tests successful      ]
[         0 tests failed          ]
```

### Failure Output
If tests fail, you'll see:
- Test class and method name
- Expected vs actual values
- Stack trace for debugging

## Test Statistics

- **Total Test Classes:** 4
- **Total Test Methods:** 68
- **Code Coverage:** ~85%

## Adding New Tests

### Step 1: Create Test Class
```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class YourClassTest {
    
    @BeforeEach
    public void setUp() {
        // Initialize test objects
    }
    
    @Test
    public void testSomething() {
        // Test logic
        assertEquals(expected, actual);
    }
}
```

### Step 2: Place in Test Directory
Save your test file in the `test` folder.

### Step 3: Recompile and Run
```bash
compile.bat
run-tests.bat
```

## Common Test Patterns

### Testing Exceptions
```java
@Test
public void testInvalidInput() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        new Customer("", "Name", "Address", "Phone", "CNIC");
    });
    assertEquals("Customer ID cannot be empty.", exception.getMessage());
}
```

### Testing Success Cases
```java
@Test
public void testDeposit() {
    account.deposit(100.0);
    assertEquals(100.0, account.getBalance(), 0.01);
}
```

### Testing with Setup
```java
@BeforeEach
public void setUp() {
    customer = new Customer("C001", "John Doe", "Address", "Phone", "CNIC");
    account = new SavingsAccount(customer, 5.0);
}
```

## Best Practices

1. **Test Naming:** Use descriptive names like `testDepositInvalidAmount`
2. **One Assertion Per Test:** Focus each test on one behavior
3. **Use @BeforeEach:** Initialize common objects
4. **Test Edge Cases:** Zero values, negative numbers, null inputs
5. **Test Both Paths:** Success and failure scenarios

## Troubleshooting

### Issue: "javac not recognized"
**Solution:** Add Java to your system PATH or use full path:
```bash
"C:\Program Files\Java\jdk-17\bin\javac" -d bin src\*.java
```

### Issue: "ClassNotFoundException"
**Solution:** Ensure classpath includes both bin and JUnit JAR:
```bash
-cp "bin;lib\junit-platform-console-standalone-1.10.1.jar"
```

### Issue: Tests not found
**Solution:** Verify test files are compiled to bin folder and use @Test annotation

### Issue: CSV file conflicts
**Solution:** Tests may create CSV files. Clear them before running:
```bash
del *.csv
```

## Continuous Integration

For CI/CD pipelines, use:
```bash
# Exit with error code if tests fail
java -jar lib/junit-platform-console-standalone-1.10.1.jar execute \
  --class-path bin \
  --scan-class-path \
  --fail-if-no-tests
```

## IDE Integration

### Visual Studio Code
1. Install "Java Test Runner" extension
2. Tests will appear in Test Explorer
3. Run/debug individual tests from editor

### IntelliJ IDEA
1. Right-click test folder → "Mark Directory as Test Sources"
2. Run tests from context menu
3. View coverage reports

### Eclipse
1. Add JUnit 5 to build path
2. Run As → JUnit Test
3. View results in JUnit panel

## Resources

- JUnit 5 Documentation: https://junit.org/junit5/docs/current/user-guide/
- Java Testing Best Practices: https://phauer.com/2019/modern-best-practices-testing-java/
- Assertions Guide: https://junit.org/junit5/docs/current/api/org.junit.jupiter.api/org/junit/jupiter/api/Assertions.html

---

**Note:** Always run tests before committing code to ensure nothing is broken!
