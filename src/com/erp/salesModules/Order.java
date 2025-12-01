package com.erp.salesModules;

import com.erp.enums.OrderStatus;
import java.util.*;

public class Order {
    public int id;
    public int customerId;
    public Date orderDate;
    public OrderStatus status; 
    public ArrayList items;
    public double totalAmount;
    public double discount;
    public double tax;
    public double shippingCost;
    public String paymentMethod;
    public String shippingAddress;
    public Date deliveryDate;

    public Order() {
        orderDate = new Date();
        status = OrderStatus.PENDING; 
        items = new ArrayList();
        discount = 0;
        tax = 0;
        shippingCost = 0;
    }

    public void addItem(OrderItem item) {
        items.add(item);
        calculateTotal();
    }

    public void calculateTotal() {
        double subtotal = 0;
        for(int i = 0; i < items.size(); i++) {
            OrderItem item = (OrderItem) items.get(i);
            subtotal += item.getTotal();
        }
        totalAmount = subtotal - discount + tax + shippingCost;
    }

    public void print() {
        System.out.println("Order ID: " + id);
        System.out.println("Customer ID: " + customerId);
        System.out.println("Order Date: " + orderDate);
        System.out.println("Status: " + status);
        System.out.println("Total Items: " + items.size());
        System.out.println("Total Amount: $" + totalAmount);
    }

    public void printDetailed() {
        print();
        System.out.println("\n=== Order Items ===");
        for(int i = 0; i < items.size(); i++) {
            OrderItem item = (OrderItem) items.get(i);
            item.printDetails();   
        }
        System.out.println("Discount: $" + discount);
        System.out.println("Tax: $" + tax);
        System.out.println("Shipping: $" + shippingCost);
        System.out.println("TOTAL: $" + totalAmount);
    }
}