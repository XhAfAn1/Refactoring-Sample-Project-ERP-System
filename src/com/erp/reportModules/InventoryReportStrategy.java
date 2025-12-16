package com.erp.reportModules;

import com.erp.coreModules.ERPSystem;
import com.erp.productModules.Product;

public class InventoryReportStrategy implements ReportStrategy {

    @Override
    public String getName() {
        return "Inventory Report";
    }

    @Override
    public void generate() {
        System.out.println("\n=== INVENTORY REPORT ===");
        System.out.println("Total Products: " + ERPSystem.allProducts.size());

        double totalValue = 0;
        int totalStock = 0;

        for (Object obj : ERPSystem.allProducts) {
            Product p = (Product) obj;
            int stock = (Integer) ERPSystem.inventory.get(p.product_id);
            totalStock += stock;
            totalValue += p.product_cost * stock;
        }

        System.out.println("Total Stock Units: " + totalStock);
        System.out.println("Total Inventory Value: $" + totalValue);
    }
}
