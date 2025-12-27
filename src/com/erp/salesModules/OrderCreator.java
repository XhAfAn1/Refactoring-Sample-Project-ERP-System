package com.erp.salesModules;

import com.erp.coreModules.ERPSystem;
import com.erp.customerModules.Customer;
import com.erp.productModules.Product;
import java.util.ArrayList;
import java.util.List;

public class OrderCreator {

   
    private List<OrderObserver> observers = new ArrayList<>();

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(Order order) {
        for (OrderObserver observer : observers) {
            observer.onOrderPlaced(order);
        }
    }
    

    public void createOrder() {
        System.out.print("Enter Customer ID: ");
        int custId = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        Customer customer = findCustomer(custId);
        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        Order order = new Order();
        order.orderId = ERPSystem.allOrders.size() + 1;
        order.customerId = custId;

        addItemsToOrder(order);

        if (order.items.isEmpty()) {
            System.out.println("No items added. Order cancelled.");
            return;
        }

        applyCharges(order);
        order.calculateTotal();

        if (!customer.canPlaceOrder(order.totalAmount)) {
            System.out.println("Customer credit limit exceeded!");
            System.out.println("Available credit: $" + customer.getAvailableCredit());
            System.out.print("Proceed anyway? (yes/no): ");
            String proceed = ERPSystem.scanner.nextLine();
            if (!proceed.equalsIgnoreCase("yes")) return;
        }

        finalizeOrder(order, customer);
    }

    private void addItemsToOrder(Order order) {
        while (true) {
            System.out.print("Enter Product ID (0 to finish): ");
            int prodId = ERPSystem.scanner.nextInt();
            if (prodId == 0) break;

            Product product = findProduct(prodId);
            if (product == null) {
                System.out.println("Product not found!");
                continue;
            }

            System.out.print("Enter Quantity: ");
            int qty = ERPSystem.scanner.nextInt();
            ERPSystem.scanner.nextLine();


            int stock = (Integer) ERPSystem.inventory.get(prodId);
            if (stock < qty) {
                System.out.println("Insufficient stock! Available: " + stock);
                continue;
            }

            OrderItem item = new OrderItem(product, qty);
            order.addItem(item);

            System.out.println("Item added!");
        }
    }

    private Product findProduct(int id) {
        for (Object obj : ERPSystem.allProducts) {
            Product p = (Product) obj;
            if (p.productId == id) return p;
        }
        return null;
    }
    
    private Customer findCustomer(int id) {
        for (Object obj : ERPSystem.allCustomers) {
            Customer c = (Customer) obj;
            if (c.customerId == id) return c;
        }
        return null;
    }

    private void applyCharges(Order order) {
        System.out.print("Enter discount amount: ");
        order.discount = ERPSystem.scanner.nextDouble();
        System.out.print("Enter tax amount: ");
        order.tax = ERPSystem.scanner.nextDouble();
        System.out.print("Enter shipping cost: ");
        order.shippingCost = ERPSystem.scanner.nextDouble();
        ERPSystem.scanner.nextLine();
    }

    private void finalizeOrder(Order order, Customer customer) {
        customer.customerCurrentBalance += order.totalAmount;
        ERPSystem.allOrders.add(order);
        
    
        notifyObservers(order);
   
        System.out.println("Order created successfully!");
        System.out.println("Order Total: $" + order.totalAmount);
    }
}