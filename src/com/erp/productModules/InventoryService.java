package com.erp.productModules;

import com.erp.coreModules.ERPSystem;
import java.util.*;

public class InventoryService implements ProductAdmin, StockOperator, InventoryReporter {

    // --- ProductAdmin Implementation ---
    @Override
    public void addProduct(Product p, int initialStock) {
        ERPSystem.allProducts.add(p);
        ERPSystem.inventory.put(p.id, initialStock);
    }

    @Override
    public boolean updateProduct(int id, double newPrice, double newCost) {
        for (Object obj : ERPSystem.allProducts) {
            Product p = (Product) obj;
            if (p.id == id) {
                p.price = newPrice;
                p.cost = newCost;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int id) {
        Iterator it = ERPSystem.allProducts.iterator();
        while (it.hasNext()) {
            Product p = (Product) it.next();
            if (p.id == id) {
                it.remove();
                ERPSystem.inventory.remove(id);
                return true;
            }
        }
        return false;
    }

    // --- StockOperator Implementation ---
    @Override
    public boolean updateStock(int id, int quantity, boolean isAdding) {
        if (!ERPSystem.inventory.containsKey(id)) return false;
        
        int current = (Integer) ERPSystem.inventory.get(id);
        if (isAdding) {
            ERPSystem.inventory.put(id, current + quantity);
            return true;
        } else {
            if (current >= quantity) {
                ERPSystem.inventory.put(id, current - quantity);
                return true;
            }
        }
        return false;
    }

    @Override
    public int checkStock(int id) {
        if (ERPSystem.inventory.containsKey(id)) {
            return (Integer) ERPSystem.inventory.get(id);
        }
        return -1; // Not found code
    }

    // --- InventoryReporter Implementation ---
    @Override
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        for (Object obj : ERPSystem.allProducts) {
            list.add((Product) obj);
        }
        return list;
    }
    
    @Override
    public Map<Integer, Integer> getStockMap() {
         return new HashMap(ERPSystem.inventory);
    }

    @Override
    public List<Product> getLowStockProducts() {
        List<Product> lowStock = new ArrayList<>();
        for (Object obj : ERPSystem.allProducts) {
            Product p = (Product) obj;
            int stock = (Integer) ERPSystem.inventory.get(p.id);
            if (stock <= p.reorderLevel) {
                lowStock.add(p);
            }
        }
        return lowStock;
    }

    @Override
    public Map<String, List<Product>> getProductsByCategory() {
        Map<String, List<Product>> map = new HashMap<>();
        for (Object obj : ERPSystem.allProducts) {
            Product p = (Product) obj;
            if (!map.containsKey(p.category)) {
                map.put(p.category, new ArrayList<>());
            }
            map.get(p.category).add(p);
        }
        return map;
    }
}