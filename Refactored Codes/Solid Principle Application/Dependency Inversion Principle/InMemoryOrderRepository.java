package com.erp;

import java.util.*;

public class InMemoryOrderRepository implements OrderRepository {
    private List<Order> orders;

    public InMemoryOrderRepository(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public List<Order> getOrdersByCustomerId(int customerId) {
        List<Order> result = new ArrayList<>();
        for (Order o : orders) {
            if (o.customerId == customerId) result.add(o);
        }
        return result;
    }
}