#  Nebuchadnezzar’s ERP System - Refactoring Sample Project

![Java](https://img.shields.io/badge/Language-Java-orange) ![Build Status](https://img.shields.io/badge/Build-Passing-brightgreen) ![License](https://img.shields.io/badge/License-MIT-blue)

##  Overview
**ERP System** is a comprehensive, console-based Enterprise Resource Planning application built in pure Java. It simulates the core operational backbone of a business, managing everything from Human Resources and Inventory to Financial Accounting and Supply Chain operations.

##  Educational Purpose
This project is deliberately engineered with **Code Smells**, **Anti-Patterns**, and **Design Violations**. It serves as a comprehensive case study for software design courses, providing a "target-rich environment" for practicing:

* Applying **SOLID Principles**
* Refactoring **Code Smells**
* Implementing **Design Patterns**
* Implementing **Architectural Design**

---

##  Refactoring Timeline

### Restructuring Packages
* **Commit:** `cb3b5bb`
* **Links:** [View Changes](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/commit/cb3b5bbce9c8bfcf9d52522e804fa83912519e1a) | [Browse Repo at this Commit](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/tree/cb3b5bbce9c8bfcf9d52522e804fa83912519e1a)
* **Description:**
    In this commit, we categorized the modules and shaped a basic structure of the whole system into appropriate packages.
* **Example:**
    ```text
    // Before
    src/com/erp/Employee.java
    src/com/erp/Product.java
    
    // After
    src/com/erp/employeeModules/Employee.java
    src/com/erp/productModules/Product.java
    ```

### 1. Apply SOLID Principles

#### i. Single Responsibility Principle (SRP)
* **Commit:** `149106b`
* **Links:** [View Changes](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/commit/149106b35d88d034d0a04e5058c86a82705ac34b) | [Browse Repo at this Commit](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/tree/149106b35d88d034d0a04e5058c86a82705ac34b)
* **Description:**
    In this commit, we applied the Single Responsibility Principle on the Customer Module. Previously, the `CustomerManager` class was a "God Class" handling everything: Console UI, business logic, complex operations of customer management, and even order services. We broke the `CustomerManager` class into **5 different classes**, assigning appropriate and single responsibilities to each.
* **Example:**
    ```java
    // Before: One class doing everything
    class CustomerManager {
        showMenu() { ... }       // UI
        addCustomer() { ... }    // Logic
        searchCustomer() { ... } // Logic
        processOrder() { ... }   // Order Logic
    }

    // After: Responsibilities split into 5 classes
    class CustomerConsoleUI {
        viewAllUI() { ... }
    }

    class CustomerService {
        addCustomer(Customer c) { ... }
        getAllCustomers() { ... }
        ...
    }

    class CustomerRepository {
        // Database operations
    }

    class OrderService {
        // Order logic decoupled from Customer
    }

    class OrderRepository {
        // Order data persistence
    }
    ```

#### ii. Open-Closed Principle (OCP)
* **Commit:** `ab649ea`
* **Links:** [View Changes](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/commit/ab649eabaec7c13535966400b7ebff0c5a6a1053) | [Browse Repo at this Commit](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/tree/149106b35d88d034d0a04e5058c86a82705ac34b)
* **Description:**
    In this commit, we applied the Open/Closed Principle on the setting commands in the Core Module. We created a `SettingCommand` interface, and all system commands implemented this interface and were stored in a list. Using this method, we removed the rigid `if-else` statements that violated the Open/Closed Principle, making the system easy to extend with new commands without modifying existing code.
* **Example:**
    ```java
    // Before: SystemSettings.java (Violates OCP)
    // Adding a new setting requires modifying this method and adding another 'else if'
    public void showMenu() {
        int choice = scanner.nextInt();
        if (choice == 1) {
            changeCompanyName();
        } else if (choice == 2) {
            viewSystemInfo();
        } 
        // ... more else-ifs
    }

    // After: Refactored with Command Pattern (Follows OCP)
    
    // 1. Interface
    public interface SettingCommand {
        String getName();
        void execute();
    }

    // 2. Concrete Implementation
    public class ChangeCompanyNameCommand implements SettingCommand {
        public void execute() {
            // Logic to change name...
        }
    }

    // 3. Invoker (SystemSettings.java)
    public class SystemSettings {
        private List<SettingCommand> commands = new ArrayList<>();

        public SystemSettings() {
            commands.add(new ChangeCompanyNameCommand());
            commands.add(new ViewSystemInfoCommand());
            // New commands can be added here without changing the execution logic
        }

        public void showMenu() {
            // ...
            if (choice >= 1 && choice <= commands.size()) {
                commands.get(choice - 1).execute(); // Polymorphic execution
            }
        }
    }
    ```

#### iii. Liskov Substitution Principle (LSP)
* **Commit:** `5bf37f3`
* **Links:** [View Changes](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/commit/5bf37f3b9669b13e43fdaec8bbd2efd1dd86087c) | [Browse Repo at this Commit](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/tree/5bf37f3b9669b13e43fdaec8bbd2efd1dd86087c)
* **Description:**
    In this commit, we applied the Liskov Substitution Principle (LSP) on the Employee Module. Previously, the system relied on a single concrete `Employee` class to handle all types of staff. This structure was prone to violations where specific employee types (like part-time) might not fit the generic behavior (e.g., fixed monthly salary vs. hourly calculations), forcing the code to rely on unused fields or manual type checks.
    
    We refactored `Employee` into an **abstract base class** and separated the logic into `FullTimeEmployee` and `PartTimeEmployee` subclasses. Now, the system can interchange these subclasses wherever an `Employee` is expected without breaking functionality, as each subclass correctly implements the abstract contract (e.g., `calculateSalary()`).
* **Example:**
    ```java
    // Before: Single Generic Class (Ambiguous behavior)
    public class Employee {
        public double salary; // Unclear: Is this monthly or annual?
        // Logic often required manual checks for type
        public void print() { ... }
    }

    // After: Polymorphic Hierarchy (LSP Compliant)
    public abstract class Employee {
        public abstract double calculateSalary();
        public abstract double calculateAnnualSalary();
    }

    public class FullTimeEmployee extends Employee {
        public double monthlySalary;
        @Override
        public double calculateSalary() { return monthlySalary; }
    }

    public class PartTimeEmployee extends Employee {
        public double hourlyRate;
        public int hoursPerWeek;
        @Override
        public double calculateSalary() { return hourlyRate * hoursPerWeek * 4; }
    }
    ```

#### iv. Interface Segregation Principle (ISP)
* **Commit:** `e16eede`
* **Links:** [View Changes](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/commit/e16eede5c5f2a7d2c85e975665b2322a7033c8f6) | [Browse Repo at this Commit](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/tree/e16eede5c5f2a7d2c85e975665b2322a7033c8f6)
* **Description:**
    In this commit, we applied the Interface Segregation Principle (ISP) to the Product Module. Previously, the `InventoryManager` was a "God Class" that forced any client interacting with it to depend on all its methods—whether they were managing products, operating stock, or generating reports. This violated ISP because clients were forced to depend on methods they did not use.
    
    We refactored this by breaking the monolithic responsibility into **three distinct interfaces**:
    1. `ProductAdmin`: For administrative tasks like adding/deleting products.
    2. `StockOperator`: For operational tasks like updating stock levels.
    3. `InventoryReporter`: For read-only reporting tasks.
    
    The new `InventoryService` implements these interfaces, but clients (like a UI or a reporter) can now depend only on the specific interface they need, reducing coupling.
* **Example:**
    ```java
    // Before: One Monolithic Class (Violates ISP)
    // Clients who just want to check stock are forced to see "deleteProduct"
    class InventoryManager {
        void addProduct(Product p);
        void deleteProduct(int id);
        void updateStock(int id, int qty);
        int checkStock(int id);
        List<Product> generateReport();
    }

    // After: Segregated Interfaces (Follows ISP)
    
    // 1. For Admins
    public interface ProductAdmin {
        void addProduct(Product p, int initialStock);
        boolean deleteProduct(int id);
    }

    // 2. For Daily Operations
    public interface StockOperator {
        boolean updateStock(int id, int quantity, boolean isAdding);
        int checkStock(int id);
    }

    // 3. For Reporting
    public interface InventoryReporter {
        List<Product> getAllProducts();
        List<Product> getLowStockProducts();
    }

    // Implementation
    public class InventoryService implements ProductAdmin, StockOperator, InventoryReporter {
        // Implements all methods...
    }
    ```

#### v. Dependency Inversion Principle (DIP)
* **Commit:** `eceff4a`
* **Links:** [View Changes](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/commit/eceff4adcbc5bfbb3e00f8763b7bac306e4a64b3) | [Browse Repo at this Commit](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/tree/eceff4adcbc5bfbb3e00f8763b7bac306e4a64b3)
* **Description:**
    In this commit, we applied the Dependency Inversion Principle (DIP) to the Customer Module. Previously, the high-level `CustomerService` class depended directly on the concrete `CustomerRepository` class (which was tightly coupled to an internal `ArrayList`). This violated DIP because high-level policies should not depend on low-level implementation details.
    
    We introduced an abstraction, the `ICustomerRepository` interface. Now, `CustomerService` depends only on this interface, not the implementation. This makes the system flexible; we can easily swap the storage mechanism (e.g., from an in-memory List to a SQL Database) in the future without changing a single line of code in `CustomerService`.
* **Example:**
    ```java
    // Before: Tightly Coupled (Violates DIP)
    // CustomerService depends directly on the concrete class
    public class CustomerService {
        private final CustomerRepository repo; 

        public CustomerService(CustomerRepository repo) {
            this.repo = repo;
        }
    }

    // After: Loosely Coupled (Follows DIP)
    // 1. The Abstraction
    public interface ICustomerRepository {
        void add(Customer c);
        Customer findById(int id);
    }

    // 2. The Implementation (Low-level module)
    public class CustomerRepository implements ICustomerRepository {
        private final List<Customer> customers = new ArrayList<>();
        // ... implements methods
    }

    // 3. The High-Level Module (Depends on Abstraction)
    public class CustomerService {
        private final ICustomerRepository repo; // Depends on Interface

        public CustomerService(ICustomerRepository repo) {
            this.repo = repo;
        }
    }
    ```

---

### 2. Refactoring Code Smells

#### i. Dead Code
* **Commit:** `44789dd`
* **Links:** [View Changes](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/commit/44789dd4e55ec6ac049fb566b950d68cc2eece89) | [Browse Repo at this Commit](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/tree/44789dd4e55ec6ac049fb566b950d68cc2eece89)
* **Description:**
    Removed unused variables and methods that were cluttering different classes. Also Removed all the unused files & classes(e.g., `Test.java`, `Calculator.java`, `DataValidator.java`).

#### ii. Comments
* **Commit:** `72191fa`
* **Links:** [View Changes](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/commit/72191fae9297624eb538443ac70606e307e794ee) | [Browse Repo at this Commit](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/tree/72191fae9297624eb538443ac70606e307e794ee)
* **Description:**
    Deleted obvious comments that explained *what* the code was doing instead of *why*. As the whole project was AI-generated, so every file had unnecessary comments.
* **Example:**
    ```java
    // Before
    
    // THE ABSTRACTION
    public interface ICustomerRepository {
      void add(Customer c);
      List<Customer> getAll();
    }
    // After
    
    public interface ICustomerRepository {
      void add(Customer c);
      List<Customer> getAll();
    }
    ```

#### iii. Inappropriate Naming
* **Commit:** `a807352`
* **Links:** [View Changes](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/commit/a8073520c953e1aa11eff6e404b42263b396b441) | [Browse Repo at this Commit](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/tree/a8073520c953e1aa11eff6e404b42263b396b441)
* **Description:**
    In this commit, we addressed the "Inappropriate Naming" code smell. Previously, multiple classes (`Employee`, `Product`, `Customer`) used identical generic variable names like `id`, `name`, and `department`. This made the code difficult to read and debug, especially when multiple objects were being processed in the same context. We renamed these fields to be entity-specific (e.g., changing `id` to `employee_id` or `product_id`) to clearly indicate which entity the data belongs to.
* **Example:**
    ```java
    // Before: Ambiguous generic names
    public class Employee {
        public int id;
        public String name;
        public String email;
    }

    // After: Explicit, context-aware names
    public class Employee {
        public int employee_id;
        public String employee_name;
        public String employee_email;
    }
    ```

#### iv. Feature Envy
* **Commit:** `3beb6a1`
* **Links:** [View Changes](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/commit/3beb6a169e5c115ed73f4b66d6716565ba8c0bb9) | [Browse Repo at this Commit](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/tree/3beb6a169e5c115ed73f4b66d6716565ba8c0bb9)
* **Description:**
    In this commit, we resolved "Feature Envy," where methods in one class were overly interested in the data of another class.
    1.  **Order Class:** The `printDetailed()` method in `Order` was directly accessing and calculating data belonging to `OrderItem` (like price and quantity) to print details. We moved this logic into `OrderItem` by creating a `printDetails()` method.
    2.  **SupplierManager Class:** The `updateSupplier` method was manually setting multiple fields of the `Supplier` object (`phone`, `email`, `rating`). We encapsulated this behavior by creating a `updateContactInfo()` method inside `Supplier`, allowing the manager to simply pass the new values.
* **Example:**
    ```java
    // Before: Order class accessing OrderItem's internal data
    // (Feature Envy: Order is envious of OrderItem's fields)
    for (OrderItem item : items) {
        System.out.println("Price: " + item.price);
        System.out.println("Subtotal: " + (item.price * item.quantity));
    }

    // After: Logic moved to the class that owns the data
    for (OrderItem item : items) {
        item.printDetails(); // Order asks OrderItem to do the work
    }
    ```
    ```java
    // Before: SupplierManager manually updating fields
    s.supplier_phone = newPhone;
    s.supplier_email = newEmail;
    s.supplier_rating = newRating;

    // After: Using a method in Supplier to handle updates
    s.updateContactInfo(newPhone, newEmail, newRating);
    ```

#### v. Long Method
* **Commit:** `40b6ca5`
* **Links:** [View Changes](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/commit/40b6ca5aff19a09dcfe8de32a2ee217fd4a69d14) | [Browse Repo at this Commit](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/tree/40b6ca5aff19a09dcfe8de32a2ee217fd4a69d14)
* **Description:**
    Decomposed the massive `SupplierManager` method into smaller helper methods (`addItemsToOrder`, `applyCharges`).
* **Example:**
    ```java
  LATER
    ```

#### vi. Long Parameter List
* **Commit:** `f7ab8f8`
* **Links:** [View Changes](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/commit/f7ab8f826021ec94f6db9ad9c82b68bae7dfdd5e) | [Browse Repo at this Commit](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/tree/f7ab8f826021ec94f6db9ad9c82b68bae7dfdd5e)
* **Description:**
    In this commit, we refactored the `OrderItem` class to eliminate the "Long Parameter List" smell. The constructor previously required four separate arguments (`id`, `name`, `quantity`, `price`), forcing the client code to manually extract and pass these fields. This was error-prone and verbose.
    
    We applied the **Preserve Whole Object** refactoring technique (a variation of Introduce Parameter Object). Instead of passing flattened data, we now pass the entire `Product` object to the constructor. This reduced the parameter count, improved code readability, and encapsulated the logic of extracting product details inside `OrderItem`.
* **Example:**
    ```java
    // Before: Constructor requires extracting specific fields manually
    public OrderItem(int pid, String pname, int qty, double price) { ... }
    
    // Usage:
    new OrderItem(p.id, p.name, qty, p.price);

    // After: Passing the whole object (Cleaner and safer)
    public OrderItem(Product product, int qty) {
        this.productId = product.product_id;
        this.productName = product.product_name;
        // ...
    }
    
    // Usage:
    new OrderItem(product, qty);
    ```

#### vii. Duplicated Code
* **Commit:** `03a8dda`
* **Links:** [View Changes](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/commit/03a8ddabdb65d21326f1fb8da1a089278780d0e5) | [Browse Repo at this Commit](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/tree/03a8ddabdb65d21326f1fb8da1a089278780d0e5)
* **Description:**
    In this commit, we addressed the "Duplicated Code" smell in the `FinanceManager` class. The methods `incomeStatement()` and `expenseReport()` contained nearly identical logic for iterating through transactions, filtering by type, and aggregating totals by category. Similarly, the logic for calculating total employee salaries was repeated in multiple places.
    
    We applied the **Extract Method** refactoring technique. We created a reusable helper method `generateCategoryReport()` that accepts parameters for the report title and transaction type, and a `calculateTotalMonthlySalaries()` method to centralize the payroll calculation. This adheres to the DRY (Don't Repeat Yourself) principle, reducing the risk of inconsistencies.
* **Example:**
    ```java
    // Before: Identical logic repeated in two methods
    public void incomeStatement() {
        // ... loop through transactions ...
        if(t.type.equals("INCOME")) {
            // add to map, sum total
        }
    }

    public void expenseReport() {
        // ... IDENTICAL loop through transactions ...
        if(t.type.equals("EXPENSE")) {
            // add to map, sum total
        }
    }

    // After: Common logic extracted to a helper method
    private void generateCategoryReport(String title, String type) {
        // ... Single loop implementation ...
        if(t.type.equals(type)) { ... }
    }

    public void incomeStatement() {
        generateCategoryReport("INCOME STATEMENT", "INCOME");
    }

    public void expenseReport() {
        generateCategoryReport("EXPENSE REPORT", "EXPENSE");
    }
    ```

#### viii. Large Class
* **Commit:** `ae5402c`
* **Links:** [View Changes](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/commit/ae5402c3b91622b423c2a13864c2617a672a52a9) | [Browse Repo at this Commit](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/tree/ae5402c3b91622b423c2a13864c2617a672a52a9)
* **Description:**
    Broken down `SalesManager` into `OrderCreator`, `OrderViewer`, `OrderUpdater`, and `SalesAnalytics`.
* **Example:**
    ```java
    // Before
    class SalesManager {
        void createOrder() { ... }
        void viewOrder() { ... }
        void updateStatus() { ... }
        void getStats() { ... }
    }

    // After
    class SalesManager {
        private final OrderCreator orderCreator = new OrderCreator();
        private final SalesAnalytics salesAnalytics = new SalesAnalytics();
    }
    ```

#### ix. Switch Statements
* **Commit:** `afccbaf`
* **Links:** [View Changes](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/commit/afccbaf1029fcd4c150e35ffb3b32cf86a0d2ee9) | [Browse Repo at this Commit](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/tree/afccbaf1029fcd4c150e35ffb3b32cf86a0d2ee9)
* **Description:**
    In this commit, we addressed the "Switch Statements" code smell in `SalesManager`. The `showMenu` method relied on a rigid `switch` statement to handle user selections. Such structures often violate the Open/Closed Principle because adding a new option requires modifying the control flow logic.
    
    We refactored this by introducing a `Map<Integer, Runnable>` to store the menu actions. Instead of switching on the input, the system now looks up the corresponding method reference in the map and executes it. This replaces conditional logic with a cleaner, data-driven approach.
* **Example:**
    ```java
    // Before: Rigid Switch Statement
    switch (choice) {
        case 1 -> orderCreator.createOrder();
        case 2 -> orderViewer.viewAllOrders();
        case 3 -> orderViewer.viewOrderDetails();
        // ... more cases
        default -> System.out.println("Invalid choice!");
    }

    // After: Data-Driven Map Lookup
    // 1. Setup Map
    private final Map<Integer, Runnable> menuActions = new HashMap<>();
    public SalesManager() {
        menuActions.put(1, orderCreator::createOrder);
        menuActions.put(2, orderViewer::viewAllOrders);
        // ...
    }

    // 2. Execution
    Runnable action = menuActions.get(choice);
    if (action != null) {
        action.run();
    }
    ```

#### x. Speculative Generality
* **Commit:** `d8fd5c3`
* **Links:** [View Changes](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/commit/d8fd5c3bc6f82f441d8b867525cb463ad8bdd745) | [Browse Repo at this Commit](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/tree/d8fd5c3bc6f82f441d8b867525cb463ad8bdd745)
* **Description:**
    In this commit, we addressed the "Speculative Generality" code smell. Previously, we had defined several interfaces (`ProductAdmin`, `StockOperator`, `InventoryReporter`) that were only implemented by a single class (`InventoryService`).
    
    While interfaces are useful for polymorphism, creating them "just in case" we might need another implementation in the future is a form of over-engineering. Since there was no immediate functional need for these abstractions, we removed the unused interfaces and collapsed the hierarchy, keeping only the concrete `InventoryService` class. This simplifies the codebase and adheres to the **YAGNI** (You Ain't Gonna Need It) principle.
* **Example:**
    ```java
    // Before: Unnecessary Interfaces (Over-Engineering)
    public interface ProductAdmin { ... }
    public interface StockOperator { ... }
    
    public class InventoryService implements ProductAdmin, StockOperator {
        @Override
        public void addProduct(Product p) { ... }
    }

    // After: Simplified Concrete Class
    // Interfaces deleted.
    public class InventoryService {
        public void addProduct(Product p) { ... }
    }
    ```

#### xi. Primitive Obsession
* **Commit:** `59b40bc`
* **Links:** [View Changes](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/commit/59b40bcbfa5064d5fdc2f16f6968bef987e278ba) | [Browse Repo at this Commit](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/tree/59b40bcbfa5064d5fdc2f16f6968bef987e278ba)
* **Description:**
    In this commit, we addressed the "Primitive Obsession" code smell. The application was using primitive types (specifically `String` literals like "INCOME", "PENDING", "PAID") to represent domain concepts like transaction types and order statuses. This is error-prone because a simple typo (e.g., "IMCOME") would not be caught by the compiler, leading to runtime bugs.
    
    We introduced distinct Java Enums: `OrderStatus`, `TransactionType`, and `PaymentStatus`. We then updated the core logic in `FinanceManager`, `Order`, and `SalesManager` to use these enums instead of raw strings. This enforces type safety and makes the code self-documenting.
* **Example:**
    ```java
    // Before: Using raw Strings (Error-prone)
    if (transaction.type.equals("INCOME")) { ... }
    
    // Typo risk:
    order.status = "CANCLED"; // Compiler won't catch this!

    // After: Using Enums (Type-safe)
    if (transaction.type == TransactionType.INCOME) { ... }
    
    // Compiler error:
    // order.status = OrderStatus.CANCLED; // Error: Symbol not found
    order.status = OrderStatus.CANCELLED;  // Correct
    ```

---

### 3. Apply Design Patterns

#### i. Factory Pattern
* **Commit:** `ff50b56`
* **Links:** [View Changes](https://github.com/ummeMuqaddisa/Refactoring-Sample-Project-ERP-System/commit/ff50b56a1161991c2d270ca1ed8ae382290400a9) | [Browse Repo at this Commit](https://github.com/ummeMuqaddisa/Refactoring-Sample-Project-ERP-System/tree/ff50b56a1161991c2d270ca1ed8ae382290400a9)
* **Description:**
    In this commit, we implemented the **Factory Pattern** to solve the issue of tight coupling in object creation. Previously, the `addEmployee` method directly instantiated `FullTimeEmployee` objects, making the system inflexible.
    
    We introduced an `EmployeeFactory` class with a static creation method. Now, the `EmployeeManager` delegates the responsibility of instantiation to the factory. Based on the input type (1 for Full Time, 2 for Part Time), the factory returns the appropriate subclass. This separates the *logic of creation* from the *logic of usage*, adhering to the Open/Closed Principle.
* **Example:**
    ```java
    // Before: Tightly coupled instantiation (Hardcoded)
    public void addEmployee() {
        FullTimeEmployee e = new FullTimeEmployee(); // Can only create one type
        // ...
    }

    // After: Decoupled creation using Factory
    public class EmployeeFactory {
        public static Employee createEmployee(int type) {
            switch (type) {
                case 1: return new FullTimeEmployee();
                case 2: return new PartTimeEmployee();
                default: return null;
            }
        }
    }

    // Client Code
    int type = scanner.nextInt();
    Employee e = EmployeeFactory.createEmployee(type); // Dynamic instantiation
    ```

#### ii. Singleton Pattern
* **Commit:** `92a98bd`
* **Links:** [View Changes](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/commit/92a98bdda5896aff909087fb14b364a187cc679d) | [Browse Repo at this Commit](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/tree/92a98bdda5896aff909087fb14b364a187cc679d)
* **Description:**
    In this commit, we applied the **Singleton Pattern** to manage the application's in-memory "database". Previously, all data lists (e.g., `allEmployees`, `allProducts`) were public static fields in the main `ERPSystem` class. This unregulated global state is a bad practice.
    
    We moved these lists into a dedicated `DatabaseContext` class that ensures only one instance of the data store exists throughout the application lifecycle. Access to this data is now controlled via the `getInstance()` method, providing a centralized point for data initialization and access.
* **Example:**
    ```java
    // Before: Uncontrolled Global State
    public class ERPSystem {
        public static ArrayList allEmployees = new ArrayList();
        public static void initializeData() { ... }
    }

    // After: Controlled Access via Singleton
    public class DatabaseContext {
        private static DatabaseContext instance;
        public ArrayList allEmployees;

        private DatabaseContext() { ... } // Private Constructor

        public static synchronized DatabaseContext getInstance() {
            if (instance == null) instance = new DatabaseContext();
            return instance;
        }
    }

    // Usage
    DatabaseContext.getInstance().allEmployees.add(newEmployee);
    ```

#### iii. Builder Pattern
* **Commit:** `8c88552`
* **Links:** [View Changes](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/commit/8c885528bbf8158dc97009a17aa14b2fe14277ae) | [Browse Repo at this Commit](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/tree/8c885528bbf8158dc97009a17aa14b2fe14277ae)
* **Description:**
    In this commit, we applied the **Builder Pattern** to the `Product` class. The class contains many attributes (ID, name, price, cost, category, dimensions, etc.), leading to object creation code that was cluttered with manual field assignments. This is often associated with the "Telescoping Constructor" anti-pattern or inconsistent object states during initialization.
    
    We introduced a static inner `Builder` class. This allows clients to construct `Product` objects using a fluent API (method chaining). It makes the code significantly more readable and handles optional parameters gracefully without requiring multiple constructor overloads. **Crucially, it allows us to build the object with only our desired variables**, as using a traditional constructor for a class with so many fields is inflexible and error-prone.
* **Example:**
    ```java
    // Before: Cluttered initialization with manual field setting
    Product p = new Product();
    p.product_id = 1;
    p.product_name = "Laptop";
    p.product_price = 1200.0;
    p.product_category = "Electronics";
    // ... potentially missing fields or setting them in wrong order

    // After: Fluent, Readable Construction with specific variables
    Product p = new Product.Builder()
            .withId(1)
            .withName("Laptop")
            .withPrice(1200.0)
            .withCategory("Electronics")
            // We can skip optional fields like 'dimensions' or 'weight' easily
            .build();
    ```

#### iv. Strategy Pattern
* **Commit:** `4515201`
* **Links:** [View Changes](https://github.com/ummeMuqaddisa/Refactoring-Sample-Project-ERP-System/commit/451520184c2fd6f1b0dfc52bbb6c0ed2361327b5) | [Browse Repo at this Commit](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/tree/451520184c2fd6f1b0dfc52bbb6c0ed2361327b5)
* **Description:**
    In this commit, we applied the **Strategy Pattern** to the Reporting module. The `ReportManager` class was previously a monolithic class containing distinct logic for every type of report (Sales, Inventory, Employee, etc.), which violated the Single Responsibility and Open/Closed principles.
    
    We refactored this by defining a `ReportStrategy` interface. We then extracted the logic for each report type into its own concrete strategy class (`SalesReportStrategy`, `InventoryReportStrategy`, etc.). The `ReportManager` now maintains a map of these strategies and delegates the report generation to the appropriate strategy at runtime. This allows new reports to be added simply by creating a new class, without modifying the manager.
* **Example:**
    ```java
    // Before: Monolithic Manager (Rigid)
    public class ReportManager {
        public void salesReport() { /* ... */ }
        public void inventoryReport() { /* ... */ }
        public void employeeReport() { /* ... */ }
    }

    // After: Strategy Pattern (Flexible)
    // 1. The Strategy Interface
    public interface ReportStrategy {
        void generate();
        String getName();
    }

    // 2. Concrete Strategies
    public class SalesReportStrategy implements ReportStrategy {
        public void generate() { /* Sales Logic */ }
    }

    // 3. The Context (ReportManager) delegates
    strategies.get(choice).generate();
    ```

#### v. Observer Pattern (Publisher-Subscriber)
* **Commit:** `975c46d`
* **Links:** [View Changes](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/commit/975c46d9f4997361b438b7cecf54cf25ead7d6f7) | [Browse Repo at this Commit](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/tree/975c46d9f4997361b438b7cecf54cf25ead7d6f7)
* **Description:**
    In this commit, we implemented the **Observer Pattern**, effectively creating a **Publisher-Subscriber** system to decouple order processing. Previously, the `OrderCreator` (Publisher) was tightly coupled to `InventoryService` and `SalesAnalytics`, manually calling their update methods.
    
    We introduced an `OrderObserver` interface (Subscriber). The `OrderCreator` now maintains a dynamic list of these observers. When an order is placed, it acts as a publisher, iterating through its list and broadcasting the event by calling `onOrderPlaced()` on every registered subscriber. This allows multiple subsystems to "subscribe" to order events and react independently without the publisher needing to know their specific implementation details.
* **Example:**
    ```java
    // 1. Subscriber Interface
    public interface OrderObserver {
        void onOrderPlaced(Order order);
    }

    // 2. Publisher (Subject)
    public class OrderCreator {
        // List of Subscribers
        private List<OrderObserver> observers = new ArrayList<>();

        // Method to Subscribe
        public void addObserver(OrderObserver observer) {
            observers.add(observer);
        }

        // Broadcast Event to All Subscribers
        private void notifyObservers(Order order) {
            for (OrderObserver observer : observers) {
                observer.onOrderPlaced(order);
            }
        }

        public void createOrder() {
            // ... logic ...
            notifyObservers(order); // Notify all listeners!
        }
    }

    // 3. Concrete Subscriber
    public class InventoryService implements OrderObserver {
        @Override
        public void onOrderPlaced(Order order) {
            System.out.println("Inventory updating for new order...");
            // ... logic to deduct stock ...
        }
    }
    ```

#### vi. Facade Pattern
* **Commit:** `692ccd6`
* **Links:** [View Changes](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/commit/692ccd6505526162b3851d8b7064bdfc1e1443e5) | [Browse Repo at this Commit](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System/tree/692ccd6505526162b3851d8b7064bdfc1e1443e5)
* **Description:**
    In this commit, we applied the **Facade Pattern** to simplify the interface for the Customer Portal. The system involves complex interactions between multiple classes—specifically `InventoryService`, `OrderCreator`, and `CustomerRepository`. Exposing this complexity to the main client makes the code hard to use and maintain.
    
    We introduced the `CustomerPortalFacade` class. This class **holds objects of different subsystems** internally (aggregating `InventoryService` and `OrderCreator`). It acts as a unified interface where the user can perform various tasks—like browsing products (via Inventory) or placing orders (via OrderCreator)—simply by interacting with the Facade. This hides the messy initialization and wiring logic from the main application.
* **Example:**
    ```java
    // 1. The Facade Class (Holds different subsystem objects)
    public class CustomerPortalFacade {
        // The Facade manages these complex objects for you
        private final InventoryService inventoryService;
        private final OrderCreator orderCreator;

        public CustomerPortalFacade() {
            // Initialization and wiring happen hidden inside the Facade
            this.inventoryService = new InventoryService();
            this.orderCreator = new OrderCreator();
            this.orderCreator.addObserver(this.inventoryService); 
        }

        public void showPortal() {
            // User can access different features through one entry point
            // 1. Browse Products (Delegates to InventoryService)
            // 2. Place Order (Delegates to OrderCreator)
        }
    }

    // 2. Client Usage (Simple)
    // The main program doesn't need to worry about Inventory or Orders objects
    CustomerPortalFacade portal = new CustomerPortalFacade();
    portal.showPortal(); 
    ```
---
### 4. Architectural Design
---
[Image](www.example.com)
---
##  How to Run the Project

Since this is a console-based Java application, you can run it using any Java IDE (like IntelliJ IDEA, Eclipse, or NetBeans) or via the command line.

### Prerequisites
* **Java Development Kit (JDK):** Version 8 or higher.

### Option 1: Using an IDE (Recommended)
1.  **Clone the Repository:**
    ```bash
    git clone [https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System.git](https://github.com/XhAfAn1/Refactoring-Sample-Project-ERP-System.git)
    ```
2.  **Open in IDE:**
    * Open IntelliJ IDEA (or your preferred IDE).
    * Select `File` > `Open` and navigate to the cloned project folder.
3.  **Run the Application:**
    * Navigate to `src/com/erp/coreModules/ERPSystem.java`.
    * Right-click on the file and select `Run 'ERPSystem.main()'`.
    * The application will start in the IDE's console window.

### Option 2: Using Command Line
1.  **Navigate to the Source Directory:**
    ```bash
    cd Refactoring-Sample-Project-ERP-System/src
    ```
2.  **Compile the Java Files:**
    ```bash
    javac com/erp/coreModules/ERPSystem.java
    ```
3.  **Run the Main Class:**
    ```bash
    java com.erp.coreModules.ERPSystem
    ```

### Usage
Once running, follow the on-screen menu prompts to navigate the ERP system.
* Enter numbers (e.g., `1`, `2`) to select menu options.
* Follow the instructions to add employees, manage inventory, place orders, etc.
* **Note:** Since the database is in-memory (simulated), all data will be reset when you close the application.
---

##  Functional Features
Even with the architectural shortcomings, the system implements a comprehensive set of business operations.

###  Employee Management
* **CRUD Operations:** Complete lifecycle management to Add, View, Search, Update, and Delete employee records.
* **Payroll Logic:** Differentiated salary calculations for Full-Time (monthly salary + bonuses) and Part-Time (hourly rate × hours) staff.
* **HR Actions:** Utilities to grant percentage-based raises, issue bonuses, and generate departmental distribution reports.

###  Inventory & Products
* **Catalog Management:** Tools to create, delete, and update product details including pricing and cost.
* **Stock Control:** Features to manually update stock, check current levels, and view Low-Stock Alerts based on reorder levels.
* **Valuation:** Categorization of products and calculation of total inventory asset value.

###  Sales & Orders
* **Order Processing:** Creation of multi-item orders with automatic subtotal calculation.
* **Lifecycle Tracking:** State machine managing statuses: `PENDING` → `CONFIRMED` → `SHIPPED` → `DELIVERED` or `CANCELLED`.
* **Financial Logic:** Application of Discounts, Tax, and Shipping Costs to the final order total.
* **Analytics:** Generation of sales statistics (revenue, avg. order value) and identification of Top Customers.

###  Supplier & Purchasing
* **Vendor Profiles:** Management of supplier contact info, addresses, and performance ratings.
* **Procurement:** Creation of Purchase Orders (POs) to restock inventory from specific suppliers.
* **Receiving Workflow:** Functionality to "receive" Purchase Orders, which automatically increments product stock levels.
* **Performance Tracking:** Monitoring of supplier ratings and total purchase history.

###  Finance & Accounting
* **Transaction Ledger:** Recording of income and expenses with metadata (Date, Category, Description).
* **Financial Statements:** On-demand generation of Income Statements, Expense Reports, Cash Flow analysis, and Profit & Loss reports.
* **Balance Sheet:** Tracking of Accounts Receivable (customer debt) and Accounts Payable (unpaid supplier POs).

###  Reports & Analytics
* **Strategy-Based Engine:** A flexible reporting system using the Strategy Pattern to generate various summaries.
* **Report Types:**
    * **Sales Report:** Order breakdowns and revenue stats.
    * **Inventory Report:** Stock levels and valuation.
    * **Employee Report:** Payroll and department stats.
    * **Monthly Report:** High-level system overview.

###  System Settings
* **Configuration:** Management of global settings like Company Name and Default Tax Rate.
* **Data Maintenance:** Tools for database statistics, Simulated System Backup, and clearing all data.
* **User Management:** A console-based interface for adding users and managing permissions (Admin/Manager/User).

###  Customer Portal (Facade)
* **Self-Service Interface:** A dedicated portal allowing customers to log in and browse the product catalog.
* **Portal Actions:** Customers can independently place orders, view their personal order history, and check their credit balance.

---

##  Full Project Structure

### Initial Project Structure

```text
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
# Configuration Files
package.json
package-lock.json
index.js
README.md
.gitignore
.gitattributes
```

### After Refactoring

```text
src/com/erp/
├── coreModules/          # System Entry Point & Global Config
│   ├── ERPSystem.java    # Main Class (God Object)
│   ├── DatabaseContext.java
│   ├── SystemSettings.java
│   ├── Utils.java
│   ├── SettingCommand.java
│   └── commands/         # Command Pattern Implementations
│       ├── ChangeCompanyNameCommand.java
│       ├── ClearAllDataCommand.java
│       ├── DatabaseStatsCommand.java
│       ├── InitializeSampleDataCommand.java
│       ├── SystemBackupCommand.java
│       ├── SystemLogsCommand.java
│       ├── TaxSettingsCommand.java
│       ├── UserManagementCommand.java
│       └── ViewSystemInfoCommand.java
├── customerModules/      # Customer Management & Portal
│   ├── Customer.java
│   ├── CustomerConsoleUI.java
│   ├── CustomerPortalFacade.java
│   ├── CustomerRepository.java
│   ├── CustomerService.java
│   ├── ICustomerRepository.java
│   ├── OrderRepository.java
│   └── OrderService.java
├── employeeModules/      # HR & Payroll Logic
│   ├── Employee.java
│   ├── EmployeeFactory.java
│   ├── EmployeeManager.java
│   ├── FullTimeEmployee.java
│   └── PartTimeEmployee.java
├── financeModules/       # Accounting & Ledger
│   ├── FinanceManager.java
│   └── Transaction.java
├── productModules/       # Inventory & Catalog
│   ├── InventoryConsoleUI.java
│   ├── InventoryService.java
│   └── Product.java
├── salesModules/         # Orders & Revenue Analysis
│   ├── Order.java
│   ├── OrderCreator.java
│   ├── OrderItem.java
│   ├── OrderObserver.java
│   ├── OrderUpdater.java
│   ├── OrderViewer.java
│   ├── SalesAnalytics.java
│   └── SalesManager.java
├── supplierModules/      # Procurement & Vendor Mgmt
│   ├── PurchaseOrder.java
│   ├── PurchaseOrderItem.java
│   ├── Supplier.java
│   └── SupplierManager.java
├── reportModules/        # Reporting Engine (Strategy Pattern)
│   ├── EmployeeReportStrategy.java
│   ├── InventoryReportStrategy.java
│   ├── MonthlyReportStrategy.java
│   ├── ReportManager.java
│   ├── ReportStrategy.java
│   └── SalesReportStrategy.java
└── enums/                # Enumerations
    ├── OrderStatus.java
    ├── PaymentStatus.java
    └── TransactionType.java

# Configuration Files
package.json
package-lock.json
index.js
README.md
.gitignore
.gitattributes

```
---

##  Technical Limitations
* **In-Memory Database:** All data is stored in static lists. Data is lost when the application restarts.
* **Single User:** Designed for a single concurrent console user.
* **Security:** Passwords are stored in plain text (Simulated auth).
