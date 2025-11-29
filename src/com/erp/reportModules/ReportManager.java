package com.erp.reportModules;

import com.erp.coreModules.ERPSystem;
import com.erp.customerModules.Customer;
import com.erp.employeeModules.FullTimeEmployee;
import com.erp.productModules.Product;
import com.erp.salesModules.Order;
import com.erp.salesModules.OrderItem;
import java.util.*;

public class ReportManager {
    public void showMenu() {
        while(true) {
            System.out.println("\n=== REPORTS & ANALYTICS ===");
            System.out.println("1. Sales Report");
            System.out.println("2. Inventory Report");
            System.out.println("3. Employee Report");
            System.out.println("4. Customer Analysis");
            System.out.println("5. Product Performance");
            System.out.println("6. Financial Summary");
            System.out.println("7. Monthly Report");
            System.out.println("8. Export Data");
            System.out.println("9. Back");
            System.out.print("Enter choice: ");

            int choice = ERPSystem.scanner.nextInt();
            ERPSystem.scanner.nextLine();

            if(choice == 1) {
                salesReport();
            } else if(choice == 2) {
                inventoryReport();
            } else if(choice == 3) {
                employeeReport();
            } else if(choice == 4) {
                customerAnalysis();
            } else if(choice == 5) {
                productPerformance();
            } else if(choice == 6) {
                financialSummary();
            } else if(choice == 7) {
                monthlyReport();
            } else if(choice == 8) {
                exportData();
            } else if(choice == 9) {
                break;
            }
        }
    }

    public void salesReport() {
        System.out.println("\n=== SALES REPORT ===");
        System.out.println("Total Orders: " + ERPSystem.allOrders.size());
        System.out.println("Total Revenue: $" + ERPSystem.totalRevenue);

        double totalQuantity = 0;
        for(int i = 0; i < ERPSystem.allOrders.size(); i++) {
            Order o = (Order)ERPSystem.allOrders.get(i);
            for(int j = 0; j < o.items.size(); j++) {
                OrderItem item = (OrderItem)o.items.get(j);
                totalQuantity += item.quantity;
            }
        }
        System.out.println("Total Items Sold: " + (int)totalQuantity);

        if(ERPSystem.allOrders.size() > 0) {
            System.out.println("Average Order Value: $" + (ERPSystem.totalRevenue / ERPSystem.allOrders.size()));
        }

        int pending = 0, confirmed = 0, shipped = 0, delivered = 0, cancelled = 0;
        for(int i = 0; i < ERPSystem.allOrders.size(); i++) {
            Order o = (Order)ERPSystem.allOrders.get(i);
            if(o.status.equals("PENDING")) pending++;
            else if(o.status.equals("CONFIRMED")) confirmed++;
            else if(o.status.equals("SHIPPED")) shipped++;
            else if(o.status.equals("DELIVERED")) delivered++;
            else if(o.status.equals("CANCELLED")) cancelled++;
        }

        System.out.println("\nOrder Status Breakdown:");
        System.out.println("Pending: " + pending);
        System.out.println("Confirmed: " + confirmed);
        System.out.println("Shipped: " + shipped);
        System.out.println("Delivered: " + delivered);
        System.out.println("Cancelled: " + cancelled);
    }

    public void inventoryReport() {
        System.out.println("\n=== INVENTORY REPORT ===");
        System.out.println("Total Products: " + ERPSystem.allProducts.size());

        double totalInventoryValue = 0;
        int totalStock = 0;

        for(int i = 0; i < ERPSystem.allProducts.size(); i++) {
            Product p = (Product)ERPSystem.allProducts.get(i);
            int stock = (Integer)ERPSystem.inventory.get(p.id);
            totalStock += stock;
            totalInventoryValue += (p.cost * stock);
        }

        System.out.println("Total Stock Units: " + totalStock);
        System.out.println("Total Inventory Value: $" + totalInventoryValue);

        System.out.println("\n=== Stock Levels ===");
        for(int i = 0; i < ERPSystem.allProducts.size(); i++) {
            Product p = (Product)ERPSystem.allProducts.get(i);
            int stock = (Integer)ERPSystem.inventory.get(p.id);
            System.out.println(p.name + ": " + stock + " units");
        }

        System.out.println("\n=== Low Stock Items ===");
        int lowStockCount = 0;
        for(int i = 0; i < ERPSystem.allProducts.size(); i++) {
            Product p = (Product)ERPSystem.allProducts.get(i);
            int stock = (Integer)ERPSystem.inventory.get(p.id);
            if(stock <= p.reorderLevel) {
                System.out.println(p.name + ": " + stock + " (Reorder Level: " + p.reorderLevel + ")");
                lowStockCount++;
            }
        }
        if(lowStockCount == 0) {
            System.out.println("No low stock items!");
        }
    }

