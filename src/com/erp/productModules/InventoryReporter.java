package com.erp.productModules;

import java.util.List;
import java.util.Map;

public interface InventoryReporter {
    List<Product> getAllProducts();
    Map<Integer, Integer> getStockMap();
    List<Product> getLowStockProducts();
    Map<String, List<Product>> getProductsByCategory();
}