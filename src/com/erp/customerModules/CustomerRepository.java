package com.erp.customerModules;

import java.util.*;

public class CustomerRepository implements ICustomerRepository {
    private final List<Customer> customers = new ArrayList<>();

    @Override
    public void add(Customer c) {
        customers.add(c);
    }

    @Override
    public List<Customer> getAll() {
        return new ArrayList<>(customers);
    }

    @Override
    public Customer findById(int id) {
        for (Customer c : customers) {
            if (c.customerId == id) return c;
        }
        return null;
    }

    @Override
    public void remove(Customer c) {
        customers.remove(c);
    }
}