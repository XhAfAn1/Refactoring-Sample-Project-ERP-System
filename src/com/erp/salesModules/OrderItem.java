package com.erp.salesModules;

public class OrderItem {
    public int productId;
    public String productName;
    public int quantity;
    public double price;
    public double discount;

    public OrderItem(int pid, String pname, int qty, double pr) {
        productId = pid;
        productName = pname;
        quantity = qty;
        price = pr;
        discount = 0;
    }

    public double getTotal() {
        return (price * quantity) - discount;
    }
}
