# ERP SYSTEM - Enterprise Resource Planning

---

## Overview
This is a comprehensive ERP (Enterprise Resource Planning) system built with pure Java.  
It manages various business operations including:

- Employee Management
- Inventory Management
- Sales & Orders
- Customer Management
- Supplier Management
- Finance & Accounting
- Reports & Analytics

---

## Intentional Code Smells & Design Violations
This project has been deliberately written with poor design patterns and code smells for educational purposes in a software design course.

### Violations

1. **God Object Anti-Pattern**
   - `ERPSystem` class acts as a god object with too many responsibilities
   - Manager classes are overly large and complex

2. **Static Abuse**
   - Excessive use of static variables (`allEmployees`, `allProducts`, etc.)
   - Global state management through static fields
   - Static scanner shared across entire application

3. **Primitive Obsession**
   - Using raw `ArrayList` and `HashMap` without generics
   - No custom collection types

4. **Long Methods**
   - Many methods exceed 50+ lines
   - Complex nested logic without proper decomposition

5. **Duplicate Code**
   - Repeated CRUD operations across different managers
   - Similar validation logic scattered throughout
   - Repeated menu display patterns

6. **No Abstraction**
   - No interfaces or abstract classes
   - Tight coupling between classes
   - Direct field access (all fields are public)

7. **Poor Encapsulation**
   - All class fields are public
   - No getters/setters
   - No data hiding

8. **Magic Numbers**
   - Hard-coded values throughout the code
   - No constants defined

9. **No Design Patterns**
   - Missing Factory, Strategy, Observer, and Singleton patterns

10. **Poor Error Handling**
    - Minimal exception handling
    - No custom exceptions
    - Silent failures in some cases

11. **Tight Coupling**
    - Classes directly reference each other
    - No dependency injection
    - Hard to test or modify

12. **Single Responsibility Violation**
    - Manager classes handle UI, business logic, and data access
    - Classes have multiple reasons to change

13. **Open/Closed Principle Violation**
    - Code requires modification to extend functionality
    - No abstraction for extension points

14. **Liskov Substitution Violation**
    - No inheritance hierarchy to violate (yet another smell)

15. **Interface Segregation Violation**
    - No interfaces at all

16. **Dependency Inversion Violation**
    - High-level modules depend on low-level modules
    - No abstraction layer

---

## Compilation and Execution

### Method 1 - Using npm scripts
```bash
npm run build    # Compiles all Java files
npm run start    # Compiles and runs the system
```

### Method 2 - Manual compilation
```bash
javac -d bin src/com/erp/*.java
java -cp bin com.erp.ERPSystem
```

### Method 3 - Compile individual files
```bash
cd src
javac com/erp/*.java
java com.erp.ERPSystem
```

---

## System Requirements
- Java JDK 8 or higher
- Terminal or Command Line interface

---

## Project Structure
```
src/com/erp/
├── ERPSystem.java           - Main entry point with static data
├── Employee.java            - Employee entity
├── EmployeeManager.java     - Employee CRUD operations
├── Product.java             - Product entity
├── InventoryManager.java    - Inventory operations
├── Customer.java            - Customer entity
├── CustomerManager.java     - Customer operations
├── Order.java               - Order entity
├── OrderItem.java           - Order line item
├── SalesManager.java        - Sales and order operations
├── Supplier.java            - Supplier entity
├── PurchaseOrder.java       - Purchase order entity
├── PurchaseOrderItem.java   - PO line item
├── SupplierManager.java     - Supplier and purchasing
├── Transaction.java         - Financial transaction
├── FinanceManager.java      - Finance and accounting
├── ReportManager.java       - Reports and analytics
├── SystemSettings.java      - System configuration
├── DataValidator.java       - Data validation utilities
├── Calculator.java          - Mathematical operations
└── Utils.java               - General utilities
```

**Total Classes:** 22  
**Total Lines of Code:** ~3000+

---

## Features

### Employee Management
- Add, view, search, update, delete employees
- Give raises and bonuses
- Department reports
- Salary calculations

### Inventory Management
- Add, view, update, delete products
- Stock management (add/remove)
- Low stock alerts
- Product categories
- Profit margin calculations

### Sales and Orders
- Create orders with multiple items
- Track order status (Pending, Confirmed, Shipped, Delivered, Cancelled)
- Process payments
- Sales statistics
- Top customers report

### Customer Management
- Add, view, search, update, delete customers
- Credit limit management
- Customer order history
- Balance reports

### Supplier Management
- Add, view, update, delete suppliers
- Create purchase orders
- Receive purchase orders (updates inventory)
- Supplier performance tracking

### Finance and Accounting
- Add income/expense transactions
- Income statement
- Expense report
- Cash flow analysis
- Profit and Loss statement
- Accounts Receivable and Payable

### Reports and Analytics
- Sales reports
- Inventory reports
- Employee reports
- Customer analysis
- Product performance
- Financial summary
- Monthly consolidated report
- Data export (CSV format)

### System Settings
- Change company name
- View system information
- Database statistics
- Clear all data
- Initialize sample data
- System backup
- User management
- Tax settings

---

## Usage Notes
- All data is stored in memory (no database)
- Sample data is initialized on startup
- Use menu numbers to navigate
- Input validation is minimal
- No authentication or authorization implemented

---

## Assignment Purpose
This codebase is intentionally poor and should be refactored to apply:

- SOLID Principles
- Design Patterns (Factory, Strategy, Observer, Singleton, etc.)
- Proper encapsulation and abstraction
- Separation of concerns
- Dependency injection
- Exception handling
- Code organization and modularization

---

## Good Luck With Your Refactoring
