public interface CustomerRepository {
    void addCustomer(Customer c);
    Customer getCustomerById(int id);
    void updateCustomer(Customer c);
    void deleteCustomer(int id);
    List<Customer> getAllCustomers();
}

public interface OrderRepository {
    List<Order> getOrdersByCustomerId(int customerId);
}