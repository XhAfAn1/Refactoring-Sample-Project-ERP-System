package com.erp.salesModules;

import com.erp.coreModules.ERPSystem;

public class SalesManager {

    private final OrderCreator orderCreator = new OrderCreator();
    private final OrderViewer orderViewer = new OrderViewer();
    private final OrderUpdater orderUpdater = new OrderUpdater();
    private final SalesAnalytics salesAnalytics = new SalesAnalytics();

    public void showMenu() {
        while (true) {
            System.out.println("\n=== SALES & ORDERS ===");
            System.out.println("1. Create New Order");
            System.out.println("2. View All Orders");
            System.out.println("3. View Order Details");
            System.out.println("4. Update Order Status");
            System.out.println("5. Cancel Order");
            System.out.println("6. Process Payment");
            System.out.println("7. Sales Statistics");
            System.out.println("8. Top Customers");
            System.out.println("9. Back");
            System.out.print("Enter choice: ");

            int choice = ERPSystem.scanner.nextInt();
            ERPSystem.scanner.nextLine();

            switch (choice) {
                case 1 -> orderCreator.createOrder();
                case 2 -> orderViewer.viewAllOrders();
                case 3 -> orderViewer.viewOrderDetails();
                case 4 -> orderUpdater.updateOrderStatus();
                case 5 -> orderUpdater.cancelOrder();
                case 6 -> orderUpdater.processPayment();
                case 7 -> salesAnalytics.salesStatistics();
                case 8 -> salesAnalytics.topCustomers();
                case 9 -> { return; }
            }
        }
    }
}
