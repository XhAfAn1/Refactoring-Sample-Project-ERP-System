package com.erp.salesModules;

import com.erp.productModules.Product;

public class OrderItem {
    public int productId;
    public String productName;
    public int quantity;
    public double price;
    public double discount;

    public OrderItem(Product product, int qty) {
        this.productId = product.product_id;
        this.productName = product.product_name;
        this.quantity = qty;
        this.price = product.product_price;
        this.discount = 0;
    }

    public double getTotal() {
        return (price * quantity) - discount;
    }
    
    public void printDetails() {
        System.out.println("Product ID: " + productId);
        System.out.println("Product Name: " + productName);
        System.out.println("Quantity: " + quantity);
        System.out.println("Price: $" + price);
        System.out.println("Subtotal: $" + getTotal());
        System.out.println("---");
    }
}