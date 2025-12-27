package com.erp.employeeModules;

import java.util.*;

public abstract class Employee {
    public int employeeId;
    public String employeeName;
    public String employeeDepartment;
    public String employeeEmail;
    public String employeePhone;
    public Date employeeHireDate;
    public String employeeAddress;
    public int employeeAge;
    public String employeePosition;
    public boolean employeeIsActive;
    public String employeeManager;

    public Employee() {
        employeeHireDate = new Date();
        employeeIsActive = true;
    }

    public void print() {
        System.out.println("ID: " + employeeId);
        System.out.println("Name: " + employeeName);
        System.out.println("Department: " + employeeDepartment);
        System.out.println("Email: " + employeeEmail);
        System.out.println("Phone: " + employeePhone);
        System.out.println("Position: " + employeePosition);
        System.out.println("Hire Date: " + employeeHireDate);
        System.out.println("Active: " + employeeIsActive);
    }

    public abstract double calculateSalary();
    public abstract double calculateAnnualSalary();
    public abstract int getVacationDays();
    public abstract void giveRaise(double percentage);

    public int getId() { return employeeId; }
    public void setId(int id) { this.employeeId = id; }
    
    public String getName() { return employeeName; }
    public void setName(String name) { this.employeeName = name; }
    

}