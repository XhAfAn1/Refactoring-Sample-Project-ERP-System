package com.erp.employeeModules;

import java.util.*;

public abstract class Employee {
    public int id;
    public String name;
    public String department;
    public String email;
    public String phone;
    public Date hireDate;
    public String address;
    public int age;
    public String position;
    public boolean isActive;
    public String manager;

    public Employee() {
        hireDate = new Date();
        isActive = true;
    }

    public void print() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Position: " + position);
        System.out.println("Hire Date: " + hireDate);
        System.out.println("Active: " + isActive);
    }

    public abstract double calculateSalary();
    public abstract double calculateAnnualSalary();
    public abstract int getVacationDays();
    public abstract void giveRaise(double percentage);

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    

}