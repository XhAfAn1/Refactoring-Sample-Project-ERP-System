package com.erp.supplierModules;

import java.util.*;

public class Supplier {
    public int id;
    public String name;
    public String contact;
    public String phone;
    public String email;
    public String address;
    public String country;
    public double rating;
    public ArrayList products;
    public double totalPurchases;
    public String paymentTerms;
    public boolean isActive;

    public Supplier() {
        products = new ArrayList();
        totalPurchases = 0;
        isActive = true;
        rating = 5.0;
    }

    public void print() {
        System.out.println("Supplier ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Contact: " + contact);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Rating: " + rating);
        System.out.println("Total Purchases: $" + totalPurchases);
    }
}