    public void employeeReport() {
        System.out.println("\n=== EMPLOYEE REPORT ===");
        System.out.println("Total Employees: " + ERPSystem.allEmployees.size());

        double totalSalary = 0;
        HashMap deptCount = new HashMap();

        for(int i = 0; i < ERPSystem.allEmployees.size(); i++) {
            FullTimeEmployee e = (FullTimeEmployee)ERPSystem.allEmployees.get(i);
            totalSalary += e.monthlySalary;

            if(deptCount.containsKey(e.department)) {
                int count = (Integer)deptCount.get(e.department);
                deptCount.put(e.department, count + 1);
            } else {
                deptCount.put(e.department, 1);
            }
        }

        System.out.println("Total Monthly Salary: $" + totalSalary);
        System.out.println("Total Annual Salary: $" + (totalSalary * 12));

        if(ERPSystem.allEmployees.size() > 0) {
            System.out.println("Average Salary: $" + (totalSalary / ERPSystem.allEmployees.size()));
        }

        System.out.println("\n=== Department Distribution ===");
        Iterator it = deptCount.keySet().iterator();
        while(it.hasNext()) {
            String dept = (String)it.next();
            int count = (Integer)deptCount.get(dept);
            System.out.println(dept + ": " + count + " employees");
        }
    }

    public void customerAnalysis() {
        System.out.println("\n=== CUSTOMER ANALYSIS ===");
        System.out.println("Total Customers: " + ERPSystem.allCustomers.size());

        double totalCreditLimit = 0;
        double totalBalance = 0;
        int activeCustomers = 0;

        for(int i = 0; i < ERPSystem.allCustomers.size(); i++) {
            Customer c = (Customer)ERPSystem.allCustomers.get(i);
            totalCreditLimit += c.creditLimit;
            totalBalance += c.currentBalance;
            if(c.isActive) activeCustomers++;
        }

        System.out.println("Active Customers: " + activeCustomers);
        System.out.println("Total Credit Limit: $" + totalCreditLimit);
        System.out.println("Total Outstanding: $" + totalBalance);

        HashMap customerSpending = new HashMap();
        for(int i = 0; i < ERPSystem.allOrders.size(); i++) {
            Order o = (Order)ERPSystem.allOrders.get(i);
            if(customerSpending.containsKey(o.customerId)) {
                double total = (Double)customerSpending.get(o.customerId);
                customerSpending.put(o.customerId, total + o.totalAmount);
            } else {
                customerSpending.put(o.customerId, o.totalAmount);
            }
        }

        System.out.println("\n=== Top Spending Customers ===");
        Iterator it = customerSpending.keySet().iterator();
        while(it.hasNext()) {
            int custId = (Integer)it.next();
            double total = (Double)customerSpending.get(custId);

            for(int i = 0; i < ERPSystem.allCustomers.size(); i++) {
                Customer c = (Customer)ERPSystem.allCustomers.get(i);
                if(c.id == custId) {
                    System.out.println(c.name + ": $" + total);
                    break;
                }
            }
        }
    }

    public void productPerformance() {
        System.out.println("\n=== PRODUCT PERFORMANCE ===");

        HashMap productSales = new HashMap();
        HashMap productRevenue = new HashMap();

        for(int i = 0; i < ERPSystem.allOrders.size(); i++) {
            Order o = (Order)ERPSystem.allOrders.get(i);
            for(int j = 0; j < o.items.size(); j++) {
                OrderItem item = (OrderItem)o.items.get(j);

                if(productSales.containsKey(item.productId)) {
                    int qty = (Integer)productSales.get(item.productId);
                    productSales.put(item.productId, qty + item.quantity);

                    double rev = (Double)productRevenue.get(item.productId);
                    productRevenue.put(item.productId, rev + (item.price * item.quantity));
                } else {
                    productSales.put(item.productId, item.quantity);
                    productRevenue.put(item.productId, item.price * item.quantity);
                }
            }
        }

        System.out.println("\n=== Sales by Product ===");
        for(int i = 0; i < ERPSystem.allProducts.size(); i++) {
            Product p = (Product)ERPSystem.allProducts.get(i);
            if(productSales.containsKey(p.id)) {
                int qty = (Integer)productSales.get(p.id);
                double rev = (Double)productRevenue.get(p.id);
                System.out.println("\nProduct: " + p.name);
                System.out.println("Units Sold: " + qty);
                System.out.println("Revenue: $" + rev);
                System.out.println("Profit: $" + (rev - (p.cost * qty)));
            } else {
                System.out.println("\nProduct: " + p.name);
                System.out.println("Units Sold: 0");
            }
        }
    }

