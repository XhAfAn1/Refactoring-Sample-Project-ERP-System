package com.erp.salesModules;

import com.erp.coreModules.ERPSystem;
import com.erp.customerModules.Customer;
import com.erp.productModules.Product;
import java.util.*;

public class SalesManager {
    public void showMenu() {
        while(true) {
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

            if(choice == 1) {
                createOrder();
            } else if(choice == 2) {
                viewAllOrders();
            } else if(choice == 3) {
                viewOrderDetails();
            } else if(choice == 4) {
                updateOrderStatus();
            } else if(choice == 5) {
                cancelOrder();
            } else if(choice == 6) {
                processPayment();
            } else if(choice == 7) {
                salesStatistics();
            } else if(choice == 8) {
                topCustomers();
            } else if(choice == 9) {
                break;
            }
        }
    }

    public void createOrder() {
        System.out.print("Enter Customer ID: ");
        int custId = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        boolean custFound = false;
        Customer customer = null;
        for(int i = 0; i < ERPSystem.allCustomers.size(); i++) {
            Customer c = (Customer)ERPSystem.allCustomers.get(i);
            if(c.customer_id == custId) {
                custFound = true;
                customer = c;
                break;
            }
        }

        if(!custFound) {
            System.out.println("Customer not found!");
            return;
        }

        Order order = new Order();
        order.id = ERPSystem.allOrders.size() + 1;
        order.customerId = custId;

        System.out.println("Add items to order (enter 0 to finish):");
        while(true) {
            System.out.print("Enter Product ID (0 to finish): ");
            int prodId = ERPSystem.scanner.nextInt();
            if(prodId == 0) break;

            Product product = null;
            for(int i = 0; i < ERPSystem.allProducts.size(); i++) {
                Product p = (Product)ERPSystem.allProducts.get(i);
                if(p.product_id == prodId) {
                    product = p;
                    break;
                }
            }

            if(product == null) {
                System.out.println("Product not found!");
                continue;
            }

            System.out.print("Enter quantity: ");
            int qty = ERPSystem.scanner.nextInt();
            ERPSystem.scanner.nextLine();

            int stock = (Integer)ERPSystem.inventory.get(prodId);
            if(stock < qty) {
                System.out.println("Insufficient stock! Available: " + stock);
                continue;
            }

            OrderItem item = new OrderItem(product, qty);
            
            order.addItem(item);

            ERPSystem.inventory.put(prodId, stock - qty);
            System.out.println("Item added!");
        }

        if(order.items.size() == 0) {
            System.out.println("No items added. Order cancelled.");
            return;
        }

        System.out.print("Enter discount amount: ");
        order.discount = ERPSystem.scanner.nextDouble();
        System.out.print("Enter tax amount: ");
        order.tax = ERPSystem.scanner.nextDouble();
        System.out.print("Enter shipping cost: ");
        order.shippingCost = ERPSystem.scanner.nextDouble();
        ERPSystem.scanner.nextLine();

        order.calculateTotal();

        if(!customer.canPlaceOrder(order.totalAmount)) {
            System.out.println("Customer credit limit exceeded!");
            System.out.println("Available credit: $" + customer.getAvailableCredit());
            System.out.print("Proceed anyway? (yes/no): ");
            String proceed = ERPSystem.scanner.nextLine();
            if(!proceed.equalsIgnoreCase("yes")) {
                return;
            }
        }

        customer.customer_currentBalance += order.totalAmount;
        ERPSystem.allOrders.add(order);
        ERPSystem.totalRevenue += order.totalAmount;

        System.out.println("\nOrder created successfully!");
        order.print();
    }

    public void viewAllOrders() {
        System.out.println("\n=== ALL ORDERS ===");
        for(int i = 0; i < ERPSystem.allOrders.size(); i++) {
            Order o = (Order)ERPSystem.allOrders.get(i);
            System.out.println("\n--- Order " + (i+1) + " ---");
            o.print();
        }
    }

    public void viewOrderDetails() {
        System.out.print("Enter Order ID: ");
        int id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        for(int i = 0; i < ERPSystem.allOrders.size(); i++) {
            Order o = (Order)ERPSystem.allOrders.get(i);
            if(o.id == id) {
                o.printDetailed();
                return;
            }
        }
        System.out.println("Order not found!");
    }

