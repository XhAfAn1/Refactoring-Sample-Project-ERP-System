package com.erp.productModules;

public class Product {
    public int productId;
    public String productName;
    public double productPrice;
    public String productCategory;
    public String productDescription;
    public double productCost;
    public String productSupplier;
    public int productReorderLevel;
    public String productBarcode;
    public double productWeight;
    public String productDimensions;

    
    private Product(Builder builder) {
        this.productId = builder.product_id;
        this.productName = builder.product_name;
        this.productPrice = builder.product_price;
        this.productCategory = builder.product_category;
        this.productDescription = builder.product_description;
        this.productCost = builder.product_cost;
        this.productSupplier = builder.product_supplier;
        this.productReorderLevel = builder.product_reorderLevel;
        this.productBarcode = builder.product_barcode;
        this.productWeight = builder.product_weight;
        this.productDimensions = builder.product_dimensions;
    }

   
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

        
        public Product build() {
            return new Product(this);
        }
    }

    public void print() {
        System.out.println("Product ID: " + productId);
        System.out.println("Name: " + productName);
        System.out.println("Price: $" + productPrice);
        System.out.println("Category: " + productCategory);
        System.out.println("Cost: $" + productCost);
        System.out.println("Supplier: " + productSupplier);
    }

    public double calculateProfit() {
        return productPrice - productCost;
    }

    public double calculateProfitMargin() {
        return ((productPrice - productCost) / productPrice) * 100;
    }
}