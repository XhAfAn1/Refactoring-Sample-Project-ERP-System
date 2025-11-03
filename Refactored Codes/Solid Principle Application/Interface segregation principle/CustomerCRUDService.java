import java.util.ArrayList;
import java.util.List;

public class CustomerCRUDService implements CustomerService {
    private final List<Customer> customers;
    
    public CustomerCRUDService(List<Customer> customerList) {
        this.customers = customerList;
    }
    
    @Override
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
    
    @Override
    public Customer findCustomerById(int id) {
        for (Customer customer : customers) {
            if (customer.id == id) {
                return customer;
            }
        }
        return null;
    }
    
    @Override
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }
    
    @Override
    public void updateCustomer(Customer updatedCustomer) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).id == updatedCustomer.id) {
                customers.set(i, updatedCustomer);
                return;
            }
        }
    }
    
    @Override
    public void deleteCustomer(int id) {
        customers.removeIf(customer -> customer.id == id);
    }
}