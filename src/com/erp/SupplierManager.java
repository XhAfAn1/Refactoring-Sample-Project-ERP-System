package com.erp;

import java.util.*;

public class SupplierManager {
    public ArrayList purchaseOrders = new ArrayList();

    public void showMenu() {
        while(true) {
            System.out.println("\n=== SUPPLIER MANAGEMENT ===");
            System.out.println("1. Add Supplier");
            System.out.println("2. View All Suppliers");
            System.out.println("3. Update Supplier");
            System.out.println("4. Delete Supplier");
            System.out.println("5. Create Purchase Order");
            System.out.println("6. View Purchase Orders");
            System.out.println("7. Receive Purchase Order");
            System.out.println("8. Supplier Performance");
            System.out.println("9. Back");
            System.out.print("Enter choice: ");

            int choice = ERPSystem.scanner.nextInt();
            ERPSystem.scanner.nextLine();

            if(choice == 1) {
                addSupplier();
            } else if(choice == 2) {
                viewAll();
            } else if(choice == 3) {
                updateSupplier();
            } else if(choice == 4) {
                deleteSupplier();
            } else if(choice == 5) {
                createPurchaseOrder();
            } else if(choice == 6) {
                viewPurchaseOrders();
            } else if(choice == 7) {
                receivePurchaseOrder();
            } else if(choice == 8) {
                supplierPerformance();
            } else if(choice == 9) {
                break;
            }
        }
    }

    public void addSupplier() {
        Supplier s = new Supplier();
        System.out.print("Enter Supplier ID: ");
        s.id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();
        System.out.print("Enter Name: ");
        s.name = ERPSystem.scanner.nextLine();
        System.out.print("Enter Contact Person: ");
        s.contact = ERPSystem.scanner.nextLine();
        System.out.print("Enter Phone: ");
        s.phone = ERPSystem.scanner.nextLine();
        System.out.print("Enter Email: ");
        s.email = ERPSystem.scanner.nextLine();
        System.out.print("Enter Address: ");
        s.address = ERPSystem.scanner.nextLine();
        System.out.print("Enter Payment Terms: ");
        s.paymentTerms = ERPSystem.scanner.nextLine();

        ERPSystem.allSuppliers.add(s);
        System.out.println("Supplier added successfully!");
    }

    public void viewAll() {
        System.out.println("\n=== ALL SUPPLIERS ===");
        for(int i = 0; i < ERPSystem.allSuppliers.size(); i++) {
            Supplier s = (Supplier)ERPSystem.allSuppliers.get(i);
            System.out.println("\n--- Supplier " + (i+1) + " ---");
            s.print();
        }
    }

    public void updateSupplier() {
        System.out.print("Enter Supplier ID: ");
        int id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        for(int i = 0; i < ERPSystem.allSuppliers.size(); i++) {
            Supplier s = (Supplier)ERPSystem.allSuppliers.get(i);
            if(s.id == id) {
                System.out.print("Enter new phone (current: " + s.phone + "): ");
                s.phone = ERPSystem.scanner.nextLine();
                System.out.print("Enter new email (current: " + s.email + "): ");
                s.email = ERPSystem.scanner.nextLine();
                System.out.print("Enter new rating (current: " + s.rating + "): ");
                s.rating = ERPSystem.scanner.nextDouble();
                ERPSystem.scanner.nextLine();
                System.out.println("Supplier updated!");
                return;
            }
        }
        System.out.println("Supplier not found!");
    }

    public void deleteSupplier() {
        System.out.print("Enter Supplier ID: ");
        int id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        for(int i = 0; i < ERPSystem.allSuppliers.size(); i++) {
            Supplier s = (Supplier)ERPSystem.allSuppliers.get(i);
            if(s.id == id) {
                ERPSystem.allSuppliers.remove(i);
                System.out.println("Supplier deleted!");
                return;
            }
        }
        System.out.println("Supplier not found!");
    }

