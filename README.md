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

# 🚩 Intentional Code Smells & Anti-Patterns

The following issues are purposely added to the system so you can practice detecting and refactoring them.

---

## 1. **God Object Anti-Pattern**
- `ERPSystem` stores all application data in static lists.  
- Contains the main UI loop + logic + state.  
- Manager classes also mix unrelated responsibilities.

---

## 2. **Static Abuse & Global Mutable State**
- Heavy use of public static lists (`Employees`, `Products`, `Orders`).  
- Shared `Scanner` instance across the entire application.  
- Impossible to test cleanly due to global state.

---

## 3. **Primitive Obsession**
Using primitives like `String`, `double`, `int` where Value Objects or Enums should be used.

**Examples:**
- Currency as `double`
- Phone numbers as `String`
- Status as `"PENDING"` / `"SHIPPED"` instead of Enums

---

## 4. **Long Methods (Spaghetti Code)**
- UI logic, business calculations, and printing inside one giant method.  
- 50+ line methods with deeply nested loops and conditions.

---

## 5. **Duplicate Code (DRY Violation)**
- Repeated CRUD code in each manager class.  
- Repeated input validation logic.  
- Copy-paste blocks between modules.

---

## 6. **No Abstraction**
- No interfaces.  
- No separation of layers:  
  - UI  
  - Business logic  
  - Data  
- Everything tightly coupled.

---

## 7. **Poor Encapsulation**
- Entity class fields are all `public`.  
- No getters or setters.  
- No validation for updates.

---

## 8. **Magic Numbers & Hard-Coded Strings**
**Examples:**
- `"PENDING"`, `"DELIVERED"`  
- `"Electronics"`, `"Clothing"`  
- `0.15` for tax  
- Hard-coded menu options  
- No constants or enums used.

---

## 9. **Missing Design Patterns**
The code ignores obvious opportunities to apply patterns:

| Pattern   | Should Be Used For |
|-----------|---------------------|
| Factory   | Employee creation, product creation |
| Strategy  | Tax calculation, salary calculation |
| Observer  | Stock level change alerts |
| Builder   | Order / PurchaseOrder creation |
| Singleton | System settings, logging |

---

## 10. **Poor Error Handling**
- Exceptions caught and ignored.  
- No custom exceptions.  
- Many parts can crash due to invalid input.

---

## 11. **Tight Coupling**
- Classes instantiate dependencies directly with `new`.  
- No Dependency Injection.  
- Hard to test or replace modules.

---

## 12. **Single Responsibility Principle Broken**
Manager classes handle:
- User input  
- Data validation  
- Business logic  
- Console printing  
- Data storage  
All in one place.

---

## 13. **Open/Closed Principle Violated**
- Adding a new type of employee or report requires modifying multiple existing classes.  
- No extensibility.

---

## 14. **Liskov Substitution Violations**
- Some subclasses don’t behave like their parent type.  
- Inheritance used for code-sharing instead of modeling real relationships.

---

## 15. **Interface Segregation Violations**
- No interfaces at all.  
- Classes depend on full concrete implementations.

---

## 16. **Dependency Inversion Violations**
- High-level modules depend directly on `ArrayList` and concrete classes.  
- No repository or service abstraction.

---

# ✨ Functional Features

Even with poor design, the system is feature-rich.

---

## 👥 Employee Management
- Add, view, update, search, delete employees  
- Full-time & part-time payroll  
- Department filtering  
- Bonuses and raises  

---

## 📦 Inventory & Products
- Add/update/delete products  
- Adjust stock  
- Low-stock alerts  
- Product categories  
- Inventory value calculation  

---

## 🛒 Sales & Orders
- Multi-item orders  
- Order status lifecycle  
- Tax + discount + shipping logic  
- Top customers report  

---

## 🚚 Supplier & Purchasing
- Supplier profiles  
- Purchase orders  
- Receiving goods → updates inventory  
- Supplier performance tracking  

---

## 💰 Finance & Accounting
- Income & expense records  
- Income statement  
- Expense report  
- Cash flow analysis  
- Accounts payable & receivable  

---

## 📊 Reports & Analytics
- Sales reports  
- Inventory summary  
- Employee reports  
- Monthly summary  
- CSV export (simulated)  

---

## ⚙️ System Settings
- Change company name  
- Tax percentage settings  
- Sample data initialization  
- Backup simulation  
- System usage statistics  

---

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


# 🏗️ Full Project Structure (Simplified)

```plaintext
src/com/erp/
├── ERPSystem.java
├── Employee.java
├── EmployeeManager.java
├── Product.java
├── InventoryManager.java
├── Customer.java
├── CustomerManager.java
├── Order.java
├── OrderItem.java
├── SalesManager.java
├── Supplier.java
├── PurchaseOrder.java
├── PurchaseOrderItem.java
├── SupplierManager.java
├── Transaction.java
├── FinanceManager.java
├── ReportManager.java
├── SystemSettings.java
├── DataValidator.java
├── Calculator.java
└── Utils.java



The project is organized into functional modules (though they are tightly coupled):


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
