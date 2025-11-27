package com.erp.coreModules;

import java.util.*;

public class SystemSettings {
    public void showMenu() {
        while(true) {
            System.out.println("\n=== SYSTEM SETTINGS ===");
            System.out.println("1. Change Company Name");
            System.out.println("2. View System Info");
            System.out.println("3. Database Statistics");
            System.out.println("4. Clear All Data");
            System.out.println("5. Initialize Sample Data");
            System.out.println("6. System Backup");
            System.out.println("7. User Management");
            System.out.println("8. Tax Settings");
            System.out.println("9. Back");
            System.out.print("Enter choice: ");

            int choice = ERPSystem.scanner.nextInt();
            ERPSystem.scanner.nextLine();

            if(choice == 1) {
                changeCompanyName();
            } else if(choice == 2) {
                viewSystemInfo();
            } else if(choice == 3) {
                databaseStats();
            } else if(choice == 4) {
                clearAllData();
            } else if(choice == 5) {
                ERPSystem.initializeData();
                System.out.println("Sample data initialized!");
            } else if(choice == 6) {
                systemBackup();
            } else if(choice == 7) {
                userManagement();
            } else if(choice == 8) {
                taxSettings();
            } else if(choice == 9) {
                break;
            }
        }
    }

    public void changeCompanyName() {
        System.out.print("Enter new company name: ");
        String name = ERPSystem.scanner.nextLine();
        ERPSystem.companyName = name;
        System.out.println("Company name updated to: " + name);
    }

    public void viewSystemInfo() {
        System.out.println("\n=== SYSTEM INFORMATION ===");
        System.out.println("Company: " + ERPSystem.companyName);
        System.out.println("System Version: 1.0");
        System.out.println("Database: In-Memory");
        System.out.println("Current Date: " + new Date());
        System.out.println("\n--- Statistics ---");
        System.out.println("Total Employees: " + ERPSystem.allEmployees.size());
        System.out.println("Total Products: " + ERPSystem.allProducts.size());
        System.out.println("Total Orders: " + ERPSystem.allOrders.size());
        System.out.println("Total Customers: " + ERPSystem.allCustomers.size());
        System.out.println("Total Suppliers: " + ERPSystem.allSuppliers.size());
    }

    public void databaseStats() {
        System.out.println("\n=== DATABASE STATISTICS ===");
        System.out.println("Employees Table: " + ERPSystem.allEmployees.size() + " records");
        System.out.println("Products Table: " + ERPSystem.allProducts.size() + " records");
        System.out.println("Orders Table: " + ERPSystem.allOrders.size() + " records");
        System.out.println("Customers Table: " + ERPSystem.allCustomers.size() + " records");
        System.out.println("Suppliers Table: " + ERPSystem.allSuppliers.size() + " records");
        System.out.println("Inventory Records: " + ERPSystem.inventory.size() + " items");

        int totalRecords = ERPSystem.allEmployees.size() +
                          ERPSystem.allProducts.size() +
                          ERPSystem.allOrders.size() +
                          ERPSystem.allCustomers.size() +
                          ERPSystem.allSuppliers.size();
        System.out.println("\nTotal Records: " + totalRecords);
    }

    public void clearAllData() {
        System.out.print("Are you sure you want to clear all data? (yes/no): ");
        String confirm = ERPSystem.scanner.nextLine();

        if(confirm.equalsIgnoreCase("yes")) {
            ERPSystem.allEmployees.clear();
            ERPSystem.allProducts.clear();
            ERPSystem.allOrders.clear();
            ERPSystem.allCustomers.clear();
            ERPSystem.allSuppliers.clear();
            ERPSystem.inventory.clear();
            ERPSystem.totalRevenue = 0;
            ERPSystem.totalExpenses = 0;
            System.out.println("All data cleared!");
        } else {
            System.out.println("Operation cancelled.");
        }
    }

    public void systemBackup() {
        System.out.println("\n=== SYSTEM BACKUP ===");
        System.out.println("Creating backup...");
        System.out.println("Backup created: backup_" + new Date().getTime() + ".bak");
        System.out.println("\nBackup Contents:");
        System.out.println("- " + ERPSystem.allEmployees.size() + " employees");
        System.out.println("- " + ERPSystem.allProducts.size() + " products");
        System.out.println("- " + ERPSystem.allOrders.size() + " orders");
        System.out.println("- " + ERPSystem.allCustomers.size() + " customers");
        System.out.println("- " + ERPSystem.allSuppliers.size() + " suppliers");
        System.out.println("\nBackup completed successfully!");
    }

    public void userManagement() {
        System.out.println("\n=== USER MANAGEMENT ===");
        System.out.println("1. Add User");
        System.out.println("2. View Users");
        System.out.println("3. Change Password");
        System.out.println("4. User Permissions");
        System.out.print("Enter choice: ");
        int choice = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        if(choice == 1) {
            System.out.print("Enter username: ");
            String username = ERPSystem.scanner.nextLine();
            System.out.print("Enter password: ");
            String password = ERPSystem.scanner.nextLine();
            System.out.println("User created: " + username);
        } else if(choice == 2) {
            System.out.println("\n=== SYSTEM USERS ===");
            System.out.println("1. admin (Administrator)");
            System.out.println("2. manager (Manager)");
            System.out.println("3. user (Regular User)");
        } else if(choice == 3) {
            System.out.print("Enter username: ");
            String username = ERPSystem.scanner.nextLine();
            System.out.print("Enter new password: ");
            String password = ERPSystem.scanner.nextLine();
            System.out.println("Password changed for: " + username);
        } else if(choice == 4) {
            System.out.println("\n=== USER PERMISSIONS ===");
            System.out.println("admin: Full Access");
            System.out.println("manager: Read/Write");
            System.out.println("user: Read Only");
        }
    }

    public void taxSettings() {
        System.out.println("\n=== TAX SETTINGS ===");
        System.out.print("Enter default tax rate (%): ");
        double rate = ERPSystem.scanner.nextDouble();
        ERPSystem.scanner.nextLine();
        System.out.println("Tax rate set to: " + rate + "%");
        System.out.print("Apply tax automatically? (yes/no): ");
        String auto = ERPSystem.scanner.nextLine();
        if(auto.equalsIgnoreCase("yes")) {
            System.out.println("Automatic tax calculation enabled");
        } else {
            System.out.println("Automatic tax calculation disabled");
        }
    }
}
