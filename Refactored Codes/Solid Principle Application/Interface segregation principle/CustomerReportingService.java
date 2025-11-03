import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerReportingService implements CustomerReportManager {
    private final CustomerCRUDManager customerService;
    private final CustomerRelationshipManager relationshipManager;
    
    public CustomerReportingService(CustomerCRUDManager customerService, 
                                   CustomerRelationshipManager relationshipManager) {
        this.customerService = customerService;
        this.relationshipManager = relationshipManager;
    }
    
    @Override
    public void generateBalanceReport() {
        List<Customer> customers = customerService.getAllCustomers();
        double totalOutstanding = 0;
        
        System.out.println("\n=== CUSTOMER BALANCE REPORT ===");
        for (Customer customer : customers) {
            if (customer.currentBalance > 0) {
                System.out.println("\nCustomer: " + customer.name);
                System.out.println("Outstanding Balance: $" + customer.currentBalance);
                System.out.println("Credit Limit: $" + customer.creditLimit);
                System.out.println("Available Credit: $" + customer.getAvailableCredit());
                totalOutstanding += customer.currentBalance;
            }
        }
        System.out.println("\nTotal Outstanding: $" + totalOutstanding);
    }
    
    @Override
    public void generateCustomerActivityReport() {
        List<Customer> customers = customerService.getAllCustomers();
        
        System.out.println("\n=== CUSTOMER ACTIVITY REPORT ===");
        for (Customer customer : customers) {
            int orderCount = relationshipManager.getCustomerOrderCount(customer.id);
            double lifetimeValue = relationshipManager.getCustomerLifetimeValue(customer.id);
            
            System.out.println("\nCustomer: " + customer.name);
            System.out.println("Total Orders: " + orderCount);
            System.out.println("Lifetime Value: $" + lifetimeValue);
            System.out.println("Active: " + customer.isActive);
        }
    }
    
    @Override
    public void generateTopCustomersReport() {
        List<Customer> customers = customerService.getAllCustomers();
        
        // Sort by lifetime value
        customers.sort((c1, c2) -> Double.compare(
            relationshipManager.getCustomerLifetimeValue(c2.id),
            relationshipManager.getCustomerLifetimeValue(c1.id)
        ));
        
        System.out.println("\n=== TOP CUSTOMERS BY REVENUE ===");
        for (int i = 0; i < Math.min(5, customers.size()); i++) {
            Customer customer = customers.get(i);
            double lifetimeValue = relationshipManager.getCustomerLifetimeValue(customer.id);
            System.out.println((i + 1) + ". " + customer.name + " - $" + lifetimeValue);
        }
    }
    
    @Override
    public Map<String, Double> getCustomerStatistics() {
        List<Customer> customers = customerService.getAllCustomers();
        Map<String, Double> stats = new HashMap<>();
        
        double totalCreditLimit = 0;
        double totalOutstanding = 0;
        int activeCustomers = 0;
        
        for (Customer customer : customers) {
            totalCreditLimit += customer.creditLimit;
            totalOutstanding += customer.currentBalance;
            if (customer.isActive) activeCustomers++;
        }
        
        stats.put("totalCreditLimit", totalCreditLimit);
        stats.put("totalOutstanding", totalOutstanding);
        stats.put("activeCustomers", (double) activeCustomers);
        stats.put("totalCustomers", (double) customers.size());
        
        return stats;
    }
}
