package com.erp;

import java.util.*;

public class InMemoryCustomerRepository implements CustomerRepository {
    private List<Customer> customers;

    public InMemoryCustomerRepository(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public void addCustomer(Customer c) {
        customers.add(c);
    }

    @Override
    public Customer getCustomerById(int id) {
        for (Customer c : customers) {
            if (c.id == id) return c;
        }
        return null;
    }

    @Override
    public void updateCustomer(Customer c) {
        // In memory, object reference update is enough
    }

    @Override
    public void deleteCustomer(int id) {
        customers.removeIf(c -> c.id == id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customers;
    }
}