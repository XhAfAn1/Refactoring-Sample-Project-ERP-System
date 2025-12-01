package com.erp.employeeModules;

import java.util.*;

public abstract class Employee {
    public int employee_id;
    public String employee_name;
    public String employee_department;
    public String employee_email;
    public String employee_phone;
    public Date employee_hireDate;
    public String employee_address;
    public int employee_age;
    public String employee_position;
    public boolean employee_isActive;
    public String employee_manager;

    public Employee() {
        employee_hireDate = new Date();
        employee_isActive = true;
    }

    public void print() {
        System.out.println("ID: " + employee_id);
        System.out.println("Name: " + employee_name);
        System.out.println("Department: " + employee_department);
        System.out.println("Email: " + employee_email);
        System.out.println("Phone: " + employee_phone);
        System.out.println("Position: " + employee_position);
        System.out.println("Hire Date: " + employee_hireDate);
        System.out.println("Active: " + employee_isActive);
    }

    public abstract double calculateSalary();
    public abstract double calculateAnnualSalary();
    public abstract int getVacationDays();
    public abstract void giveRaise(double percentage);

    public int getId() { return employee_id; }
    public void setId(int id) { this.employee_id = id; }
    
    public String getName() { return employee_name; }
    public void setName(String name) { this.employee_name = name; }
    

}