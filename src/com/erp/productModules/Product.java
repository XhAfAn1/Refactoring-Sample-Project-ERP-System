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

    // 1. Private Constructor: Can only be called by the Builder
    private Product(Builder builder) {
        this.product_id = builder.product_id;
        this.product_name = builder.product_name;
        this.product_price = builder.product_price;
        this.product_category = builder.product_category;
        this.product_description = builder.product_description;
        this.product_cost = builder.product_cost;
        this.product_supplier = builder.product_supplier;
        this.product_reorderLevel = builder.product_reorderLevel;
        this.product_barcode = builder.product_barcode;
        this.product_weight = builder.product_weight;
        this.product_dimensions = builder.product_dimensions;
    }

    // 2. Static Inner Builder Class
    public static class Builder {
        private int product_id;
        private String product_name;
        private double product_price;
        private String product_category;
        private String product_description;
        private double product_cost;
        private String product_supplier;
        private int product_reorderLevel;
        private String product_barcode;
        private double product_weight;
        private String product_dimensions;

        public Builder() {
            
        }

        public Builder withId(int id) {
            this.product_id = id;
            return this;
        }

        public Builder withName(String name) {
            this.product_name = name;
            return this;
        }

        public Builder withPrice(double price) {
            this.product_price = price;
            return this;
        }

        public Builder withCategory(String category) {
            this.product_category = category;
            return this;
        }

        public Builder withCost(double cost) {
            this.product_cost = cost;
            return this;
        }

        public Builder withReorderLevel(int level) {
            this.product_reorderLevel = level;
            return this;
        }

        public Builder withDescription(String description) {
            this.product_description = description;
            return this;
        }

        public Builder withSupplier(String supplier) {
            this.product_supplier = supplier;
            return this;
        }

        // 3. The Build Method
        public Product build() {
            return new Product(this);
        }
    }

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