    public void createPurchaseOrder() {
        System.out.print("Enter Supplier ID: ");
        int suppId = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        boolean found = false;
        for(int i = 0; i < ERPSystem.allSuppliers.size(); i++) {
            Supplier s = (Supplier)ERPSystem.allSuppliers.get(i);
            if(s.id == suppId) {
                found = true;
                break;
            }
        }

        if(!found) {
            System.out.println("Supplier not found!");
            return;
        }

        PurchaseOrder po = new PurchaseOrder();
        po.id = purchaseOrders.size() + 1;
        po.supplierId = suppId;

        System.out.println("Add items to purchase order (enter 0 to finish):");
        while(true) {
            System.out.print("Enter Product ID (0 to finish): ");
            int prodId = ERPSystem.scanner.nextInt();
            if(prodId == 0) break;

            Product product = null;
            for(int i = 0; i < ERPSystem.allProducts.size(); i++) {
                Product p = (Product)ERPSystem.allProducts.get(i);
                if(p.id == prodId) {
                    product = p;
                    break;
                }
            }

            if(product == null) {
                System.out.println("Product not found!");
                continue;
            }

            System.out.print("Enter quantity: ");
            int qty = ERPSystem.scanner.nextInt();
            System.out.print("Enter cost per unit: ");
            double cost = ERPSystem.scanner.nextDouble();
            ERPSystem.scanner.nextLine();

            PurchaseOrderItem item = new PurchaseOrderItem(prodId, product.name, qty, cost);
            po.addItem(item);
            System.out.println("Item added!");
        }

        if(po.items.size() == 0) {
            System.out.println("No items added. Purchase order cancelled.");
            return;
        }

        purchaseOrders.add(po);
        ERPSystem.totalExpenses += po.totalAmount;

        System.out.println("\nPurchase order created successfully!");
        po.print();
    }

    public void viewPurchaseOrders() {
        System.out.println("\n=== PURCHASE ORDERS ===");
        for(int i = 0; i < purchaseOrders.size(); i++) {
            PurchaseOrder po = (PurchaseOrder)purchaseOrders.get(i);
            System.out.println("\n--- PO " + (i+1) + " ---");
            po.print();
        }
    }

    public void receivePurchaseOrder() {
        System.out.print("Enter Purchase Order ID: ");
        int id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        for(int i = 0; i < purchaseOrders.size(); i++) {
            PurchaseOrder po = (PurchaseOrder)purchaseOrders.get(i);
            if(po.id == id) {
                if(po.status.equals("RECEIVED")) {
                    System.out.println("PO already received!");
                    return;
                }

                po.status = "RECEIVED";
                po.deliveredDate = new Date();

                for(int j = 0; j < po.items.size(); j++) {
                    PurchaseOrderItem item = (PurchaseOrderItem)po.items.get(j);
                    int currentStock = (Integer)ERPSystem.inventory.get(item.productId);
                    ERPSystem.inventory.put(item.productId, currentStock + item.quantity);
                }

                for(int j = 0; j < ERPSystem.allSuppliers.size(); j++) {
                    Supplier s = (Supplier)ERPSystem.allSuppliers.get(j);
                    if(s.id == po.supplierId) {
                        s.totalPurchases += po.totalAmount;
                        break;
                    }
                }

                System.out.println("Purchase order received and inventory updated!");
                return;
            }
        }
        System.out.println("Purchase order not found!");
    }

    public void supplierPerformance() {
        System.out.println("\n=== SUPPLIER PERFORMANCE ===");
        for(int i = 0; i < ERPSystem.allSuppliers.size(); i++) {
            Supplier s = (Supplier)ERPSystem.allSuppliers.get(i);
            System.out.println("\nSupplier: " + s.name);
            System.out.println("Rating: " + s.rating);
            System.out.println("Total Purchases: $" + s.totalPurchases);

            int poCount = 0;
            for(int j = 0; j < purchaseOrders.size(); j++) {
                PurchaseOrder po = (PurchaseOrder)purchaseOrders.get(j);
                if(po.supplierId == s.id) {
                    poCount++;
                }
            }
            System.out.println("Total Purchase Orders: " + poCount);
        }
    }
}
