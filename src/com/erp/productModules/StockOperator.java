package com.erp.productModules;

public interface StockOperator {
    // returns true if successful, false if insufficient stock
    boolean updateStock(int id, int quantity, boolean isAdding); 
    int checkStock(int id);
}