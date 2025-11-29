package com.erp.customerModules;

import java.util.List;

import com.erp.salesModules.Order;

public class OrderService {
    private final OrderRepository repo;

    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    public List<Order> getOrdersForCustomer(int customerId) {
        return repo.findByCustomerId(customerId);
    }
}
