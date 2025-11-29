package com.erp.coreModules.commands;

import com.erp.coreModules.ERPSystem;
import com.erp.coreModules.SettingCommand;

public class DatabaseStatsCommand implements SettingCommand {
    public String getName() { return "Database Statistics"; }

    public void execute() {
        System.out.println("\n=== DATABASE STATISTICS ===");
        System.out.println("Employees Table: " + ERPSystem.allEmployees.size());
        System.out.println("Products Table: " + ERPSystem.allProducts.size());
        System.out.println("Orders Table: " + ERPSystem.allOrders.size());
        System.out.println("Customers Table: " + ERPSystem.allCustomers.size());
        System.out.println("Suppliers Table: " + ERPSystem.allSuppliers.size());
        System.out.println("Inventory Records: " + ERPSystem.inventory.size() + " items");

        int totalRecords = ERPSystem.allEmployees.size() +
                          ERPSystem.allProducts.size() +
                          ERPSystem.allOrders.size() +
                          ERPSystem.allCustomers.size() +
                          ERPSystem.allSuppliers.size();
        System.out.println("\nTotal Records: " + totalRecords);
    }
}