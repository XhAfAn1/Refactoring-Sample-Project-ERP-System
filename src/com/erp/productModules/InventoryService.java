package com.erp.productModules;

import com.erp.coreModules.ERPSystem;
import com.erp.salesModules.Order;
import com.erp.salesModules.OrderItem;
import com.erp.salesModules.OrderObserver;
import java.util.*;

public class InventoryService implements OrderObserver {

    // --- OBSERVER METHOD ---
    @Override
    public void onOrderPlaced(Order order) {
        System.out.println("[Observer] InventoryService: Processing stock deduction...");
        
        for (Object itemObj : order.items) {
            OrderItem item = (OrderItem) itemObj;
            
            // Using the getters we added to OrderItem
            int pid = item.getProductId();
            int qty = item.getQuantity();
            String pName = item.getProductName();

            // Deduct stock
            updateStock(pid, qty, false);
            System.out.println("   -> Deducted " + qty + " of " + pName);
        }
    }
    // -----------------------

    public void addProduct(Product p, int initialStock) {
        ERPSystem.allProducts.add(p);
        ERPSystem.inventory.put(p.productId, initialStock);
    }

    public boolean updateProduct(int id, double newPrice, double newCost) {
        for (Object obj : ERPSystem.allProducts) {
            Product p = (Product) obj;
            if (p.productId == id) {
                p.productPrice = newPrice;
                p.productCost = newCost;
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(int id) {
        Iterator it = ERPSystem.allProducts.iterator();
        while (it.hasNext()) {
            Product p = (Product) it.next();
            if (p.productId == id) {
                it.remove();
                ERPSystem.inventory.remove(id);
                return true;
            }
        }
        return false;
    }

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

    public int checkStock(int id) {
        if (ERPSystem.inventory.containsKey(id)) {
            return (Integer) ERPSystem.inventory.get(id);
        }
        return -1;
    }

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        for (Object obj : ERPSystem.allProducts) {
            list.add((Product) obj);
        }
        return list;
    }
    
    public Map<Integer, Integer> getStockMap() {
         return new HashMap(ERPSystem.inventory);
    }

    public List<Product> getLowStockProducts() {
        List<Product> lowStock = new ArrayList<>();
        for (Object obj : ERPSystem.allProducts) {
            Product p = (Product) obj;
            int stock = (Integer) ERPSystem.inventory.get(p.productId);
            if (stock <= p.productReorderLevel) {
                lowStock.add(p);
            }
        }
        return lowStock;
    }

    public Map<String, List<Product>> getProductsByCategory() {
        Map<String, List<Product>> map = new HashMap<>();
        for (Object obj : ERPSystem.allProducts) {
            Product p = (Product) obj;
            if (!map.containsKey(p.productCategory)) {
                map.put(p.productCategory, new ArrayList<>());
            }
            map.get(p.productCategory).add(p);
        }
        return map;
    }
}