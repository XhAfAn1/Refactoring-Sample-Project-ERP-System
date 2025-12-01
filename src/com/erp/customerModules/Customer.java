package com.erp.customerModules;

import java.util.*;

public class Customer {
    public int customer_id;
    public String customer_name;
    public String customer_email;
    public String customer_phone;
    public String customer_address;
    public double customer_creditLimit;
    public double customer_currentBalance;
    public String customer_type;
    public Date customer_registrationDate;
    public boolean customer_isActive;
    public String customer_contactPerson;
    public ArrayList customer_orderHistory;

    public Customer() {
        customer_registrationDate = new Date();
        customer_isActive = true;
        customer_currentBalance = 0;
        customer_orderHistory = new ArrayList();
    }

    public void print() {
        System.out.println("Customer ID: " + customer_id);
        System.out.println("Name: " + customer_name);
        System.out.println("Email: " + customer_email);
        System.out.println("Phone: " + customer_phone);
        System.out.println("Credit Limit: $" + customer_creditLimit);
        System.out.println("Current Balance: $" + customer_currentBalance);
        System.out.println("Active: " + customer_isActive);
    }

    public double getAvailableCredit() {
        return customer_creditLimit - customer_currentBalance;
    }

    public boolean canPlaceOrder(double amount) {
        return getAvailableCredit() >= amount;
    }
}
