import java.util.ArrayList;
import java.util.List;

public class CustomerSearchService implements CustomerSearchManager {
    private final CustomerCRUDManager customerService;
    
    public CustomerSearchService(CustomerCRUDManager customerService) {
        this.customerService = customerService;
    }
    
    @Override
    public List<Customer> searchCustomersByName(String name) {
        List<Customer> results = new ArrayList<>();
        List<Customer> allCustomers = customerService.getAllCustomers();
        
        for (Customer customer : allCustomers) {
            if (customer.name.toLowerCase().contains(name.toLowerCase())) {
                results.add(customer);
            }
        }
        return results;
    }
    
    @Override
    public List<Customer> searchCustomersByEmail(String email) {
        List<Customer> results = new ArrayList<>();
        List<Customer> allCustomers = customerService.getAllCustomers();
        
        for (Customer customer : allCustomers) {
            if (customer.email.toLowerCase().contains(email.toLowerCase())) {
                results.add(customer);
            }
        }
        return results;
    }
    
    @Override
    public List<Customer> searchCustomersByType(String type) {
        List<Customer> results = new ArrayList<>();
        List<Customer> allCustomers = customerService.getAllCustomers();
        
        for (Customer customer : allCustomers) {
            if (customer.type.equalsIgnoreCase(type)) {
                results.add(customer);
            }
        }
        return results;
    }
    
    @Override
    public List<Customer> getCustomersWithOutstandingBalance() {
        List<Customer> results = new ArrayList<>();
        List<Customer> allCustomers = customerService.getAllCustomers();
        
        for (Customer customer : allCustomers) {
            if (customer.currentBalance > 0) {
                results.add(customer);
            }
        }
        return results;
    }
}