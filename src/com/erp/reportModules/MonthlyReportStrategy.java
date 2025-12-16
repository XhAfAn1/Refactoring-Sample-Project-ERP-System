package com.erp.reportModules;

import com.erp.coreModules.ERPSystem;
import com.erp.employeeModules.FullTimeEmployee;
import com.erp.productModules.Product;

public class MonthlyReportStrategy implements ReportStrategy {

    @Override
    public String getName() {
        return "Monthly Report";
    }

    @Override
    public void generate() {
        System.out.println("\n=== MONTHLY REPORT ===");

        System.out.println("Orders: " + ERPSystem.allOrders.size());
        System.out.println("Revenue: $" + ERPSystem.totalRevenue);

        int totalStock = 0;
        for (Object obj : ERPSystem.allProducts) {
            Product p = (Product) obj;
            totalStock += (Integer) ERPSystem.inventory.get(p.product_id);
        }
        System.out.println("Total Stock: " + totalStock);

        double payroll = 0;
        for (Object obj : ERPSystem.allEmployees) {
            FullTimeEmployee e = (FullTimeEmployee) obj;
            payroll += e.monthlySalary;
        }
        System.out.println("Monthly Payroll: $" + payroll);
    }
}
