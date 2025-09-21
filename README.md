```
 _____  _       _ _        _   _       ____              _    
|  __ \(_)     (_) |      | | | |     |  _ \            | |   
| |  | |_  __ _ _| |_ __ _| | | |__   | |_) | __ _ _ __ | | __
| |  | | |/ _` | | __/ _` | | | '_ \  |  _ < / _` | '_ \| |/ /
| |__| | | (_| | | || (_| | | | |_) | | |_) | (_| | | | |   < 
|_____/|_|\__, |_|\__\__,_|_| |_.__/  |____/ \__,_|_| |_|_|\_\
           __/ |                                              
          |___/                                               
```

# Java Digital Banking Console Application

## 📋 Project Overview

A Java console application for digital banking that allows users to create, manage, and view bank accounts, as well as perform common banking operations. This project serves as a foundation for Java learners to practice object-oriented programming, business rule implementation, and financial data handling.

## 🎯 Educational Objectives

- Implement object-oriented programming (entities, services, repositories, separation of concerns)
- Properly handle financial amounts using BigDecimal (precision, 2 decimal places)
- Implement basic authentication (email + password) and session management
- Apply banking business rules (balances, transfers, account closure)
- Produce structured, readable, and testable code

## 🔑 Key Features

### Authentication & Profile Management
- **Registration**: Create an account with full name, email (unique), address, and password (min 6 characters)
- **Login**: Access the system using email and password
- **Session Management**: Authenticated users can access their resources; logout functionality
- **Profile Updates**: Modify email and/or address information
- **Password Changes**: Update password (minimum 6 characters)

### Bank Account Management
- **Account Creation**: Generate unique readable IDs (e.g., BK-XXXX-1234), with initial balance of 0 and active status
- **Account Listing**: Users can view only their own accounts
- **Balance Inquiry**: Check balance by account
- **Account Closure**: Only by the account owner and if the balance is 0; account status changed to inactive (no physical deletion)

### Transaction Processing
- **Deposits**: Increase balance; record a DEPOSIT transaction
- **Withdrawals**: Decrease balance if sufficient funds available; record a WITHDRAW transaction
- **Internal Transfers**:
  - Between existing accounts A → B: Double entry — TRANSFEROUT on A, TRANSFERIN on B
  - To unknown destination: Debit on A and TRANSFEROUT entry with counterparty (external ID); no internal credit
- **Transaction History**: Chronological sorting; detailed display (date/time, type, amount, description, potential counterparty)

### Console Interface
- Clear menus, explicit error messages, guided input
- Input validation: numeric values, amounts > 0, max 2 decimal places, non-empty email, password ≥ 6 characters

## 📐 Architecture

### Domain Model
- **User**: id: UUID, fullName: String, email: String, address: String
- **Account**: accountId: String, ownerUserId: UUID, balance: BigDecimal(2), createdAt: Instant, active: boolean
- **Transaction**: id: UUID, timestamp: Instant, accountId: String, type: {DEPOSIT, WITHDRAW, TRANSFERIN, TRANSFEROUT}, amount: BigDecimal(2), counterpartyAccountId?: String, description?: String

### Software Components
- **Main (Console UI)**: Menus, inputs, displays, flow control
- **Services**:
  - AuthService: Registration, login, session, profile, password
  - AccountService: Creation, listing, closure, owner & balance checks
  - TransactionService: Deposit, withdrawal, transfer, history consultation
- **Repositories**:
  - Interfaces: UserRepository, AccountRepository, TransactionRepository
  - In-memory implementations: InMemoryUserRepository, InMemoryAccountRepository, InMemoryTransactionRepository
- **Utils**: Console input, amount parsing/validation

## 💼 Business Rules

- **Currency**: BigDecimal scale = 2; amount > 0; ≤ 2 decimal places
- **Withdrawal/Transfer**: Rejected if insufficient balance
- **Closure**: Only by owner and if balance = 0
- **Ownership**: A user can only operate on their own accounts
- **Traceability**: Any operation modifies the balance and adds an immutable history line
- **Inactivity**: Any operation on an inactive account is rejected
- **Email uniqueness**: Registration fails if the email already exists

## 👤 User Flow

### Welcome Menu (Not Connected)
- Register
- Login
- Exit

### Main Menu (Connected as "[username]")
- Create account
- List my accounts
- Deposit
- Withdraw
- Transfer
- Transaction history
- Update profile
- Change password
- Close account
- Logout
- Exit

## 🧪 Test Data & Scenarios

### Test Users
- alice@example.com (pwd ≥ 6)
- bob@example.com (pwd ≥ 6)

### Test Scenarios
1. Alice creates accounts A1 & A2; deposits 500 on A1; transfers 200 from A1→A2; withdraws 50 from A2
2. Bob creates account B1; attempts to withdraw 100 from a 0 balance → fails; deposits 100; withdraws 80 → succeeds
3. Closing account A2 if balance is 0; otherwise rejected

## ✅ Acceptance Criteria

- Functional registration/login; email uniqueness checked
- Account creation/listing/viewing/closing compliant with rules
- Deposits/Withdrawals/Transfers: Correct balance updates + proper history recording
- History sorted by date with type, amount, description (and counterparty if transfer)
- Clear error messages (invalid amount, insufficient balance, inactive account, non-owner, etc.)

## 🚀 Getting Started

### Prerequisites
- Java JDK 8 or higher
- Maven or Gradle (for dependency management)

### Installation
1. Clone the repository
   ```bash
   git clone [repository-url]
   ```
2. Navigate to the project directory
   ```bash
   cd java-digital-banking
   ```
3. Build the project
   ```bash
   mvn clean install
   ```
   or
   ```bash
   gradle build
   ```

### Running the Application
```bash
java -jar target/digital-banking-1.0.jar
```

## 📝 License

This project is licensed under the UM6P-FrJ License - see the LICENSE file for details.
