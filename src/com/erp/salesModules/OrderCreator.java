package com.erp.salesModules;

import com.erp.coreModules.ERPSystem;
import com.erp.customerModules.Customer;
import com.erp.productModules.Product;
import java.util.ArrayList;

public class OrderCreator {

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
        order.id = ERPSystem.allOrders.size() + 1;
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

    private Customer findCustomer(int custId) {
    for (Customer c : (ArrayList<Customer>) ERPSystem.allCustomers) {
        if (c.customer_id == custId) return c;
    }
    return null;
}

    private void addItemsToOrder(Order order) {
        System.out.println("Add items to order (enter 0 to finish):");
        while (true) {
            System.out.print("Enter Product ID (0 to finish): ");
            int prodId = ERPSystem.scanner.nextInt();
            if (prodId == 0) break;

            Product product = findProduct(prodId);
            if (product == null) {
                System.out.println("Product not found!");
                continue;
            }

            System.out.print("Enter quantity: ");
            int qty = ERPSystem.scanner.nextInt();
            ERPSystem.scanner.nextLine();

            int stock = (Integer) ERPSystem.inventory.get(prodId);
            if (stock < qty) {
                System.out.println("Insufficient stock! Available: " + stock);
                continue;
            }

            OrderItem item = new OrderItem(product, qty);
            order.addItem(item);

            ERPSystem.inventory.put(prodId, stock - qty);
            System.out.println("Item added!");
        }
    }

    private Product findProduct(int id) {
        for (Product p : (ArrayList<Product>)ERPSystem.allProducts) {
            if (p.product_id == id) return p;
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
        customer.customer_currentBalance += order.totalAmount;
        ERPSystem.allOrders.add(order);
        ERPSystem.totalRevenue += order.totalAmount;

        System.out.println("\nOrder created successfully!");
        order.print();
    }
}
