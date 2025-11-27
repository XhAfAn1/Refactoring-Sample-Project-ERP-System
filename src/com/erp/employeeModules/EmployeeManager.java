package com.erp.employeeModules;

import java.util.*;

import com.erp.coreModules.ERPSystem;



public class EmployeeManager {
    public void showMenu() {
        while(true) {
            System.out.println("\n=== EMPLOYEE MANAGEMENT ===");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Give Raise");
            System.out.println("7. Add Bonus");
            System.out.println("8. Department Report");
            System.out.println("9. Back");
            System.out.print("Enter choice: ");

            int choice = ERPSystem.scanner.nextInt();
            ERPSystem.scanner.nextLine();

            if(choice == 1) {
                addEmployee();
            } else if(choice == 2) {
                viewAll();
            } else if(choice == 3) {
                searchEmployee();
            } else if(choice == 4) {
                updateEmployee();
            } else if(choice == 5) {
                deleteEmployee();
            } else if(choice == 6) {
                giveRaise();
            } else if(choice == 7) {
                addBonus();
            } else if(choice == 8) {
                departmentReport();
            } else if(choice == 9) {
                break;
            }
        }
    }

    public void addEmployee() {
        Employee e = new Employee();
        System.out.print("Enter ID: ");
        e.id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();
        System.out.print("Enter Name: ");
        e.name = ERPSystem.scanner.nextLine();
        System.out.print("Enter Department: ");
        e.department = ERPSystem.scanner.nextLine();
        System.out.print("Enter Salary: ");
        e.salary = ERPSystem.scanner.nextDouble();
        ERPSystem.scanner.nextLine();
        System.out.print("Enter Email: ");
        e.email = ERPSystem.scanner.nextLine();
        System.out.print("Enter Phone: ");
        e.phone = ERPSystem.scanner.nextLine();
        System.out.print("Enter Position: ");
        e.position = ERPSystem.scanner.nextLine();

        ERPSystem.allEmployees.add(e);
        System.out.println("Employee added successfully!");
    }

    public void viewAll() {
        System.out.println("\n=== ALL EMPLOYEES ===");
        for(int i = 0; i < ERPSystem.allEmployees.size(); i++) {
            Employee e = (Employee)ERPSystem.allEmployees.get(i);
            System.out.println("\n--- Employee " + (i+1) + " ---");
            e.print();
        }
    }

    public void searchEmployee() {
        System.out.print("Enter Employee ID: ");
        int id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        boolean found = false;
        for(int i = 0; i < ERPSystem.allEmployees.size(); i++) {
            Employee e = (Employee)ERPSystem.allEmployees.get(i);
            if(e.id == id) {
                e.print();
                found = true;
                break;
            }
        }

        if(!found) {
            System.out.println("Employee not found!");
        }
    }

    public void updateEmployee() {
        System.out.print("Enter Employee ID to update: ");
        int id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        for(int i = 0; i < ERPSystem.allEmployees.size(); i++) {
            Employee e = (Employee)ERPSystem.allEmployees.get(i);
            if(e.id == id) {
                System.out.print("Enter new name (current: " + e.name + "): ");
                e.name = ERPSystem.scanner.nextLine();
                System.out.print("Enter new salary (current: " + e.salary + "): ");
                e.salary = ERPSystem.scanner.nextDouble();
                ERPSystem.scanner.nextLine();
                System.out.println("Employee updated!");
                return;
            }
        }
        System.out.println("Employee not found!");
    }

    public void deleteEmployee() {
        System.out.print("Enter Employee ID to delete: ");
        int id = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        for(int i = 0; i < ERPSystem.allEmployees.size(); i++) {
            Employee e = (Employee)ERPSystem.allEmployees.get(i);
            if(e.id == id) {
                ERPSystem.allEmployees.remove(i);
                System.out.println("Employee deleted!");
                return;
            }
        }
        System.out.println("Employee not found!");
    }

    public void giveRaise() {
        System.out.print("Enter Employee ID: ");
        int id = ERPSystem.scanner.nextInt();
        System.out.print("Enter raise percentage: ");
        double percent = ERPSystem.scanner.nextDouble();
        ERPSystem.scanner.nextLine();

        for(int i = 0; i < ERPSystem.allEmployees.size(); i++) {
            Employee e = (Employee)ERPSystem.allEmployees.get(i);
            if(e.id == id) {
                e.giveRaise(percent);
                return;
            }
        }
        System.out.println("Employee not found!");
    }

    public void addBonus() {
        System.out.print("Enter Employee ID: ");
        int id = ERPSystem.scanner.nextInt();
        System.out.print("Enter bonus amount: ");
        double amount = ERPSystem.scanner.nextDouble();
        ERPSystem.scanner.nextLine();

        for(int i = 0; i < ERPSystem.allEmployees.size(); i++) {
            Employee e = (Employee)ERPSystem.allEmployees.get(i);
            if(e.id == id) {
                e.addBonus(amount);
                return;
            }
        }
        System.out.println("Employee not found!");
    }

    public void departmentReport() {
        HashMap deptMap = new HashMap();
        for(int i = 0; i < ERPSystem.allEmployees.size(); i++) {
            Employee e = (Employee)ERPSystem.allEmployees.get(i);
            if(deptMap.containsKey(e.department)) {
                ArrayList list = (ArrayList)deptMap.get(e.department);
                list.add(e);
            } else {
                ArrayList list = new ArrayList();
                list.add(e);
                deptMap.put(e.department, list);
            }
        }

        System.out.println("\n=== DEPARTMENT REPORT ===");
        Iterator it = deptMap.keySet().iterator();
        while(it.hasNext()) {
            String dept = (String)it.next();
            ArrayList list = (ArrayList)deptMap.get(dept);
            System.out.println("\nDepartment: " + dept);
            System.out.println("Total Employees: " + list.size());
            double totalSalary = 0;
            for(int i = 0; i < list.size(); i++) {
                Employee e = (Employee)list.get(i);
                totalSalary += e.salary;
            }
            System.out.println("Total Salary Expense: $" + totalSalary);
        }
    }
}
