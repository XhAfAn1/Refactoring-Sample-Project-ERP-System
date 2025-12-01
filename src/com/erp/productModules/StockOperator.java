package com.erp.productModules;

public interface StockOperator {
    boolean updateStock(int id, int quantity, boolean isAdding); 
    int checkStock(int id);
}