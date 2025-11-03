package com.erp.repository;

import com.erp.Order;
import java.util.*;

public class OrderRepository {
    private final List<Order> orders = new ArrayList<>();

    public void add(Order o) {
        orders.add(o);
    }

    public List<Order> findByCustomerId(int customerId) {
        List<Order> result = new ArrayList<>();
        for (Order o : orders) {
            if (o.customerId == customerId) {
                result.add(o);
            }
        }
        return result;
    }

    public List<Order> getAll() {
        return new ArrayList<>(orders);
    }
}
