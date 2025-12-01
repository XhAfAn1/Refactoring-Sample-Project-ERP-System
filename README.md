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

# 🏭 Refactoring 

![Java](https://img.shields.io/badge/Language-Java-orange)
![Build](https://img.shields.io/badge/Build-NPM%20%7C%20Javac-blue)
![Status](https://img.shields.io/badge/Status-Legacy%20Code-red)

## 📖 Overview

**ERP System** is a console-based Enterprise Resource Planning application written in pure Java. It simulates a monolithic legacy codebase containing HR, Supply Chain, Sales, and Financial Accounting functionalities.

> **⚠️ Warning**  
> This project intentionally contains **bad design**, **code smells**, and **SOLID violations** to serve as a refactoring practice system.

---

## 🏗️ Project Architecture — *Before Refactoring*

The system uses a **God Class Architecture**, with tight coupling and global static state.

### 📂 Directory Structure
```text
src/com/erp/
├── coreModules/
│   ├── ERPSystem.java
│   ├── SystemSettings.java
│   └── Utils.java
├── customerModules/
│   ├── Customer.java
│   ├── CustomerManager.java
│   └── OrderService.java
├── employeeModules/
│   ├── Employee.java
│   ├── FullTimeEmployee.java
│   ├── PartTimeEmployee.java
│   └── EmployeeManager.java
├── financeModules/
├── productModules/
├── reportModules/
├── salesModules/
└── supplierModules/

# 🏭 Refactoring 

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
The code ignores obvious opportunities to apply pat

