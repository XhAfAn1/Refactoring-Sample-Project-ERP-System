package com.erp.supplierModules;

import com.erp.coreModules.ERPSystem;
import com.erp.productModules.Product;
import java.util.*;

public class SupplierManager {
    private static final int EXIT_CHOICE = 9;
    private static final int FINISH_ADDING_ITEMS = 0;
    
    public ArrayList<PurchaseOrder> purchaseOrders = new ArrayList<>();

    public void showMenu() {
        while (true) {
            displayMenuOptions();
            int choice = getUserChoice();
            
            if (choice == EXIT_CHOICE) {
                break;
            }
            
            handleMenuChoice(choice);
        }
    }

    private void displayMenuOptions() {
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
    }

    private int getUserChoice() {
        int choice = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();
        return choice;
    }

    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1: addSupplier(); break;
            case 2: viewAll(); break;
            case 3: updateSupplier(); break;
            case 4: deleteSupplier(); break;
            case 5: createPurchaseOrder(); break;
            case 6: viewPurchaseOrders(); break;
            case 7: receivePurchaseOrder(); break;
            case 8: supplierPerformance(); break;
        }
    }

    public void addSupplier() {
        Supplier supplier = createSupplierFromInput();
        ERPSystem.allSuppliers.add(supplier);
        System.out.println("Supplier added successfully!");
    }

    private Supplier createSupplierFromInput() {
        Supplier s = new Supplier();
        s.supplierId = readInt("Enter Supplier ID: ");
        s.supplierName = readLine("Enter Name: ");
        s.supplierContact = readLine("Enter Contact Person: ");
        s.supplierPhone = readLine("Enter Phone: ");
        s.supplierEmail = readLine("Enter Email: ");
        s.supplierAdress = readLine("Enter Address: ");
        s.supplierPaymentTerms = readLine("Enter Payment Terms: ");
        return s;
    }

    public void viewAll() {
        System.out.println("\n=== ALL SUPPLIERS ===");
        for (int i = 0; i < ERPSystem.allSuppliers.size(); i++) {
            Supplier s = (Supplier) ERPSystem.allSuppliers.get(i);
            System.out.println("\n--- Supplier " + (i + 1) + " ---");
            s.print();
        }
    }

    public void updateSupplier() {
        int id = readInt("Enter Supplier ID: ");
        Supplier supplier = findSupplierById(id);
        
        if (supplier == null) {
            System.out.println("Supplier not found!");
            return;
        }
        
        updateSupplierDetails(supplier);
        System.out.println("Supplier updated!");
    }

    private void updateSupplierDetails(Supplier supplier) {
        String newPhone = readLine("Enter new phone (current: " + supplier.supplierPhone + "): ");
        String newEmail = readLine("Enter new email (current: " + supplier.supplierEmail + "): ");
        double newRating = readDouble("Enter new rating (current: " + supplier.supplierRating + "): ");
        
        supplier.updateContactInfo(newPhone, newEmail, newRating);
    }

    public void deleteSupplier() {
        int id = readInt("Enter Supplier ID: ");
        
        if (removeSupplierById(id)) {
            System.out.println("Supplier deleted!");
        } else {
            System.out.println("Supplier not found!");
        }
    }

    private boolean removeSupplierById(int id) {
        for (int i = 0; i < ERPSystem.allSuppliers.size(); i++) {
            Supplier s = (Supplier) ERPSystem.allSuppliers.get(i);
            if (s.supplierId == id) {
                ERPSystem.allSuppliers.remove(i);
                return true;
            }
        }
        return false;
    }

    public void createPurchaseOrder() {
        int supplierId = readInt("Enter Supplier ID: ");
        
        if (!isValidSupplier(supplierId)) {
            System.out.println("Supplier not found!");
            return;
        }

        PurchaseOrder po = buildPurchaseOrder(supplierId);
        
        if (po == null) {
            System.out.println("No items added. Purchase order cancelled.");
            return;
        }

        savePurchaseOrder(po);
        displayPurchaseOrderConfirmation(po);
    }

    private PurchaseOrder buildPurchaseOrder(int supplierId) {
        PurchaseOrder po = initializePurchaseOrder(supplierId);
        
        System.out.println("Add items to purchase order (enter 0 to finish):");
        addItemsToPurchaseOrder(po);
        
        return po.items.size() > 0 ? po : null;
    }

    private PurchaseOrder initializePurchaseOrder(int supplierId) {
        PurchaseOrder po = new PurchaseOrder();
        po.purchaseOrderId = purchaseOrders.size() + 1;
        po.supplierId = supplierId;
        return po;
    }

    private void addItemsToPurchaseOrder(PurchaseOrder po) {
        while (true) {
            int productId = readInt("Enter Product ID (0 to finish): ");
            
            if (productId == FINISH_ADDING_ITEMS) {
                break;
            }

            Product product = findProductById(productId);
            
            if (product == null) {
                System.out.println("Product not found!");
                continue;
            }

            PurchaseOrderItem item = createPurchaseOrderItem(productId, product);
            po.addItem(item);
            System.out.println("Item added!");
        }
    }

    private PurchaseOrderItem createPurchaseOrderItem(int productId, Product product) {
        int quantity = readInt("Enter quantity: ");
        double cost = readDouble("Enter cost per unit: ");
        return new PurchaseOrderItem(productId, product.productName, quantity, cost);
    }

    private void savePurchaseOrder(PurchaseOrder po) {
        purchaseOrders.add(po);
        ERPSystem.totalExpenses += po.totalAmount;
    }

    private void displayPurchaseOrderConfirmation(PurchaseOrder po) {
        System.out.println("\nPurchase order created successfully!");
        po.print();
    }

    public void viewPurchaseOrders() {
        System.out.println("\n=== PURCHASE ORDERS ===");
        for (int i = 0; i < purchaseOrders.size(); i++) {
            PurchaseOrder po = (PurchaseOrder) purchaseOrders.get(i);
            System.out.println("\n--- PO " + (i + 1) + " ---");
            po.print();
        }
    }

    public void receivePurchaseOrder() {
        int id = readInt("Enter Purchase Order ID: ");
        PurchaseOrder po = findPurchaseOrderById(id);
        
        if (po == null) {
            System.out.println("Purchase order not found!");
            return;
        }
        
        if (isAlreadyReceived(po)) {
            System.out.println("PO already received!");
            return;
        }

        processPurchaseOrderReceipt(po);
        System.out.println("Purchase order received and inventory updated!");
    }

    private void processPurchaseOrderReceipt(PurchaseOrder po) {
        markAsReceived(po);
        updateInventoryFromPurchaseOrder(po);
        updateSupplierPurchaseTotal(po);
    }

    private void markAsReceived(PurchaseOrder po) {
        po.status = "RECEIVED";
        po.deliveredDate = new Date();
    }

    private boolean isAlreadyReceived(PurchaseOrder po) {
        return "RECEIVED".equals(po.status);
    }

    private void updateInventoryFromPurchaseOrder(PurchaseOrder po) {
        for (int i = 0; i < po.items.size(); i++) {
            PurchaseOrderItem item = (PurchaseOrderItem) po.items.get(i);
            updateProductInventory(item);
        }
    }

    private void updateProductInventory(PurchaseOrderItem item) {
        int currentStock = (Integer) ERPSystem.inventory.get(item.productId);
        ERPSystem.inventory.put(item.productId, currentStock + item.quantity);
    }

    private void updateSupplierPurchaseTotal(PurchaseOrder po) {
        Supplier supplier = findSupplierById(po.supplierId);
        if (supplier != null) {
            supplier.supplierTotalPurchases += po.totalAmount;
        }
    }

    public void supplierPerformance() {
        System.out.println("\n=== SUPPLIER PERFORMANCE ===");
        for (int i = 0; i < ERPSystem.allSuppliers.size(); i++) {
            Supplier s = (Supplier) ERPSystem.allSuppliers.get(i);
            displaySupplierPerformance(s);
        }
    }

    private void displaySupplierPerformance(Supplier supplier) {
        System.out.println("\nSupplier: " + supplier.supplierName);
        System.out.println("Rating: " + supplier.supplierRating);
        System.out.println("Total Purchases: $" + supplier.supplierTotalPurchases);
        System.out.println("Total Purchase Orders: " + countPurchaseOrdersForSupplier(supplier.supplierId));
    }

    private int countPurchaseOrdersForSupplier(int supplierId) {
        int count = 0;
        for (int i = 0; i < purchaseOrders.size(); i++) {
            PurchaseOrder po = (PurchaseOrder) purchaseOrders.get(i);
            if (po.supplierId == supplierId) {
                count++;
            }
        }
        return count;
    }

    private Supplier findSupplierById(int id) {
        for (int i = 0; i < ERPSystem.allSuppliers.size(); i++) {
            Supplier s = (Supplier) ERPSystem.allSuppliers.get(i);
            if (s.supplierId == id) {
                return s;
            }
        }
        return null;
    }

    private boolean isValidSupplier(int supplierId) {
        return findSupplierById(supplierId) != null;
    }

    private Product findProductById(int productId) {
        for (int i = 0; i < ERPSystem.allProducts.size(); i++) {
            Product p = (Product) ERPSystem.allProducts.get(i);
            if (p.productId == productId) {
                return p;
            }
        }
        return null;
    }

    private PurchaseOrder findPurchaseOrderById(int id) {
        for (int i = 0; i < purchaseOrders.size(); i++) {
            PurchaseOrder po = (PurchaseOrder) purchaseOrders.get(i);
            if (po.purchaseOrderId == id) {
                return po;
            }
        }
        return null;
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        int value = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();
        return value;
    }

    private double readDouble(String prompt) {
        System.out.print(prompt);
        double value = ERPSystem.scanner.nextDouble();
        ERPSystem.scanner.nextLine();
        return value;
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        return ERPSystem.scanner.nextLine();
    }
}