package com.erp.salesModules;

import com.erp.coreModules.ERPSystem;
import com.erp.productModules.InventoryService;
import java.util.HashMap;
import java.util.Map;

public class SalesManager {

    private final OrderCreator orderCreator = new OrderCreator();
    private final OrderViewer orderViewer = new OrderViewer();
    private final OrderUpdater orderUpdater = new OrderUpdater();
    private final SalesAnalytics salesAnalytics = new SalesAnalytics();

    private final Map<Integer, Runnable> menuActions = new HashMap<>();

    public SalesManager() {
       
        InventoryService inventoryObserver = new InventoryService();
        
      
        orderCreator.addObserver(inventoryObserver);
        orderCreator.addObserver(salesAnalytics);
      

        menuActions.put(1, orderCreator::createOrder);
        menuActions.put(2, orderViewer::viewAllOrders);
        menuActions.put(3, orderViewer::viewOrderDetails);
        menuActions.put(4, orderUpdater::updateOrderStatus);
        menuActions.put(5, orderUpdater::cancelOrder);
        menuActions.put(6, orderUpdater::processPayment);
        menuActions.put(7, salesAnalytics::salesStatistics);
        menuActions.put(8, salesAnalytics::topCustomers);
    }

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

            if (choice == 9) {
                return;
            }

            Runnable action = menuActions.get(choice);
            if (action != null) {
                action.run();
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }
}