    public void financialSummary() {
        System.out.println("\n=== FINANCIAL SUMMARY ===");
        System.out.println("Total Revenue: $" + ERPSystem.totalRevenue);
        System.out.println("Total Expenses: $" + ERPSystem.totalExpenses);

        double salaryExpense = 0;
        for(int i = 0; i < ERPSystem.allEmployees.size(); i++) {
            FullTimeEmployee e = (FullTimeEmployee)ERPSystem.allEmployees.get(i);
            salaryExpense += e.monthlySalary * 12;
        }
        System.out.println("Annual Salary Cost: $" + salaryExpense);

        double netProfit = ERPSystem.totalRevenue - ERPSystem.totalExpenses - salaryExpense;
        System.out.println("Net Profit/Loss: $" + netProfit);

        double totalAR = 0;
        for(int i = 0; i < ERPSystem.allCustomers.size(); i++) {
            Customer c = (Customer)ERPSystem.allCustomers.get(i);
            totalAR += c.currentBalance;
        }
        System.out.println("\nAccounts Receivable: $" + totalAR);

        double inventoryValue = 0;
        for(int i = 0; i < ERPSystem.allProducts.size(); i++) {
            Product p = (Product)ERPSystem.allProducts.get(i);
            int stock = (Integer)ERPSystem.inventory.get(p.id);
            inventoryValue += (p.cost * stock);
        }
        System.out.println("Inventory Value: $" + inventoryValue);

        System.out.println("\n=== Key Metrics ===");
        if(ERPSystem.totalRevenue > 0) {
            double profitMargin = (netProfit / ERPSystem.totalRevenue) * 100;
            System.out.println("Profit Margin: " + profitMargin + "%");
        }

        if(ERPSystem.allOrders.size() > 0) {
            System.out.println("Average Order Value: $" + (ERPSystem.totalRevenue / ERPSystem.allOrders.size()));
        }
    }

    public void monthlyReport() {
        System.out.println("\n=== MONTHLY REPORT ===");
        System.out.println("Generated: " + new Date());
        System.out.println("\n--- SALES ---");
        System.out.println("Total Orders: " + ERPSystem.allOrders.size());
        System.out.println("Revenue: $" + ERPSystem.totalRevenue);

        System.out.println("\n--- INVENTORY ---");
        System.out.println("Total Products: " + ERPSystem.allProducts.size());
        int totalStock = 0;
        for(int i = 0; i < ERPSystem.allProducts.size(); i++) {
            Product p = (Product)ERPSystem.allProducts.get(i);
            int stock = (Integer)ERPSystem.inventory.get(p.id);
            totalStock += stock;
        }
        System.out.println("Total Stock: " + totalStock);

        System.out.println("\n--- HUMAN RESOURCES ---");
        System.out.println("Total Employees: " + ERPSystem.allEmployees.size());
        double totalSalary = 0;
        for(int i = 0; i < ERPSystem.allEmployees.size(); i++) {
            FullTimeEmployee e = (FullTimeEmployee)ERPSystem.allEmployees.get(i);
            totalSalary += e.monthlySalary;
        }
        System.out.println("Monthly Payroll: $" + totalSalary);

        System.out.println("\n--- CUSTOMERS ---");
        System.out.println("Total Customers: " + ERPSystem.allCustomers.size());

        System.out.println("\n--- FINANCES ---");
        System.out.println("Revenue: $" + ERPSystem.totalRevenue);
        System.out.println("Expenses: $" + ERPSystem.totalExpenses);
        System.out.println("Net: $" + (ERPSystem.totalRevenue - ERPSystem.totalExpenses));
    }

    public void exportData() {
        System.out.println("\n=== EXPORT DATA ===");
        System.out.println("1. Export Employees");
        System.out.println("2. Export Products");
        System.out.println("3. Export Orders");
        System.out.println("4. Export Customers");
        System.out.print("Enter choice: ");
        int choice = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        if(choice == 1) {
            System.out.println("\nExporting employees to employees.txt...");
            System.out.println("ID,Name,Department,Salary,Email");
            for(int i = 0; i < ERPSystem.allEmployees.size(); i++) {
                FullTimeEmployee e = (FullTimeEmployee)ERPSystem.allEmployees.get(i);
                System.out.println(e.id + "," + e.name + "," + e.department + "," + e.monthlySalary + "," + e.email);
            }
        } else if(choice == 2) {
            System.out.println("\nExporting products to products.txt...");
            System.out.println("ID,Name,Price,Category,Stock");
            for(int i = 0; i < ERPSystem.allProducts.size(); i++) {
                Product p = (Product)ERPSystem.allProducts.get(i);
                int stock = (Integer)ERPSystem.inventory.get(p.id);
                System.out.println(p.id + "," + p.name + "," + p.price + "," + p.category + "," + stock);
            }
        } else if(choice == 3) {
            System.out.println("\nExporting orders to orders.txt...");
            System.out.println("ID,CustomerID,OrderDate,Status,TotalAmount");
            for(int i = 0; i < ERPSystem.allOrders.size(); i++) {
                Order o = (Order)ERPSystem.allOrders.get(i);
                System.out.println(o.id + "," + o.customerId + "," + o.orderDate + "," + o.status + "," + o.totalAmount);
            }
        } else if(choice == 4) {
            System.out.println("\nExporting customers to customers.txt...");
            System.out.println("ID,Name,Email,Phone,CreditLimit,Balance");
            for(int i = 0; i < ERPSystem.allCustomers.size(); i++) {
                Customer c = (Customer)ERPSystem.allCustomers.get(i);
                System.out.println(c.id + "," + c.name + "," + c.email + "," + c.phone + "," + c.creditLimit + "," + c.currentBalance);
            }
        }

        System.out.println("\nExport completed!");
    }
}
