package com.erp.ui;

import com.erp.Order;
import com.erp.service.CustomerService;
import com.erp.service.OrderService;
import java.util.Scanner;

public class CustomerConsoleUI {
    private final CustomerService customerService;
    private final OrderService orderService;
    private final Scanner scanner;

    public CustomerConsoleUI(CustomerService customerService, OrderService orderService, Scanner scanner) {
        this.customerService = customerService;
        this.orderService = orderService;
        this.scanner = scanner;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== CUSTOMER MANAGEMENT ===");
            System.out.println("1. Add Customer");
            System.out.println("2. View All Customers");
            System.out.println("3. Search Customer");
            System.out.println("4. Update Customer");
            System.out.println("5. Delete Customer");
            System.out.println("6. View Customer Orders");
            System.out.println("7. Update Credit Limit");
            System.out.println("8. Customer Balance Report");
            System.out.println("9. Back");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addCustomerUI();
                case 2 -> viewAllUI();
                case 3 -> searchCustomerUI();
                case 4 -> updateCustomerUI();
                case 5 -> deleteCustomerUI();
                case 6 -> customerOrdersUI(); 
                case 7 -> updateCreditLimitUI();
                case 8 -> balanceReportUI();
                case 9 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void customerOrdersUI() {
        System.out.print("Enter Customer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        var orders = orderService.getOrdersForCustomer(id);
        if (orders.isEmpty()) {
            System.out.println("No orders found for this customer!");
        } else {
            System.out.println("\n=== CUSTOMER ORDERS ===");
            for (Order o : orders) {
                o.print();
                System.out.println("---");
            }
        }
    }

    private void addCustomerUI() {
        Customer c = new Customer();
        System.out.print("Enter ID: ");
        c.id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Name: ");
        c.name = scanner.nextLine();
        // ... other inputs
        service.addCustomer(c);
        System.out.println("Customer added successfully!");
    }

    private void viewAllUI() {
        for (Customer c : service.getAllCustomers()) {
            c.print();
        }
    }

    private void searchCustomerUI() {
        System.out.print("Enter Customer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Customer c = service.findCustomer(id);
        if (c != null) c.print();
        else System.out.println("Customer not found!");
    }

    private void updateCustomerUI() {
        System.out.print("Enter Customer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter new Phone: ");
        String phone = scanner.nextLine();
        service.updateCustomerContact(id, email, phone);
        System.out.println("Updated successfully!");
    }

    private void deleteCustomerUI() {
        System.out.print("Enter Customer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Customer c = service.findCustomer(id);
        if (c != null) {
            service.getAllCustomers().remove(c);
            System.out.println("Deleted successfully!");
        } else System.out.println("Customer not found!");
    }

    private void updateCreditLimitUI() {
        System.out.print("Enter Customer ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter new limit: ");
        double limit = scanner.nextDouble();
        service.updateCreditLimit(id, limit);
    }

    private void balanceReportUI() {
        double total = service.calculateTotalOutstanding();
        System.out.println("Total Outstanding Balance: $" + total);
    }
}
