public interface CustomerSearchManager {
    List<Customer> searchCustomersByName(String name);
    List<Customer> searchCustomersByEmail(String email);
    List<Customer> searchCustomersByType(String type);
    List<Customer> getCustomersWithOutstandingBalance();
}