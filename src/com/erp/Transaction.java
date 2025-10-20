package com.erp;

import java.util.*;

public class Transaction {
    public int id;
    public String type;
    public double amount;
    public Date date;
    public String description;
    public String category;
    public String reference;
    public String paymentMethod;
    public int relatedEntityId;

    public Transaction() {
        date = new Date();
    }

    public void print() {
        System.out.println("Transaction ID: " + id);
        System.out.println("Type: " + type);
        System.out.println("Amount: $" + amount);
        System.out.println("Date: " + date);
        System.out.println("Description: " + description);
        System.out.println("Category: " + category);
    }
}
