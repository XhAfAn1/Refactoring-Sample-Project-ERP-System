package com.erp.service;

import com.erp.Order;
import com.erp.repository.OrderRepository;
import java.util.List;

public class OrderService {
    private final OrderRepository repo;

    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    public List<Order> getOrdersForCustomer(int customerId) {
        return repo.findByCustomerId(customerId);
    }
}
