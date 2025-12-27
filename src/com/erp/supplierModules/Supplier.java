package com.erp.supplierModules;

import java.util.*;

public class Supplier {
    public int supplierId;
    public String supplierName;
    public String supplierContact;
    public String supplierPhone;
    public String supplierEmail;
    public String supplierAdress;
    public String supplierCountry;
    public double supplierRating;
    public ArrayList supplierProducts;
    public double supplierTotalPurchases;
    public String supplierPaymentTerms;
    public boolean supplierIsActive;

    public Supplier() {
        supplierProducts = new ArrayList();
        supplierTotalPurchases = 0;
        supplierIsActive = true;
        supplierRating = 5.0;
    }

    public void updateContactInfo(String phone, String email, double rating) {
        this.supplierPhone = phone;
        this.supplierEmail = email;
        this.supplierRating = rating;
    }

    public void print() {
        System.out.println("Supplier ID: " + supplierId);
        System.out.println("Name: " + supplierName);
        System.out.println("Contact: " + supplierContact);
        System.out.println("Phone: " + supplierPhone);
        System.out.println("Email: " + supplierEmail);
        System.out.println("Rating: " + supplierRating);
        System.out.println("Total Purchases: $" + supplierTotalPurchases);
    }
}
