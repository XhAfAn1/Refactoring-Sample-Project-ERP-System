# 🏭 Legacy ERP System - Refactoring Sample Project

![Java](https://img.shields.io/badge/Language-Java-orange)
![Build](https://img.shields.io/badge/Build-Javac%20%7C%20NPM-blue)
![Status](https://img.shields.io/badge/Status-Legacy%20Code-red)
![License](https://img.shields.io/badge/License-Educational-green)

## 📖 Overview

**ERP System** is a comprehensive, console-based Enterprise Resource Planning application built in pure Java. It simulates the core operational backbone of a business, managing everything from Human Resources and Inventory to Financial Accounting and Supply Chain operations.

**⚠️ Educational Purpose:**
This project is deliberately engineered with **Code Smells**, **Anti-Patterns**, and **Design Violations**. It serves as a comprehensive case study for software design courses, providing a "target rich environment" for practicing:
* Refactoring techniques (Extract Method, Introduce Parameter Object, etc.)
* Applying SOLID Principles
* Implementing Design Patterns (Strategy, Observer, Factory, Singleton, etc.)
* Unit Testing legacy code

---

## 🚩 Intentional Code Smells & Design Violations

The codebase has been deliberately written to exhibit the following poor design patterns. Students are expected to identify and remediate these specific issues:

### 1. God Object Anti-Pattern
* **The `ERPSystem` Class:** Acts as an omniscient controller with too many responsibilities, holding all global data lists (`allEmployees`, `allProducts`, etc.) and the main application loop.
* **Bloated Managers:** Classes like `EmployeeManager` and `FinanceManager` are overly large, handling unrelated tasks like data persistence and UI rendering.

### 2. Static Abuse & Global State
* **Global Mutable State:** Extensive use of `public static` variables makes the application fragile and difficult to test concurrently.
* **Shared Resources:** A static `Scanner` object is shared across the entire application, leading to potential resource management issues.

### 3. Primitive Obsession
* **Raw Collections:** Usage of `ArrayList` and `HashMap` without type parameters (Generics), leading to frequent and unsafe casting (e.g., `(Employee)list.get(i)`).
* **Lack of Domain Types:** Concepts like Currency, Email, or Phone Numbers are stored as primitive Strings or Doubles rather than rich value objects.

### 4. Long Methods
* **Spaghetti Code:** Many methods exceed 50+ lines of code with deep nesting (loops inside if-statements inside loops).
* **Complex Logic:** Business logic is interwoven with UI printing logic, making it hard to read and maintain.

### 5. Duplicate Code (DRY Violation)
* **Copy-Paste Programming:** CRUD operations (Create, Read, Update, Delete) are repeated across different manager classes with only minor variable name changes.
* **Validation:** Input validation logic is scattered throughout the code rather than centralized.

### 6. No Abstraction
* **Concrete Dependency:** High-level modules depend directly on low-level implementation details.
* **Missing Interfaces:** There is a distinct lack of interfaces or abstract classes to define contracts between modules.

### 7. Poor Encapsulation
* **Public Fields:** Almost all class fields in entities like `Employee` and `Product` are `public`, allowing external classes to modify state without validation.
* **No Accessors:** Lack of Getters and Setters prevents the implementation of validation rules during state changes.

### 8. Magic Numbers & Strings
* **Hard-Coded Values:** Strings like "PENDING" or "Electronics" and numbers for tax rates or array indices are hard-coded throughout the logic instead of using Constants or Enums.

### 9. No Design Patterns
* **Missing Patterns:** Opportunities for standard patterns are ignored:
    * *Factory* for creating Employee types.
    * *Strategy* for varying tax or salary calculations.
    * *Observer* for inventory alerts.

### 10. Poor Error Handling
* **Silent Failures:** Exceptions are often caught and ignored, or not caught at all, leading to application crashes on invalid input.
* **No Custom Exceptions:** The system relies on generic runtime exceptions.

### 11. Tight Coupling
* **Direct Instantiation:** Classes create their dependencies using `new ClassName()`, making it impossible to inject mock objects for testing.

### 12. Single Responsibility Violation (SRP)
* **Mixed Concerns:** Manager classes handle User Interface (Console I/O), Business Logic (Calculations), and Data Access (List manipulation) all in one file.

### 13. Open/Closed Principle Violation
* **Rigid Design:** Adding a new feature (e.g., a new Employee type or Report) requires modifying existing, tested code rather than extending it.

### 14. Liskov Substitution Violation
* **Inheritance Misuse:** (Where inheritance exists) Subclasses may not be truly substitutable for their parents, or inheritance is used purely for code reuse rather than "is-a" relationships.

### 15. Interface Segregation Violation
* **Lack of Interfaces:** Since interfaces are barely used, clients are forced to depend on entire concrete classes even if they only use a fraction of the functionality.

### 16. Dependency Inversion Violation
* **High-Level Coupling:** The `ERPSystem` (High Level) directly manipulates `ArrayLists` (Low Level) instead of depending on a Repository abstraction.

---

## ✨ Features

Despite the architectural flaws, the system is functionally rich:

### 👥 Employee Management
* **CRUD:** Add, View, Search, Update, and Delete employees.
* **Payroll:** Calculate salaries for Full-Time (with bonuses) and Part-Time (hourly) staff.
* **Actions:** Give raises, add bonuses, and generate department reports.

### 📦 Inventory & Products
* **Management:** Add and delete products, update prices and costs.
* **Stock Control:** Adjust stock levels, check current stock, and view low-stock alerts.
* **Organization:** Categorize products and view inventory value.

### 🛒 Sales & Orders
* **Order Processing:** Create new orders with multiple line items.
* **Lifecycle:** Track status (`PENDING` -> `SHIPPED` -> `DELIVERED` -> `CANCELLED`).
* **Financials:** Handle discounts, tax, shipping costs, and process payments.
* **Analytics:** View sales statistics and top-performing customers.

### 🚚 Supplier & Purchasing
* **Vendor Mgmt:** Maintain supplier profiles and contact info.
* **Procurement:** Create Purchase Orders (POs) for restocking.
* **Receiving:** Receive POs to automatically increment inventory stock.
* **Performance:** Track supplier ratings and total purchase volume.

### 💰 Finance & Accounting
* **Transactions:** Record Income and Expenses with categories.
* **Reports:** Generate Income Statements, Expense Reports, and Cash Flow analysis.
* **Balance Sheet:** Track Accounts Receivable (Customer debt) and Accounts Payable (Supplier debt).

### ⚙️ System Settings
* **Admin:** Change company details, manage users, and configure tax settings.
* **Maintenance:** Database statistics, system backup simulation, and data clearing.
* **Logs:** View system activity logs.

---




## 🏗️ Project Structure

The project is organized into functional modules (though they are tightly coupled):

```text
src/com/erp/
├── coreModules/          # System Entry Point & Global Config
│   ├── ERPSystem.java    # Main Class (God Object)
│   ├── SystemSettings.java
│   └── commands/         # Command classes for system settings
├── customerModules/      # Customer & Order Entities
│   ├── Customer.java
│   ├── CustomerConsoleUI.java
│   └── ...
├── employeeModules/      # HR Logic
│   ├── EmployeeManager.java
│   ├── FullTimeEmployee.java
│   └── PartTimeEmployee.java
├── financeModules/       # Financial Transactions
│   ├── FinanceManager.java
│   └── Transaction.java
├── productModules/       # Inventory Logic
│   ├── Product.java
│   ├── InventoryConsoleUI.java
│   └── ...
├── salesModules/         # Sales & Invoicing
│   ├── SalesManager.java
│   ├── Order.java
│   └── ...
├── supplierModules/      # Supply Chain
│   ├── SupplierManager.java
│   ├── PurchaseOrder.java
│   └── ...
└── reportModules/        # Analytics
    └── ReportManager.java
