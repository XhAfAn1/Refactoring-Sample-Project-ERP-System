package com.erp.productModules;

public interface ProductAdmin {
    void addProduct(Product p, int initialStock);
    boolean updateProduct(int id, double newPrice, double newCost);
    boolean deleteProduct(int id);
}