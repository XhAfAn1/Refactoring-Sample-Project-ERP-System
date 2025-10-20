package com.erp;

public class Product {
    public int id;
    public String name;
    public double price;
    public String category;
    public String description;
    public double cost;
    public String supplier;
    public int reorderLevel;
    public String barcode;
    public double weight;
    public String dimensions;

    public void print() {
        System.out.println("Product ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Price: $" + price);
        System.out.println("Category: " + category);
        System.out.println("Cost: $" + cost);
        System.out.println("Supplier: " + supplier);
    }

    public double calculateProfit() {
        return price - cost;
    }

    public double calculateProfitMargin() {
        return ((price - cost) / price) * 100;
    }
}
