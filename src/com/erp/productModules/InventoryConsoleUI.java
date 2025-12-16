package com.erp.productModules;

import com.erp.coreModules.ERPSystem;
import java.util.*;

public class InventoryConsoleUI {
    private final InventoryService service; 

    public InventoryConsoleUI() {
        this.service = new InventoryService();
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== INVENTORY MANAGEMENT ===");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Update Stock");
            System.out.println("4. Check Stock Level");
            System.out.println("5. Update Product");
            System.out.println("6. Delete Product");
            System.out.println("7. Low Stock Alert");
            System.out.println("8. Product Categories");
            System.out.println("9. Back");
            System.out.print("Enter choice: ");

            int choice = ERPSystem.scanner.nextInt();
            ERPSystem.scanner.nextLine();

            if (choice == 1) addProductUI();
            else if (choice == 2) viewAllUI();
            else if (choice == 3) updateStockUI();
            else if (choice == 4) checkStockUI();
            else if (choice == 5) updateProductUI();
            else if (choice == 6) deleteProductUI();
            else if (choice == 7) lowStockAlertUI();
            else if (choice == 8) categoriesUI();
            else if (choice == 9) break;
            else System.out.println("Invalid choice!");
        }
    }

    private void addProductUI() {
        System.out.print("Enter ID: "); 
        int id = ERPSystem.scanner.nextInt(); 
        ERPSystem.scanner.nextLine();

        System.out.print("Enter Name: "); 
        String name = ERPSystem.scanner.nextLine();

        System.out.print("Enter Price: "); 
        double price = ERPSystem.scanner.nextDouble(); 
        ERPSystem.scanner.nextLine();

        System.out.print("Enter Category: "); 
        String category = ERPSystem.scanner.nextLine();

        System.out.print("Enter Cost: "); 
        double cost = ERPSystem.scanner.nextDouble(); 
        ERPSystem.scanner.nextLine();

        System.out.print("Enter Initial Stock: "); 
        int stock = ERPSystem.scanner.nextInt(); 
        ERPSystem.scanner.nextLine();

        System.out.print("Enter Reorder Level: "); 
        int reorderLevel = ERPSystem.scanner.nextInt(); 
        ERPSystem.scanner.nextLine();

        // --- REFACTORED: USING STATIC INNER BUILDER ---
        Product p = new Product.Builder()
                .withId(id)
                .withName(name)
                .withPrice(price)
                .withCategory(category)
                .withCost(cost)
                .withReorderLevel(reorderLevel)
                .build();

        service.addProduct(p, stock);
        System.out.println("Product added successfully!");
    }

    private void updateProductUI() {
        System.out.print("Enter ID: "); int id = ERPSystem.scanner.nextInt();
        System.out.print("Enter New Price: "); double price = ERPSystem.scanner.nextDouble();
        System.out.print("Enter New Cost: "); double cost = ERPSystem.scanner.nextDouble();
        
        if(service.updateProduct(id, price, cost)) System.out.println("Product updated!");
        else System.out.println("Product not found!");
    }

    private void deleteProductUI() {
        System.out.print("Enter ID: "); int id = ERPSystem.scanner.nextInt();
        if (service.deleteProduct(id)) System.out.println("Deleted!");
        else System.out.println("Product not found.");
    }

    private void updateStockUI() {
        System.out.print("Enter ID: "); int id = ERPSystem.scanner.nextInt();
        System.out.print("1. Add  2. Remove: "); int type = ERPSystem.scanner.nextInt();
        System.out.print("Quantity: "); int qty = ERPSystem.scanner.nextInt();
        
        boolean success = service.updateStock(id, qty, type == 1);
        if(success) System.out.println("Stock updated.");
        else System.out.println("Action failed (Invalid ID or Insufficient Stock).");
    }

    private void checkStockUI() {
        System.out.print("Enter ID: "); int id = ERPSystem.scanner.nextInt();
        int stock = service.checkStock(id);
        if(stock != -1) {
            System.out.println("Current Stock: " + stock);
        } else {
            System.out.println("Product not found!");
        }
    }

    private void viewAllUI() {
        List<Product> products = service.getAllProducts();
        Map<Integer, Integer> stock = service.getStockMap();
        
        System.out.println("\n=== ALL PRODUCTS ===");
        for (Product p : products) {
            System.out.println("\n--- Product ---");
            p.print();
            System.out.println("Stock: " + stock.get(p.product_id));
            System.out.println("Profit Margin: " + p.calculateProfitMargin() + "%");
        }
    }

    private void lowStockAlertUI() {
        List<Product> lows = service.getLowStockProducts();
        System.out.println("\n=== LOW STOCK ===");
        if (lows.isEmpty()) System.out.println("No low stock items.");
        for (Product p : lows) {
            System.out.println("Low stock: " + p.product_name + " (Reorder Level: " + p.product_reorderLevel + ")");
        }
    }

    private void categoriesUI() {
        Map<String, List<Product>> cats = service.getProductsByCategory();
        System.out.println("\n=== CATEGORIES ===");
        Iterator<String> it = cats.keySet().iterator();
        while(it.hasNext()) {
            String cat = it.next();
            System.out.println("Category: " + cat + " | Total Products: " + cats.get(cat).size());
        }
    }
}