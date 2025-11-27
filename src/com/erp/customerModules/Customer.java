package com.erp.customerModules;

import java.util.*;

public class Customer {
    public int id;
    public String name;
    public String email;
    public String phone;
    public String address;
    public double creditLimit;
    public double currentBalance;
    public String type;
    public Date registrationDate;
    public boolean isActive;
    public String contactPerson;
    public ArrayList orderHistory;

    public Customer() {
        registrationDate = new Date();
        isActive = true;
        currentBalance = 0;
        orderHistory = new ArrayList();
    }

    public void print() {
        System.out.println("Customer ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Credit Limit: $" + creditLimit);
        System.out.println("Current Balance: $" + currentBalance);
        System.out.println("Active: " + isActive);
    }

    public double getAvailableCredit() {
        return creditLimit - currentBalance;
    }

    public boolean canPlaceOrder(double amount) {
        return getAvailableCredit() >= amount;
    }
}
