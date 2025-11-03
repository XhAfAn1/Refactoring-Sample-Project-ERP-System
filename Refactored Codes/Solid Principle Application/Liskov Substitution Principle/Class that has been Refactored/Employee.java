package com.erp;

import java.util.*;

public class Employee {
    public int id;
    public String name;
    public String department;
    public double salary;
    public String email;
    public String phone;
    public Date hireDate;
    public String address;
    public int age;
    public String position;
    public double bonus;
    public int vacationDays;
    public boolean isActive;
    public String manager;

    public Employee() {
        hireDate = new Date();
        isActive = true;
        vacationDays = 15;
        bonus = 0;
    }

    public void print() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Salary: $" + salary);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Position: " + position);
        System.out.println("Hire Date: " + hireDate);
        System.out.println("Active: " + isActive);
    }

    public double calculateAnnualSalary() {
        return salary * 12 + bonus;
    }

    public void giveRaise(double percentage) {
        salary = salary + (salary * percentage / 100);
        System.out.println("New salary: $" + salary);
    }

    public void addBonus(double amount) {
        bonus += amount;
        System.out.println("Bonus added: $" + amount);
    }
}
