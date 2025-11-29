package com.erp.customerModules;

import java.util.*;


public class CustomerRepository {
    private final List<Customer> customers = new ArrayList<>();

    public void add(Customer c) {
        customers.add(c);
    }

    public List<Customer> getAll() {
        return new ArrayList<>(customers);
    }

    public Customer findById(int id) {
        for (Customer c : customers) {
            if (c.id == id) return c;
        }
        return null;
    }

    public void remove(Customer c) {
        customers.remove(c);
    }
}
