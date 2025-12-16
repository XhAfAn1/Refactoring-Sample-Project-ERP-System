package com.erp.reportModules;

import com.erp.coreModules.ERPSystem;
import com.erp.salesModules.Order;
import com.erp.salesModules.OrderItem;

public class SalesReportStrategy implements ReportStrategy {

    @Override
    public String getName() {
        return "Sales Report";
    }

    @Override
    public void generate() {
        System.out.println("\n=== SALES REPORT ===");
        System.out.println("Total Orders: " + ERPSystem.allOrders.size());
        System.out.println("Total Revenue: $" + ERPSystem.totalRevenue);

        double totalQuantity = 0;
        for (Object obj : ERPSystem.allOrders) {
            Order o = (Order) obj;
            for (Object itemObj : o.items) {
                OrderItem item = (OrderItem) itemObj;
                totalQuantity += item.quantity;
            }
        }

        System.out.println("Total Items Sold: " + (int) totalQuantity);

        if (!ERPSystem.allOrders.isEmpty()) {
            System.out.println("Average Order Value: $" +
                    (ERPSystem.totalRevenue / ERPSystem.allOrders.size()));
        }

        int pending = 0, confirmed = 0, shipped = 0, delivered = 0, cancelled = 0;

        for (Object obj : ERPSystem.allOrders) {
            Order o = (Order) obj;

            switch (o.status) {
                case PENDING:
                    pending++;
                    break;
                case CONFIRMED:
                    confirmed++;
                    break;
                case SHIPPED:
                    shipped++;
                    break;
                case DELIVERED:
                    delivered++;
                    break;
                case CANCELLED:
                    cancelled++;
                    break;
            }
        }

        System.out.println("\nOrder Status Breakdown:");
        System.out.println("Pending: " + pending);
        System.out.println("Confirmed: " + confirmed);
        System.out.println("Shipped: " + shipped);
        System.out.println("Delivered: " + delivered);
        System.out.println("Cancelled: " + cancelled);
    }
}
