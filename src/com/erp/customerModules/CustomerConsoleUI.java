package com.erp.customerModules;

import com.erp.salesModules.Order;
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

    private void addCustomerUI() {
        Customer c = new Customer();

        System.out.print("Enter ID: ");
        c.customer_id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Name: ");
        c.customer_name = scanner.nextLine();

        System.out.print("Enter Email: ");
        c.customer_email = scanner.nextLine();

        System.out.print("Enter Phone: ");
        c.customer_phone = scanner.nextLine();

        System.out.print("Enter Credit Limit: ");
        c.customer_creditLimit = scanner.nextDouble();
        scanner.nextLine();

        customerService.addCustomer(c);
        System.out.println("Customer added successfully!");
    }

    private void viewAllUI() {
        for (Customer c : customerService.getAllCustomers()) {
            c.print();
            System.out.println("----");
        }
    }

    private void searchCustomerUI() {
        System.out.print("Enter Customer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Customer c = customerService.findCustomer(id);
        if (c != null) c.print();
        else System.out.println("Customer not found!");
    }

    private void updateCustomerUI() {
        System.out.print("Enter Customer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Customer c = customerService.findCustomer(id);
        if (c == null) {
            System.out.println("Customer not found!");
            return;
        }

        System.out.print("Enter new Email: ");
        c.customer_email = scanner.nextLine();

        System.out.print("Enter new Phone: ");
        c.customer_phone = scanner.nextLine();

        System.out.println("Updated successfully!");
    }

    private void deleteCustomerUI() {
        System.out.print("Enter Customer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (customerService.deleteCustomer(id))
            System.out.println("Deleted successfully!");
        else
            System.out.println("Customer not found!");
    }

    private void customerOrdersUI() {
        System.out.print("Enter Customer ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        var orders = orderService.getOrdersForCustomer(id);

        if (orders.isEmpty()) {
            System.out.println("No orders found!");
            return;
        }

        for (Order o : orders) {
            o.print();
            System.out.println("----");
        }
    }

    private void updateCreditLimitUI() {
        System.out.print("Enter Customer ID: ");
        int id = scanner.nextInt();

        System.out.print("Enter new limit: ");
        double limit = scanner.nextDouble();

        customerService.updateCreditLimit(id, limit);
        System.out.println("Credit limit updated!");
    }

    private void balanceReportUI() {
        double total = customerService.calculateTotalOutstanding();
        System.out.println("Total Outstanding Balance: $" + total);
    }
}
