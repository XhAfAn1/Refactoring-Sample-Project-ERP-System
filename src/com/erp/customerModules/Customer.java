package com.erp.customerModules;

import java.util.*;

public class Customer {
    public int customerId;
    public String customerName;
    public String customerEmail;
    public String customerPhone;
    public String customerAddress;
    public double customerCreditLimit;
    public double customerCurrentBalance;
    public String customerType;
    public Date customerRegistrationDate;
    public boolean customerIsActive;
    public String customerContactPerson;
    public ArrayList customerOrderHistory;

    public Customer() {
        customerRegistrationDate = new Date();
        customerIsActive = true;
        customerCurrentBalance = 0;
        customerOrderHistory = new ArrayList();
    }

    public void print() {
        System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + customerName);
        System.out.println("Email: " + customerEmail);
        System.out.println("Phone: " + customerPhone);
        System.out.println("Credit Limit: $" + customerCreditLimit);
        System.out.println("Current Balance: $" + customerCurrentBalance);
        System.out.println("Active: " + customerIsActive);
    }

    public double getAvailableCredit() {
        return customerCreditLimit - customerCurrentBalance;
    }

    public boolean canPlaceOrder(double amount) {
        return getAvailableCredit() >= amount;
    }
}
