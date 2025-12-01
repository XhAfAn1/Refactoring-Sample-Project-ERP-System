package com.erp.supplierModules;

import java.util.*;

public class Supplier {
    public int supplier_id;
    public String supplier_name;
    public String supplier_contact;
    public String supplier_phone;
    public String supplier_email;
    public String supplier_address;
    public String supplier_country;
    public double supplier_rating;
    public ArrayList supplier_products;
    public double supplier_totalPurchases;
    public String supplier_paymentTerms;
    public boolean supplier_isActive;

    public Supplier() {
        supplier_products = new ArrayList();
        supplier_totalPurchases = 0;
        supplier_isActive = true;
        supplier_rating = 5.0;
    }

    public void print() {
        System.out.println("Supplier ID: " + supplier_id);
        System.out.println("Name: " + supplier_name);
        System.out.println("Contact: " + supplier_contact);
        System.out.println("Phone: " + supplier_phone);
        System.out.println("Email: " + supplier_email);
        System.out.println("Rating: " + supplier_rating);
        System.out.println("Total Purchases: $" + supplier_totalPurchases);
    }
}
