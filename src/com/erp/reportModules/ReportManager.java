package com.erp.reportModules;

import com.erp.coreModules.ERPSystem;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReportManager {

    private final Map<Integer, ReportStrategy> strategies = new LinkedHashMap<>();

    public ReportManager() {
        strategies.put(1, new SalesReportStrategy());
        strategies.put(2, new InventoryReportStrategy());
        strategies.put(3, new EmployeeReportStrategy());
        strategies.put(4, new MonthlyReportStrategy());
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== REPORTS & ANALYTICS ===");
            for (Map.Entry<Integer, ReportStrategy> entry : strategies.entrySet()) {
                System.out.println(entry.getKey() + ". " + entry.getValue().getName());
            }
            System.out.println("9. Back");
            System.out.print("Enter choice: ");

            int choice = ERPSystem.scanner.nextInt();
            ERPSystem.scanner.nextLine();

            if (choice == 9) break;

            ReportStrategy strategy = strategies.get(choice);
            if (strategy != null) {
                strategy.generate();
            } else {
                System.out.println("Invalid option!");
            }
        }
    }
}
