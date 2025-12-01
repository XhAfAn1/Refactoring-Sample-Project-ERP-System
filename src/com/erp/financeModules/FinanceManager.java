package com.erp.financeModules;

import com.erp.coreModules.ERPSystem;
import com.erp.customerModules.Customer;
import com.erp.employeeModules.FullTimeEmployee;
import com.erp.supplierModules.PurchaseOrder;
import com.erp.supplierModules.SupplierManager;
import java.util.*;

public class FinanceManager {
    public ArrayList transactions = new ArrayList();

    public void showMenu() {
        while(true) {
            System.out.println("\n=== FINANCE & ACCOUNTING ===");
            System.out.println("1. Add Transaction");
            System.out.println("2. View All Transactions");
            System.out.println("3. Income Statement");
            System.out.println("4. Expense Report");
            System.out.println("5. Cash Flow");
            System.out.println("6. Profit & Loss");
            System.out.println("7. Accounts Receivable");
            System.out.println("8. Accounts Payable");
            System.out.println("9. Back");
            System.out.print("Enter choice: ");

            int choice = ERPSystem.scanner.nextInt();
            ERPSystem.scanner.nextLine();

            if(choice == 1) {
                addTransaction();
            } else if(choice == 2) {
                viewAll();
            } else if(choice == 3) {
                incomeStatement();
            } else if(choice == 4) {
                expenseReport();
            } else if(choice == 5) {
                cashFlow();
            } else if(choice == 6) {
                profitLoss();
            } else if(choice == 7) {
                accountsReceivable();
            } else if(choice == 8) {
                accountsPayable();
            } else if(choice == 9) {
                break;
            }
        }
    }

    public void addTransaction() {
        Transaction t = new Transaction();
        t.id = transactions.size() + 1;

        System.out.println("Select transaction type:");
        System.out.println("1. Income");
        System.out.println("2. Expense");
        System.out.print("Enter choice: ");
        int type = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        if(type == 1) {
            t.type = "INCOME";
        } else {
            t.type = "EXPENSE";
        }

        System.out.print("Enter amount: ");
        t.amount = ERPSystem.scanner.nextDouble();
        ERPSystem.scanner.nextLine();
        System.out.print("Enter description: ");
        t.description = ERPSystem.scanner.nextLine();
        System.out.print("Enter category: ");
        t.category = ERPSystem.scanner.nextLine();
        System.out.print("Enter payment method: ");
        t.paymentMethod = ERPSystem.scanner.nextLine();

        transactions.add(t);

        if(t.type.equals("INCOME")) {
            ERPSystem.totalRevenue += t.amount;
        } else {
            ERPSystem.totalExpenses += t.amount;
        }

        System.out.println("Transaction added successfully!");
    }

    public void viewAll() {
        System.out.println("\n=== ALL TRANSACTIONS ===");
        for(int i = 0; i < transactions.size(); i++) {
            Transaction t = (Transaction)transactions.get(i);
            System.out.println("\n--- Transaction " + (i+1) + " ---");
            t.print();
        }
    }

    public void incomeStatement() {
        double totalIncome = 0;
        HashMap incomeCategories = new HashMap();

        for(int i = 0; i < transactions.size(); i++) {
            Transaction t = (Transaction)transactions.get(i);
            if(t.type.equals("INCOME")) {
                totalIncome += t.amount;

                if(incomeCategories.containsKey(t.category)) {
                    double amt = (Double)incomeCategories.get(t.category);
                    incomeCategories.put(t.category, amt + t.amount);
                } else {
                    incomeCategories.put(t.category, t.amount);
                }
            }
        }

        System.out.println("\n=== INCOME STATEMENT ===");
        System.out.println("\nIncome by Category:");
        Iterator it = incomeCategories.keySet().iterator();
        while(it.hasNext()) {
            String cat = (String)it.next();
            double amt = (Double)incomeCategories.get(cat);
            System.out.println(cat + ": $" + amt);
        }
        System.out.println("\nTotal Income: $" + totalIncome);
    }

