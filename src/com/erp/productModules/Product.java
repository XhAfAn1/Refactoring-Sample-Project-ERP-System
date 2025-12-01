package com.erp.productModules;

public class Product {
    public int product_id;
    public String product_name;
    public double product_price;
    public String product_category;
    public String product_description;
    public double product_cost;
    public String product_supplier;
    public int product_reorderLevel;
    public String product_barcode;
    public double product_weight;
    public String product_dimensions;

    public void print() {
        System.out.println("Product ID: " + product_id);
        System.out.println("Name: " + product_name);
        System.out.println("Price: $" + product_price);
        System.out.println("Category: " + product_category);
        System.out.println("Cost: $" + product_cost);
        System.out.println("Supplier: " + product_supplier);
    }

    public double calculateProfit() {
        return product_price - product_cost;
    }

    public double calculateProfitMargin() {
        return ((product_price - product_cost) / product_price) * 100;
    }
}
