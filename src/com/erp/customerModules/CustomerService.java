package com.erp.customerModules;

import java.util.List;

public class CustomerService{
    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public void addCustomer(Customer c) {
        repo.add(c);
    }

    public List<Customer> getAllCustomers() {
        return repo.getAll();
    }

    public Customer findCustomer(int id) {
        return repo.findById(id);
    }

    public void updateCustomerContact(int id, String email, String phone) {
        Customer c = repo.findById(id);
        if (c != null) {
            c.email = email;
            c.phone = phone;
        }
    }

    public void updateCreditLimit(int id, double limit) {
        Customer c = repo.findById(id);
        if (c != null) {
            c.creditLimit = limit;
        }
    }

    public double calculateTotalOutstanding() {
        double total = 0;
        for (Customer c : repo.getAll()) {
            total += c.currentBalance;
        }
        return total;
    }

    boolean deleteCustomer(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
