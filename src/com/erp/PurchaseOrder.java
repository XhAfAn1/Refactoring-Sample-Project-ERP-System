package com.erp;

import java.util.*;

public class PurchaseOrder {
    public int id;
    public int supplierId;
    public Date orderDate;
    public Date expectedDelivery;
    public String status;
    public ArrayList items;
    public double totalAmount;
    public String paymentStatus;
    public Date deliveredDate;

    public PurchaseOrder() {
        orderDate = new Date();
        status = "PENDING";
        items = new ArrayList();
        paymentStatus = "UNPAID";
    }

    public void addItem(PurchaseOrderItem item) {
        items.add(item);
        calculateTotal();
    }

    public void calculateTotal() {
        double total = 0;
        for(int i = 0; i < items.size(); i++) {
            PurchaseOrderItem item = (PurchaseOrderItem)items.get(i);
            total += item.cost * item.quantity;
        }
        totalAmount = total;
    }

    public void print() {
        System.out.println("PO ID: " + id);
        System.out.println("Supplier ID: " + supplierId);
        System.out.println("Order Date: " + orderDate);
        System.out.println("Status: " + status);
        System.out.println("Total Amount: $" + totalAmount);
        System.out.println("Payment Status: " + paymentStatus);
    }
}
