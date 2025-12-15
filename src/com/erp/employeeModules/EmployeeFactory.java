package com.erp.employeeModules;

public class EmployeeFactory {
    public static Employee createEmployee(int type) {
        switch (type) {
            case 1: return new FullTimeEmployee();
            case 2: return new PartTimeEmployee();
            default: return null;
        }
    }
}
