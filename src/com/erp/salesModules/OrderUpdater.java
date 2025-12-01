package com.erp.salesModules;

import com.erp.coreModules.ERPSystem;
import com.erp.customerModules.Customer;
import java.util.ArrayList;

public class OrderUpdater {

    public void updateOrderStatus() {
        System.out.print("Enter Order ID: ");
        int id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        for (Order o :  (ArrayList<Order>)ERPSystem.allOrders) {
            if (o.id == id) {

                System.out.println("Current status: " + o.status);
                System.out.println("1. PENDING");
                System.out.println("2. CONFIRMED");
                System.out.println("3. SHIPPED");
                System.out.println("4. DELIVERED");
                System.out.println("5. CANCELLED");
                System.out.print("Select new status: ");

                int status = ERPSystem.scanner.nextInt();
                ERPSystem.scanner.nextLine();

                o.status = switch (status) {
                    case 1 -> "PENDING";
                    case 2 -> "CONFIRMED";
                    case 3 -> "SHIPPED";
                    case 4 -> "DELIVERED";
                    case 5 -> "CANCELLED";
                    default -> o.status;
                };

                System.out.println("Status updated to: " + o.status);
                return;
            }
        }
        System.out.println("Order not found!");
    }

    public void cancelOrder() {
        System.out.print("Enter Order ID: ");
        int id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        for (Order o : (ArrayList<Order>)ERPSystem.allOrders) {
            if (o.id == id) {

                if (o.status.equals("DELIVERED")) {
                    System.out.println("Cannot cancel delivered order!");
                    return;
                }

                o.status = "CANCELLED";
                ERPSystem.totalRevenue -= o.totalAmount;

                Customer c = findCustomer(o.customerId);
                if (c != null) c.customer_currentBalance -= o.totalAmount;

                System.out.println("Order cancelled!");
                return;
            }
        }
        System.out.println("Order not found!");
    }

    public void processPayment() {
        System.out.print("Enter Order ID: ");
        int id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        for (Order o : (ArrayList<Order>)ERPSystem.allOrders) {
            if (o.id == id) {
                System.out.println("Order Total: $" + o.totalAmount);
                System.out.print("Enter payment method: ");
                o.paymentMethod = ERPSystem.scanner.nextLine();

                Customer c = findCustomer(o.customerId);
                if (c != null) {
                    c.customer_currentBalance -= o.totalAmount;
                    System.out.println("Payment processed!");
                    System.out.println("New balance: $" + c.customer_currentBalance);
                }
                return;
            }
        }
        System.out.println("Order not found!");
    }

    private Customer findCustomer(int id) {
        for (Customer c : (ArrayList<Customer>)ERPSystem.allCustomers) {
            if (c.customer_id == id) return c;
        }
        return null;
    }
}
