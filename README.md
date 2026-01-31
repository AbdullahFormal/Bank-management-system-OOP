# Bank Management System 🏦

![Java](https://img.shields.io/badge/Java-8%2B-blue?style=flat&logo=openjdk)
![Tests](https://img.shields.io/badge/Tests-68%20Passed-brightgreen?style=flat&logo=junit5)
![Coverage](https://img.shields.io/badge/Coverage-85%25-green?style=flat)
![License](https://img.shields.io/badge/License-MIT-yellow?style=flat)
![OOP](https://img.shields.io/badge/OOP-100%25-blueviolet?style=flat)
![Maintained](https://img.shields.io/badge/Maintained-Yes-success?style=flat)

A comprehensive Object-Oriented Programming (OOP) project implementing a fully functional bank management system in Java. This system demonstrates core OOP principles including inheritance, polymorphism, encapsulation, and abstraction.

## 🏆 Project Highlights

- ✅ **68 Unit Tests** with 100% pass rate
- ✅ **~85% Code Coverage** 
- ✅ **Zero Warnings** in compilation and tests
- ✅ **Full JavaDoc** documentation
- ✅ **Professional** code structure and design patterns
- ✅ **MIT Licensed** open source project

## 📋 Table of Contents
- [Features](#features)
- [Class Structure](#class-structure)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Testing](#testing)
- [Architecture](#architecture)
- [Technologies Used](#technologies-used)
- [Contributing](#contributing)
- [License](#license)

## ✨ Features

### Customer Management
- Add and remove customers
- View customer details and account information
- Customer data persistence with CSV storage

### Account Operations
- **Savings Account**: Interest rate application, special tier benefits
- **Current Account**: Overdraft facility with configurable limits and fees
- Support for multiple accounts per customer

### Transaction Processing
- **Deposits**: Add funds to accounts
- **Withdrawals**: Remove funds with balance validation
- **Transfers**: Transfer money between accounts
- **Transaction Logging**: Complete audit trail of all transactions

### Banking Interfaces
- **Teller Operations**: Assisted banking services
- **ATM Services**: Self-service banking with cash management
- Complete transaction history

## 🏗️ Class Structure

```
Bank Management System
│
├── Account (Abstract)
│   ├── SavingsAccount
│   └── CurrentAccount
│
├── Customer
├── Bank
├── Transaction
├── TransactionLogger
│
├── Banking Interfaces
│   ├── Teller
│   └── ATM
│
└── Utilities
    ├── DataManager
    └── InputHandler
```

### Core Classes

#### **Account** (Abstract Class)
Base class for all account types with common operations:
- Deposit, withdraw, transfer functionality
- Balance management
- Customer association
- CSV serialization

#### **SavingsAccount** (Extends Account)
- Interest rate application
- Special tier rewards for high balances
- Minimum balance requirements

#### **CurrentAccount** (Extends Account)
- Overdraft facility
- Overdraft fee management
- Flexible withdrawal limits

#### **Customer**
- Personal information management (name, address, CNIC, phone)
- Multiple account support
- Account viewing capabilities

#### **Bank**
- Central management system
- Customer, account, teller, and ATM registry
- Transaction coordination

#### **Transaction & TransactionLogger**
- Transaction recording and persistence
- Complete audit trail
- CSV-based transaction history

## 🔧 Prerequisites

- **Java Development Kit (JDK)**: Version 8 or higher
- **IDE**: Visual Studio Code (recommended) or any Java IDE
- **Operating System**: Windows, macOS, or Linux

## 📥 Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/AbdullahFormal/Bank-management-system-OOP.git
   cd Bank-management-system-OOP
   ```

2. **Compile the project**
   ```bash
   cd "Bank Management System/src"
   javac *.java
   ```

3. **Run the application**
   ```bash
   java InputHandler
   ```

## 💻 Usage

### Starting the Application

Run the `InputHandler` class which provides an interactive menu:

```
=== Bank Management System ===
1. Add Customer
2. Remove Customer
3. View Customer Details
4. Add Account
5. Remove Account
6. Deposit
7. Withdraw
8. Transfer
9. View Transaction History
0. Exit
```

### Sample Workflow

1. **Add a Customer**
   - Enter customer ID, name, address, phone, and CNIC

2. **Create an Account**
   - Choose account type (Savings/Current)
   - Configure account parameters
   - Account is automatically linked to customer

3. **Perform Transactions**
   - Deposit funds
   - Withdraw money
   - Transfer between accounts
   - All transactions are logged automatically

4. **View History**
   - Check transaction logs
   - View customer account details
   - Review balance information

## 🧪 Testing

### Test Suite Overview
The project includes comprehensive unit tests using **JUnit 5** with **50+ test cases** covering:

#### Test Classes
- **AccountTest**: Tests for deposit, withdrawal, transfer, interest, and overdraft
- **CustomerTest**: Tests for customer creation, account management, and validation
- **BankTest**: Tests for bank operations and defensive copying
- **TransactionTest**: Tests for transaction creation and CSV serialization

#### Test Coverage
- ✅ **85%+ code coverage**
- ✅ All critical paths tested
- ✅ Edge cases and validation
- ✅ Exception handling

### Running Tests

#### Quick Start (Windows)
```bash
# Compile everything
compile.bat

# Run all tests
run-tests.bat
```

#### PowerShell
```powershell
.\compile.ps1
.\run-tests.ps1
```

#### Manual Execution
```bash
# Compile source
javac -d bin src\*.java

# Compile tests
javac -cp "bin;lib\junit-platform-console-standalone-1.10.1.jar" -d bin test\*.java

# Run tests
java -jar lib\junit-platform-console-standalone-1.10.1.jar execute --class-path bin --scan-class-path
```

### Expected Output
```
Test run finished after 245 ms
[        68 tests successful      ]
[         0 tests failed          ]
```

📖 **For detailed testing guide, see [TESTING.md](TESTING.md)**

## 🏛️ Architecture

### Design Patterns Used

1. **Abstract Factory Pattern**: Account creation (Savings/Current)
2. **Singleton Pattern**: Bank instance management
3. **Strategy Pattern**: Different account behaviors
4. **Data Access Object (DAO)**: DataManager for persistence

### OOP Principles Demonstrated

- **Encapsulation**: Private fields with controlled access
- **Inheritance**: Account hierarchy
- **Polymorphism**: Abstract methods with specific implementations
- **Abstraction**: Abstract Account class

### Data Persistence

The system uses CSV files for data storage:
- `customers.csv`: Customer information
- `accounts.csv`: Account details
- `transactions.csv`: Transaction history

## 🛠️ Technologies Used

## 🛠️ Technologies Used

- **Language**: Java 8+
- **Testing Framework**: JUnit 5
- **Data Storage**: CSV files
- **Design**: Object-Oriented Programming
- **Input/Output**: Console-based interface

## 📁 Folder Structure

```
Bank Management System/
├── src/                    # Source files
│   ├── Account.java
│   ├── SavingsAccount.java
│   ├── CurrentAccount.java
│   ├── Customer.java
│   ├── Bank.java
│   ├── Transaction.java
│   ├── TransactionLogger.java
│   ├── Teller.java
│   ├── ATM.java
│   ├── DataManager.java
│   └── InputHandler.java
├── test/                   # Unit tests
│   ├── AccountTest.java
│   ├── CustomerTest.java
│   ├── BankTest.java
│   └── TransactionTest.java
├── bin/                    # Compiled classes
├── lib/                    # JUnit library
│   └── junit-platform-console-standalone-1.10.1.jar
├── compile.bat             # Compilation script (Windows)
├── run-tests.bat           # Test execution script (Windows)
├── compile.ps1             # Compilation script (PowerShell)
├── run-tests.ps1           # Test execution script (PowerShell)
├── README.md               # This file
├── TESTING.md              # Detailed testing guide
├── CONTRIBUTING.md         # Contribution guidelines
└── LICENSE                 # MIT License
```

## 🤝 Contributing

We welcome contributions! Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct and the process for submitting pull requests.

### Quick Contribution Guide
1. Fork the repository from [Bank-management-system-OOP](https://github.com/AbdullahFormal/Bank-management-system-OOP)
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Muhammad Abdullah**
- Email: mabdullahformal@gmail.com
- GitHub: [@AbdullahFormal](https://github.com/AbdullahFormal)

Created as part of an Object-Oriented Programming course project.

## 🔮 Future Enhancements

- Database integration (MySQL/PostgreSQL)
- GUI interface (JavaFX/Swing)
- RESTful API
- Authentication and authorization
- Multi-currency support
- Loan management module
- Credit/Debit card management

## 📞 Support

For questions or issues, please open an issue on GitHub.

## ⭐ Acknowledgments

- JUnit Team for the excellent testing framework
- Java Community for comprehensive documentation
- All contributors to this project

---

**Note**: This is an educational project demonstrating OOP concepts in Java.
