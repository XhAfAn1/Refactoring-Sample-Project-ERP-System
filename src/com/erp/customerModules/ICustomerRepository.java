package com.erp.customerModules;

import java.util.List;

// THE ABSTRACTION
public interface ICustomerRepository {
    void add(Customer c);
    List<Customer> getAll();
    Customer findById(int id);
    void remove(Customer c);
}