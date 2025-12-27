package com.erp.financeModules;

import com.erp.enums.TransactionType;
import java.util.*;

public class Transaction {
    public int transactionId;
    public TransactionType transactionType; 
    public double transactionAmount;
    public Date transactionDate;
    public String transactionDescription;
    public String transactionCategory;
    public String transactioneRference;
    public String paymentMethod;
    public int relatedEntityId;

    public Transaction() {
        transactionDate = new Date();
    }

    public void print() {
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Type: " + transactionType);
        System.out.println("Amount: $" + transactionAmount);
        System.out.println("Date: " + transactionDate);
        System.out.println("Description: " + transactionDescription);
        System.out.println("Category: " + transactionCategory);
    }
}