public interface CustomerCRUDManager {
    void addCustomer(Customer customer);
    Customer findCustomerById(int id);
    List<Customer> getAllCustomers();
    void updateCustomer(Customer customer);
    void deleteCustomer(int id);
}