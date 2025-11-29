package com.erp.customerModules;

import java.util.*;

// THE DETAILS (Depend on Abstraction)
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
            if (c.id == id) return c;
        }
        return null;
    }

    @Override
    public void remove(Customer c) {
        customers.remove(c);
    }
}