    public void updateOrderStatus() {
        System.out.print("Enter Order ID: ");
        int id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        for(int i = 0; i < ERPSystem.allOrders.size(); i++) {
            Order o = (Order)ERPSystem.allOrders.get(i);
            if(o.id == id) {
                System.out.println("Current status: " + o.status);
                System.out.println("1. PENDING");
                System.out.println("2. CONFIRMED");
                System.out.println("3. SHIPPED");
                System.out.println("4. DELIVERED");
                System.out.println("5. CANCELLED");
                System.out.print("Select new status: ");
                int status = ERPSystem.scanner.nextInt();
                ERPSystem.scanner.nextLine();

                if(status == 1) o.status = "PENDING";
                else if(status == 2) o.status = "CONFIRMED";
                else if(status == 3) o.status = "SHIPPED";
                else if(status == 4) o.status = "DELIVERED";
                else if(status == 5) o.status = "CANCELLED";

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

        for(int i = 0; i < ERPSystem.allOrders.size(); i++) {
            Order o = (Order)ERPSystem.allOrders.get(i);
            if(o.id == id) {
                if(o.status.equals("DELIVERED")) {
                    System.out.println("Cannot cancel delivered order!");
                    return;
                }
                o.status = "CANCELLED";
                ERPSystem.totalRevenue -= o.totalAmount;

                for(int j = 0; j < ERPSystem.allCustomers.size(); j++) {
                    Customer c = (Customer)ERPSystem.allCustomers.get(j);
                    if(c.customer_id == o.customerId) {
                        c.customer_currentBalance -= o.totalAmount;
                        break;
                    }
                }

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

        for(int i = 0; i < ERPSystem.allOrders.size(); i++) {
            Order o = (Order)ERPSystem.allOrders.get(i);
            if(o.id == id) {
                System.out.println("Order Total: $" + o.totalAmount);
                System.out.print("Enter payment method: ");
                o.paymentMethod = ERPSystem.scanner.nextLine();

                for(int j = 0; j < ERPSystem.allCustomers.size(); j++) {
                    Customer c = (Customer)ERPSystem.allCustomers.get(j);
                    if(c.customer_id == o.customerId) {
                        c.customer_currentBalance -= o.totalAmount;
                        System.out.println("Payment processed!");
                        System.out.println("New balance: $" + c.customer_currentBalance);
                        return;
                    }
                }
            }
        }
        System.out.println("Order not found!");
    }

    public void salesStatistics() {
        System.out.println("\n=== SALES STATISTICS ===");
        System.out.println("Total Orders: " + ERPSystem.allOrders.size());
        System.out.println("Total Revenue: $" + ERPSystem.totalRevenue);

        int pending = 0, confirmed = 0, shipped = 0, delivered = 0, cancelled = 0;
        for(int i = 0; i < ERPSystem.allOrders.size(); i++) {
            Order o = (Order)ERPSystem.allOrders.get(i);
            if(o.status.equals("PENDING")) pending++;
            else if(o.status.equals("CONFIRMED")) confirmed++;
            else if(o.status.equals("SHIPPED")) shipped++;
            else if(o.status.equals("DELIVERED")) delivered++;
            else if(o.status.equals("CANCELLED")) cancelled++;
        }

        System.out.println("\nOrders by Status:");
        System.out.println("Pending: " + pending);
        System.out.println("Confirmed: " + confirmed);
        System.out.println("Shipped: " + shipped);
        System.out.println("Delivered: " + delivered);
        System.out.println("Cancelled: " + cancelled);

        if(ERPSystem.allOrders.size() > 0) {
            double avgOrderValue = ERPSystem.totalRevenue / ERPSystem.allOrders.size();
            System.out.println("\nAverage Order Value: $" + avgOrderValue);
        }
    }

    public void topCustomers() {
        HashMap customerOrders = new HashMap();
        for(int i = 0; i < ERPSystem.allOrders.size(); i++) {
            Order o = (Order)ERPSystem.allOrders.get(i);
            if(customerOrders.containsKey(o.customerId)) {
                double total = (Double)customerOrders.get(o.customerId);
                customerOrders.put(o.customerId, total + o.totalAmount);
            } else {
                customerOrders.put(o.customerId, o.totalAmount);
            }
        }

        System.out.println("\n=== TOP CUSTOMERS ===");
        Iterator it = customerOrders.keySet().iterator();
        while(it.hasNext()) {
            int custId = (Integer)it.next();
            double total = (Double)customerOrders.get(custId);

            for(int i = 0; i < ERPSystem.allCustomers.size(); i++) {
                Customer c = (Customer)ERPSystem.allCustomers.get(i);
                if(c.customer_id == custId) {
                    System.out.println("\nCustomer: " + c.customer_name);
                    System.out.println("Total Purchases: $" + total);
                    break;
                }
            }
        }
    }
}