package com.erp;

import java.util.*;

public class InventoryManager {
    public void showMenu() {
        while(true) {
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

            if(choice == 1) {
                addProduct();
            } else if(choice == 2) {
                viewAll();
            } else if(choice == 3) {
                updateStock();
            } else if(choice == 4) {
                checkStock();
            } else if(choice == 5) {
                updateProduct();
            } else if(choice == 6) {
                deleteProduct();
            } else if(choice == 7) {
                lowStockAlert();
            } else if(choice == 8) {
                productCategories();
            } else if(choice == 9) {
                break;
            }
        }
    }

    public void addProduct() {
        Product p = new Product();
        System.out.print("Enter Product ID: ");
        p.id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();
        System.out.print("Enter Product Name: ");
        p.name = ERPSystem.scanner.nextLine();
        System.out.print("Enter Price: ");
        p.price = ERPSystem.scanner.nextDouble();
        ERPSystem.scanner.nextLine();
        System.out.print("Enter Category: ");
        p.category = ERPSystem.scanner.nextLine();
        System.out.print("Enter Cost: ");
        p.cost = ERPSystem.scanner.nextDouble();
        ERPSystem.scanner.nextLine();
        System.out.print("Enter Initial Stock: ");
        int stock = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();
        System.out.print("Enter Reorder Level: ");
        p.reorderLevel = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        ERPSystem.allProducts.add(p);
        ERPSystem.inventory.put(p.id, stock);
        System.out.println("Product added successfully!");
    }

    public void viewAll() {
        System.out.println("\n=== ALL PRODUCTS ===");
        for(int i = 0; i < ERPSystem.allProducts.size(); i++) {
            Product p = (Product)ERPSystem.allProducts.get(i);
            System.out.println("\n--- Product " + (i+1) + " ---");
            p.print();
            int stock = (Integer)ERPSystem.inventory.get(p.id);
            System.out.println("Stock: " + stock);
            System.out.println("Profit Margin: " + p.calculateProfitMargin() + "%");
        }
    }

    public void updateStock() {
        System.out.print("Enter Product ID: ");
        int id = ERPSystem.scanner.nextInt();
        System.out.print("Add (1) or Remove (2) stock: ");
        int action = ERPSystem.scanner.nextInt();
        System.out.print("Enter quantity: ");
        int qty = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        if(ERPSystem.inventory.containsKey(id)) {
            int currentStock = (Integer)ERPSystem.inventory.get(id);
            if(action == 1) {
                ERPSystem.inventory.put(id, currentStock + qty);
                System.out.println("Stock added. New stock: " + (currentStock + qty));
            } else {
                if(currentStock >= qty) {
                    ERPSystem.inventory.put(id, currentStock - qty);
                    System.out.println("Stock removed. New stock: " + (currentStock - qty));
                } else {
                    System.out.println("Insufficient stock!");
                }
            }
        } else {
            System.out.println("Product not found!");
        }
    }

    public void checkStock() {
        System.out.print("Enter Product ID: ");
        int id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        if(ERPSystem.inventory.containsKey(id)) {
            int stock = (Integer)ERPSystem.inventory.get(id);
            System.out.println("Current Stock: " + stock);

            for(int i = 0; i < ERPSystem.allProducts.size(); i++) {
                Product p = (Product)ERPSystem.allProducts.get(i);
                if(p.id == id) {
                    if(stock <= p.reorderLevel) {
                        System.out.println("WARNING: Stock is below reorder level!");
                    }
                    break;
                }
            }
        } else {
            System.out.println("Product not found!");
        }
    }

    public void updateProduct() {
        System.out.print("Enter Product ID: ");
        int id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        for(int i = 0; i < ERPSystem.allProducts.size(); i++) {
            Product p = (Product)ERPSystem.allProducts.get(i);
            if(p.id == id) {
                System.out.print("Enter new price (current: " + p.price + "): ");
                p.price = ERPSystem.scanner.nextDouble();
                ERPSystem.scanner.nextLine();
                System.out.print("Enter new cost (current: " + p.cost + "): ");
                p.cost = ERPSystem.scanner.nextDouble();
                ERPSystem.scanner.nextLine();
                System.out.println("Product updated!");
                return;
            }
        }
        System.out.println("Product not found!");
    }

    public void deleteProduct() {
        System.out.print("Enter Product ID: ");
        int id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        for(int i = 0; i < ERPSystem.allProducts.size(); i++) {
            Product p = (Product)ERPSystem.allProducts.get(i);
            if(p.id == id) {
                ERPSystem.allProducts.remove(i);
                ERPSystem.inventory.remove(id);
                System.out.println("Product deleted!");
                return;
            }
        }
        System.out.println("Product not found!");
    }

    public void lowStockAlert() {
        System.out.println("\n=== LOW STOCK PRODUCTS ===");
        boolean found = false;
        for(int i = 0; i < ERPSystem.allProducts.size(); i++) {
            Product p = (Product)ERPSystem.allProducts.get(i);
            int stock = (Integer)ERPSystem.inventory.get(p.id);
            if(stock <= p.reorderLevel) {
                System.out.println("\nProduct: " + p.name);
                System.out.println("Current Stock: " + stock);
                System.out.println("Reorder Level: " + p.reorderLevel);
                found = true;
            }
        }
        if(!found) {
            System.out.println("No low stock items!");
        }
    }

    public void productCategories() {
        HashMap catMap = new HashMap();
        for(int i = 0; i < ERPSystem.allProducts.size(); i++) {
            Product p = (Product)ERPSystem.allProducts.get(i);
            if(catMap.containsKey(p.category)) {
                ArrayList list = (ArrayList)catMap.get(p.category);
                list.add(p);
            } else {
                ArrayList list = new ArrayList();
                list.add(p);
                catMap.put(p.category, list);
            }
        }

        System.out.println("\n=== PRODUCT CATEGORIES ===");
        Iterator it = catMap.keySet().iterator();
        while(it.hasNext()) {
            String cat = (String)it.next();
            ArrayList list = (ArrayList)catMap.get(cat);
            System.out.println("\nCategory: " + cat);
            System.out.println("Total Products: " + list.size());
        }
    }
}
