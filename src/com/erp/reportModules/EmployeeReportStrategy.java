package com.erp.reportModules;

import com.erp.coreModules.ERPSystem;
import com.erp.employeeModules.FullTimeEmployee;

import java.util.HashMap;

public class EmployeeReportStrategy implements ReportStrategy {

    @Override
    public String getName() {
        return "Employee Report";
    }

    @Override
    public void generate() {
        System.out.println("\n=== EMPLOYEE REPORT ===");
        System.out.println("Total Employees: " + ERPSystem.allEmployees.size());

        double totalSalary = 0;
        HashMap<String, Integer> deptCount = new HashMap<>();

        for (Object obj : ERPSystem.allEmployees) {
            FullTimeEmployee e = (FullTimeEmployee) obj;
            totalSalary += e.monthlySalary;
            deptCount.put(
                    e.employee_department,
                    deptCount.getOrDefault(e.employee_department, 0) + 1
            );
        }

        System.out.println("Total Monthly Salary: $" + totalSalary);
        System.out.println("Average Salary: $" +
                (ERPSystem.allEmployees.isEmpty() ? 0 : totalSalary / ERPSystem.allEmployees.size()));

        System.out.println("\nDepartment Distribution:");
        for (String dept : deptCount.keySet()) {
            System.out.println(dept + ": " + deptCount.get(dept));
        }
    }
}
