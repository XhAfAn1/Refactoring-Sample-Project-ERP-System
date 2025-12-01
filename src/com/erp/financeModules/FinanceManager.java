package com.erp.financeModules;

import com.erp.coreModules.ERPSystem;
import com.erp.customerModules.Customer;
import com.erp.employeeModules.FullTimeEmployee;
import com.erp.enums.PaymentStatus;
import com.erp.enums.TransactionType;
import com.erp.supplierModules.PurchaseOrder;
import com.erp.supplierModules.SupplierManager;
import java.util.*;

public class FinanceManager {
    // Refactored: Use Generic List instead of raw ArrayList
    public List<Transaction> transactions = new ArrayList<>();

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

            if(choice == 1) addTransaction();
            else if(choice == 2) viewAll();
            else if(choice == 3) incomeStatement();
            else if(choice == 4) expenseReport();
            else if(choice == 5) cashFlow();
            else if(choice == 6) profitLoss();
            else if(choice == 7) accountsReceivable();
            else if(choice == 8) accountsPayable();
            else if(choice == 9) break;
        }
    }

    public void addTransaction() {
        Transaction t = new Transaction();
        t.id = transactions.size() + 1;

        System.out.println("Select transaction type:");
        System.out.println("1. Income");
        System.out.println("2. Expense");
        System.out.print("Enter choice: ");
        int typeChoice = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        // Refactored: Assign Enum instead of String
        if(typeChoice == 1) {
            t.type = TransactionType.INCOME;
        } else {
            t.type = TransactionType.EXPENSE;
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

        // Refactored: Enum comparison
        if(t.type == TransactionType.INCOME) {
            ERPSystem.totalRevenue += t.amount;
        } else {
            ERPSystem.totalExpenses += t.amount;
        }

        System.out.println("Transaction added successfully!");
    }

    public void viewAll() {
        System.out.println("\n=== ALL TRANSACTIONS ===");
        for(int i = 0; i < transactions.size(); i++) {
            Transaction t = transactions.get(i);
            System.out.println("\n--- Transaction " + (i+1) + " ---");
            t.print();
        }
    }

    // Refactored: Helper method using Enums to fix Duplicate Code
    private void generateCategoryReport(String title, TransactionType type, String totalLabel) {
        double total = 0;
        HashMap<String, Double> categories = new HashMap<>();

        for(Transaction t : transactions) {
            // Refactored: Enum equality check
            if(t.type == type) {
                total += t.amount;

                if(categories.containsKey(t.category)) {
                    double amt = categories.get(t.category);
                    categories.put(t.category, amt + t.amount);
                } else {
                    categories.put(t.category, t.amount);
                }
            }
        }

        System.out.println("\n=== " + title + " ===");
        System.out.println("\n" + (type == TransactionType.INCOME ? "Income" : "Expenses") + " by Category:");
        for(String cat : categories.keySet()) {
            System.out.println(cat + ": $" + categories.get(cat));
        }
        System.out.println("\n" + totalLabel + ": $" + total);
    }

    // Refactored: Extracted Logic to fix Duplicate Code
    private double calculateTotalMonthlySalaries() {
        double total = 0;
        // Note: Casting to FullTimeEmployee is a temporary limitation (Liskov substitution smell)
        // kept here as per scope, but ideally Employee should have abstract getSalary()
        for(int i = 0; i < ERPSystem.allEmployees.size(); i++) {
            FullTimeEmployee e = (FullTimeEmployee)ERPSystem.allEmployees.get(i);
            total += e.monthlySalary;
        }
        return total;
    }

    public void incomeStatement() {
        generateCategoryReport("INCOME STATEMENT", TransactionType.INCOME, "Total Income");
    }

    public void expenseReport() {
        generateCategoryReport("EXPENSE REPORT", TransactionType.EXPENSE, "Total Expenses");
        
        double salaryExpense = calculateTotalMonthlySalaries();
        System.out.println("Employee Salaries: $" + salaryExpense);
    }

    public void cashFlow() {
        double totalIncome = 0;
        double totalExpense = 0;

        for(Transaction t : transactions) {
            // Refactored: Enum comparison
            if(t.type == TransactionType.INCOME) {
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

        double annualSalaryExpense = calculateTotalMonthlySalaries() * 12;
        System.out.println("Annual Salary Expense: $" + annualSalaryExpense);

        double totalCost = ERPSystem.totalExpenses + annualSalaryExpense;
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

        // Refactored loop using Generic List from ERPSystem
        for(Customer c : (ArrayList<Customer>)ERPSystem.allCustomers) {
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
        // Refactored to use Generic List if SupplierManager was updated, or cast safely
        for(Object obj : sm.purchaseOrders) {
            PurchaseOrder po = (PurchaseOrder)obj;
            // Refactored: Enum comparison
            if(po.paymentStatus == PaymentStatus.UNPAID) {
                System.out.println("\nPO ID: " + po.id);
                System.out.println("Supplier ID: " + po.supplierId);
                System.out.println("Amount: $" + po.totalAmount);
                totalPayable += po.totalAmount;
            }
        }

        System.out.println("\nTotal Accounts Payable: $" + totalPayable);
    }
}