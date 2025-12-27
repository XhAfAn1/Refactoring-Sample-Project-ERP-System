package com.erp.salesModules;

import com.erp.coreModules.ERPSystem;
import java.util.ArrayList;

public class OrderViewer {

    public void viewAllOrders() {
    System.out.println("\n=== ALL ORDERS ===");
    for (int i = 0; i < ERPSystem.allOrders.size(); i++) {
        Order o = (Order) ERPSystem.allOrders.get(i); 
        System.out.println("\n--- Order " + (i + 1) + " ---");
        o.print();
    }
}


    public void viewOrderDetails() {
        System.out.print("Enter Order ID: ");
        int id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        for (Order o : (ArrayList<Order>)ERPSystem.allOrders) {
            if (o.orderId == id) {
                o.printDetailed();
                return;
            }
        }
        System.out.println("Order not found!");
    }
}
