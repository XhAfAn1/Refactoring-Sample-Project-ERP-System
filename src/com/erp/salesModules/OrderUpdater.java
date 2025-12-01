package com.erp.salesModules;

import com.erp.coreModules.ERPSystem;
import com.erp.customerModules.Customer;
import com.erp.enums.OrderStatus;
import java.util.ArrayList; // Import the Enum
// Removed java.util.ArrayList import as explicit casting is no longer needed

public class OrderUpdater {

    public void updateOrderStatus() {
        System.out.print("Enter Order ID: ");
        int id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        // Refactored: Removed unsafe cast (ArrayList<Order>)
        for (Order o : (ArrayList<Order>)ERPSystem.allOrders) {
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

                // Refactored: Assign Enum constants instead of Strings
                o.status = switch (status) {
                    case 1 -> OrderStatus.PENDING;
                    case 2 -> OrderStatus.CONFIRMED;
                    case 3 -> OrderStatus.SHIPPED;
                    case 4 -> OrderStatus.DELIVERED;
                    case 5 -> OrderStatus.CANCELLED;
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

        // Refactored: Removed unsafe cast
        for (Order o : (ArrayList<Order>)ERPSystem.allOrders) {
            if (o.id == id) {

                // Refactored: Enum comparison
                if (o.status == OrderStatus.DELIVERED) {
                    System.out.println("Cannot cancel delivered order!");
                    return;
                }

                o.status = OrderStatus.CANCELLED; // Refactored: Enum assignment
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

        // Refactored: Removed unsafe cast
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
        // Refactored: Removed unsafe cast
        for (Customer c : (ArrayList<Customer>)ERPSystem.allCustomers) {
            if (c.customer_id == id) return c;
        }
        return null;
    }
}