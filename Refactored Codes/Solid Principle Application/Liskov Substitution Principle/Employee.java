package com.erp;

import java.util.*;

public abstract class Employee {
    // Common fields for ALL employees
    protected int id;
    protected String name;
    protected String department;
    protected String email;
    protected String phone;
    protected Date hireDate;
    protected String address;
    protected int age;
    protected String position;
    protected boolean isActive;
    protected String manager;

    public Employee() {
        hireDate = new Date();
        isActive = true;
    }

    // Common methods with implementation
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

    // Abstract methods - subclasses MUST implement
    public abstract double calculateSalary();
    public abstract double calculateAnnualSalary();
    public abstract int getVacationDays();
    public abstract void giveRaise(double percentage);

    // Common getters/setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    

}