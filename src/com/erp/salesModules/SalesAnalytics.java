package com.erp.salesModules;

import com.erp.coreModules.ERPSystem;
import com.erp.customerModules.Customer;
import java.util.HashMap;
import java.util.Map;

public class SalesAnalytics implements OrderObserver {

    // --- OBSERVER METHOD ---
    @Override
    public void onOrderPlaced(Order order) {
        ERPSystem.totalRevenue += order.totalAmount;
        System.out.println("[Observer] SalesAnalytics: Revenue updated.");
    }
    // -----------------------

    public void salesStatistics() {
        System.out.println("\n=== SALES STATISTICS ===");
        System.out.println("Total Orders: " + ERPSystem.allOrders.size());
        System.out.println("Total Revenue: $" + ERPSystem.totalRevenue);

        int pending = 0, confirmed = 0, shipped = 0, delivered = 0, cancelled = 0;

        for (Object obj : ERPSystem.allOrders) {
            Order o = (Order) obj;
            switch (o.status) {
                case PENDING -> pending++;
                case CONFIRMED -> confirmed++;
                case SHIPPED -> shipped++;
                case DELIVERED -> delivered++;
                case CANCELLED -> cancelled++;
            }
        }

        System.out.println("\nOrders by Status:");
        System.out.println("Pending: " + pending);
        System.out.println("Confirmed: " + confirmed);
        System.out.println("Shipped: " + shipped);
        System.out.println("Delivered: " + delivered);
        System.out.println("Cancelled: " + cancelled);

        if (!ERPSystem.allOrders.isEmpty()) {
            double avgOrderValue = ERPSystem.totalRevenue / ERPSystem.allOrders.size();
            System.out.println("\nAverage Order Value: $" + avgOrderValue);
        }
    }

    public void topCustomers() {
        Map<Integer, Double> customerOrders = new HashMap<>();

        for (Object obj : ERPSystem.allOrders) {
            Order o = (Order) obj;
            customerOrders.put(o.customerId,
                    customerOrders.getOrDefault(o.customerId, 0.0) + o.totalAmount);
        }

        System.out.println("\n=== TOP CUSTOMERS ===");
        for (Integer custId : customerOrders.keySet()) {
            double total = customerOrders.get(custId);
            for (Object obj : ERPSystem.allCustomers) {
                Customer c = (Customer) obj;
                if (c.customer_id == custId) {
                    System.out.println("\nCustomer: " + c.customer_name);
                    System.out.println("Total Spent: $" + total);
                }
            }
        }
    }
}