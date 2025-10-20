package com.erp;

import java.util.*;

public class CustomerManager {
    public void showMenu() {
        while(true) {
            System.out.println("\n=== CUSTOMER MANAGEMENT ===");
            System.out.println("1. Add Customer");
            System.out.println("2. View All Customers");
            System.out.println("3. Search Customer");
            System.out.println("4. Update Customer");
            System.out.println("5. Delete Customer");
            System.out.println("6. Customer Orders");
            System.out.println("7. Update Credit Limit");
            System.out.println("8. Customer Balance Report");
            System.out.println("9. Back");
            System.out.print("Enter choice: ");

            int choice = ERPSystem.scanner.nextInt();
            ERPSystem.scanner.nextLine();

            if(choice == 1) {
                addCustomer();
            } else if(choice == 2) {
                viewAll();
            } else if(choice == 3) {
                searchCustomer();
            } else if(choice == 4) {
                updateCustomer();
            } else if(choice == 5) {
                deleteCustomer();
            } else if(choice == 6) {
                customerOrders();
            } else if(choice == 7) {
                updateCreditLimit();
            } else if(choice == 8) {
                balanceReport();
            } else if(choice == 9) {
                break;
            }
        }
    }

    public void addCustomer() {
        Customer c = new Customer();
        System.out.print("Enter Customer ID: ");
        c.id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();
        System.out.print("Enter Name: ");
        c.name = ERPSystem.scanner.nextLine();
        System.out.print("Enter Email: ");
        c.email = ERPSystem.scanner.nextLine();
        System.out.print("Enter Phone: ");
        c.phone = ERPSystem.scanner.nextLine();
        System.out.print("Enter Address: ");
        c.address = ERPSystem.scanner.nextLine();
        System.out.print("Enter Credit Limit: ");
        c.creditLimit = ERPSystem.scanner.nextDouble();
        ERPSystem.scanner.nextLine();
        System.out.print("Enter Customer Type: ");
        c.type = ERPSystem.scanner.nextLine();

        ERPSystem.allCustomers.add(c);
        System.out.println("Customer added successfully!");
    }

    public void viewAll() {
        System.out.println("\n=== ALL CUSTOMERS ===");
        for(int i = 0; i < ERPSystem.allCustomers.size(); i++) {
            Customer c = (Customer)ERPSystem.allCustomers.get(i);
            System.out.println("\n--- Customer " + (i+1) + " ---");
            c.print();
        }
    }

    public void searchCustomer() {
        System.out.print("Enter Customer ID: ");
        int id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        for(int i = 0; i < ERPSystem.allCustomers.size(); i++) {
            Customer c = (Customer)ERPSystem.allCustomers.get(i);
            if(c.id == id) {
                c.print();
                System.out.println("Available Credit: $" + c.getAvailableCredit());
                return;
            }
        }
        System.out.println("Customer not found!");
    }

    public void updateCustomer() {
        System.out.print("Enter Customer ID: ");
        int id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        for(int i = 0; i < ERPSystem.allCustomers.size(); i++) {
            Customer c = (Customer)ERPSystem.allCustomers.get(i);
            if(c.id == id) {
                System.out.print("Enter new email (current: " + c.email + "): ");
                c.email = ERPSystem.scanner.nextLine();
                System.out.print("Enter new phone (current: " + c.phone + "): ");
                c.phone = ERPSystem.scanner.nextLine();
                System.out.println("Customer updated!");
                return;
            }
        }
        System.out.println("Customer not found!");
    }

    public void deleteCustomer() {
        System.out.print("Enter Customer ID: ");
        int id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        for(int i = 0; i < ERPSystem.allCustomers.size(); i++) {
            Customer c = (Customer)ERPSystem.allCustomers.get(i);
            if(c.id == id) {
                ERPSystem.allCustomers.remove(i);
                System.out.println("Customer deleted!");
                return;
            }
        }
        System.out.println("Customer not found!");
    }

    public void customerOrders() {
        System.out.print("Enter Customer ID: ");
        int id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        System.out.println("\n=== CUSTOMER ORDERS ===");
        boolean found = false;
        for(int i = 0; i < ERPSystem.allOrders.size(); i++) {
            Order o = (Order)ERPSystem.allOrders.get(i);
            if(o.customerId == id) {
                o.print();
                System.out.println("---");
                found = true;
            }
        }

        if(!found) {
            System.out.println("No orders found for this customer!");
        }
    }

    public void updateCreditLimit() {
        System.out.print("Enter Customer ID: ");
        int id = ERPSystem.scanner.nextInt();
        System.out.print("Enter new credit limit: ");
        double limit = ERPSystem.scanner.nextDouble();
        ERPSystem.scanner.nextLine();

        for(int i = 0; i < ERPSystem.allCustomers.size(); i++) {
            Customer c = (Customer)ERPSystem.allCustomers.get(i);
            if(c.id == id) {
                c.creditLimit = limit;
                System.out.println("Credit limit updated!");
                System.out.println("Available credit: $" + c.getAvailableCredit());
                return;
            }
        }
        System.out.println("Customer not found!");
    }

    public void balanceReport() {
        System.out.println("\n=== CUSTOMER BALANCE REPORT ===");
        double totalOutstanding = 0;
        for(int i = 0; i < ERPSystem.allCustomers.size(); i++) {
            Customer c = (Customer)ERPSystem.allCustomers.get(i);
            if(c.currentBalance > 0) {
                System.out.println("\nCustomer: " + c.name);
                System.out.println("Outstanding Balance: $" + c.currentBalance);
                System.out.println("Credit Limit: $" + c.creditLimit);
                System.out.println("Available Credit: $" + c.getAvailableCredit());
                totalOutstanding += c.currentBalance;
            }
        }
        System.out.println("\nTotal Outstanding: $" + totalOutstanding);
    }
}
