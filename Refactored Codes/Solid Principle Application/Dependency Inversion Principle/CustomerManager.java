package com.erp;

public class CustomerManager {
    private CustomerRepository customerRepo;
    private OrderRepository orderRepo;

    public CustomerManager(CustomerRepository customerRepo, OrderRepository orderRepo) {
        this.customerRepo = customerRepo;
        this.orderRepo = orderRepo;
    }

    public void addCustomer() {
        Customer c = new Customer();
        // ... gather input as before ...
        customerRepo.addCustomer(c);
        System.out.println("Customer added successfully!");
    }

    public void viewAll() {
        System.out.println("\n=== ALL CUSTOMERS ===");
        for (Customer c : customerRepo.getAllCustomers()) {
            c.print();
        }
    }

    public void searchCustomer() {
        System.out.print("Enter Customer ID: ");
        int id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        Customer c = customerRepo.getCustomerById(id);
        if (c != null) {
            c.print();
            System.out.println("Available Credit: $" + c.getAvailableCredit());
        } else {
            System.out.println("Customer not found!");
        }
    }

    public void customerOrders() {
        System.out.print("Enter Customer ID: ");
        int id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        List<Order> orders = orderRepo.getOrdersByCustomerId(id);
        if (orders.isEmpty()) {
            System.out.println("No orders found for this customer!");
            return;
        }

        System.out.println("\n=== CUSTOMER ORDERS ===");
        for (Order o : orders) {
            o.print();
            System.out.println("---");
        }
    }


}
