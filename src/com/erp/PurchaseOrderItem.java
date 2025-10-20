package com.erp;

public class PurchaseOrderItem {
    public int productId;
    public String productName;
    public int quantity;
    public double cost;

    public PurchaseOrderItem(int pid, String name, int qty, double c) {
        productId = pid;
        productName = name;
        quantity = qty;
        cost = c;
    }

    public double getTotal() {
        return cost * quantity;
    }
}