    public void expenseReport() {
        double totalExpense = 0;
        HashMap expenseCategories = new HashMap();

        for(int i = 0; i < transactions.size(); i++) {
            Transaction t = (Transaction)transactions.get(i);
            if(t.type.equals("EXPENSE")) {
                totalExpense += t.amount;

                if(expenseCategories.containsKey(t.category)) {
                    double amt = (Double)expenseCategories.get(t.category);
                    expenseCategories.put(t.category, amt + t.amount);
                } else {
                    expenseCategories.put(t.category, t.amount);
                }
            }
        }

        System.out.println("\n=== EXPENSE REPORT ===");
        System.out.println("\nExpenses by Category:");
        Iterator it = expenseCategories.keySet().iterator();
        while(it.hasNext()) {
            String cat = (String)it.next();
            double amt = (Double)expenseCategories.get(cat);
            System.out.println(cat + ": $" + amt);
        }
        System.out.println("\nTotal Expenses: $" + totalExpense);

        double salaryExpense = 0;
        for(int i = 0; i < ERPSystem.allEmployees.size(); i++) {
            FullTimeEmployee e = (FullTimeEmployee)ERPSystem.allEmployees.get(i);
            salaryExpense += e.monthlySalary;
        }
        System.out.println("Employee Salaries: $" + salaryExpense);
    }

    public void cashFlow() {
        double totalIncome = 0;
        double totalExpense = 0;

        for(int i = 0; i < transactions.size(); i++) {
            Transaction t = (Transaction)transactions.get(i);
            if(t.type.equals("INCOME")) {
                totalIncome += t.amount;
            } else {
                totalExpense += t.amount;
            }
        }

        System.out.println("\n=== CASH FLOW STATEMENT ===");
        System.out.println("Total Cash Inflow: $" + totalIncome);
        System.out.println("Total Cash Outflow: $" + totalExpense);
        System.out.println("Net Cash Flow: $" + (totalIncome - totalExpense));
    }

    public void profitLoss() {
        System.out.println("\n=== PROFIT & LOSS STATEMENT ===");
        System.out.println("Total Revenue: $" + ERPSystem.totalRevenue);
        System.out.println("Total Expenses: $" + ERPSystem.totalExpenses);

        double salaryExpense = 0;
        for(int i = 0; i < ERPSystem.allEmployees.size(); i++) {
            FullTimeEmployee e = (FullTimeEmployee)ERPSystem.allEmployees.get(i);
            salaryExpense += e.monthlySalary * 12;
        }
        System.out.println("Annual Salary Expense: $" + salaryExpense);

        double totalCost = ERPSystem.totalExpenses + salaryExpense;
        double netProfit = ERPSystem.totalRevenue - totalCost;

        System.out.println("\nTotal Costs: $" + totalCost);
        System.out.println("Net Profit/Loss: $" + netProfit);

        if(netProfit > 0) {
            double margin = (netProfit / ERPSystem.totalRevenue) * 100;
            System.out.println("Profit Margin: " + margin + "%");
        }
    }

    public void accountsReceivable() {
        System.out.println("\n=== ACCOUNTS RECEIVABLE ===");
        double totalReceivable = 0;

        for(int i = 0; i < ERPSystem.allCustomers.size(); i++) {
            Customer c = (Customer)ERPSystem.allCustomers.get(i);
            if(c.customer_currentBalance > 0) {
                System.out.println("\nCustomer: " + c.customer_name);
                System.out.println("Outstanding: $" + c.customer_currentBalance);
                totalReceivable += c.customer_currentBalance;
            }
        }

        System.out.println("\nTotal Accounts Receivable: $" + totalReceivable);
    }

    public void accountsPayable() {
        System.out.println("\n=== ACCOUNTS PAYABLE ===");
        double totalPayable = 0;

        SupplierManager sm = new SupplierManager();
        for(int i = 0; i < sm.purchaseOrders.size(); i++) {
            PurchaseOrder po = (PurchaseOrder)sm.purchaseOrders.get(i);
            if(po.paymentStatus.equals("UNPAID")) {
                System.out.println("\nPO ID: " + po.id);
                System.out.println("Supplier ID: " + po.supplierId);
                System.out.println("Amount: $" + po.totalAmount);
                totalPayable += po.totalAmount;
            }
        }

        System.out.println("\nTotal Accounts Payable: $" + totalPayable);
    }
}
