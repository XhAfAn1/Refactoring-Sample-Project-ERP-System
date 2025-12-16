package com.erp.customerModules;

import com.erp.coreModules.ERPSystem;
import com.erp.productModules.InventoryService;
import com.erp.productModules.Product;
import com.erp.salesModules.Order;
import com.erp.salesModules.OrderCreator;

public class CustomerPortalFacade {

    private final InventoryService inventoryService;
    private final OrderCreator orderCreator;
    private Customer currentCustomer;

    public CustomerPortalFacade() {
        this.inventoryService = new InventoryService();

        this.orderCreator = new OrderCreator();
        this.orderCreator.addObserver(this.inventoryService);

    }

    public void showPortal() {
        System.out.println("\n=== WELCOME TO THE CUSTOMER PORTAL ===");
        
        if (!login()) {
            return;
        }

        while (true) {
            System.out.println("\n--- Hello, " + currentCustomer.customer_name + " ---");
            System.out.println("1. Browse Products");
            System.out.println("2. Place New Order");
            System.out.println("3. View My Order History");
            System.out.println("4. Check My Balance/Profile");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");

            int choice = ERPSystem.scanner.nextInt();
            ERPSystem.scanner.nextLine();

            switch (choice) {
                case 1 -> browseProducts();
                case 2 -> placeOrder();
                case 3 -> viewMyHistory();
                case 4 -> viewProfile();
                case 5 -> {
                    System.out.println("Logging out...");
                    currentCustomer = null;
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private boolean login() {
        System.out.print("Please enter your Customer ID to login: ");
        int id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        for (Object obj : ERPSystem.allCustomers) {
            Customer c = (Customer) obj;
            if (c.customer_id == id) {
                this.currentCustomer = c;
                System.out.println("Login Successful!");
                return true;
            }
        }
        System.out.println("Login Failed: Customer ID not found.");
        return false;
    }

    // --- FACADE METHODS (Simplifying Subsystems) ---

    private void browseProducts() {
        System.out.println("\n--- AVAILABLE PRODUCTS ---");
        for (Object obj : ERPSystem.allProducts) {
            Product p = (Product) obj;
            int stock = inventoryService.checkStock(p.product_id);
            if (stock > 0) {
                System.out.printf("ID: %d | %-15s | Price: $%-8.2f | Stock: %d%n", 
                    p.product_id, p.product_name, p.product_price, stock);
            }
        }
    }

    private void placeOrder() {
        System.out.println("\n--- PLACE ORDER ---");
        orderCreator.createOrder();
    }

    private void viewMyHistory() {
        System.out.println("\n--- MY ORDER HISTORY ---");
        boolean found = false;
        for (Object obj : ERPSystem.allOrders) {
            Order o = (Order) obj;
            if (o.customerId == currentCustomer.customer_id) {
                o.print(); 
                System.out.println("-------------------------");
                found = true;
            }
        }
        if (!found) System.out.println("No orders found.");
    }

    private void viewProfile() {
        System.out.println("\n--- MY PROFILE ---");
        currentCustomer.print();
        System.out.println("Available Credit: $" + currentCustomer.getAvailableCredit());
    }
}