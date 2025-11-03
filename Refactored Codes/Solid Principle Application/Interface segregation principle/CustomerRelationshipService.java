import java.util.ArrayList;
import java.util.List;

public class CustomerRelationshipService implements CustomerRelationshipManager {
    private final CustomerCRUDManager customerService;
    private final List<Order> orders;
    
    public CustomerRelationshipService(CustomerCRUDManager customerService, List<Order> orderList) {
        this.customerService = customerService;
        this.orders = orderList;
    }
    
    @Override
    public List<Order> getCustomerOrders(int customerId) {
        List<Order> customerOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.customerId == customerId) {
                customerOrders.add(order);
            }
        }
        return customerOrders;
    }
    
    @Override
    public int getCustomerOrderCount(int customerId) {
        return getCustomerOrders(customerId).size();
    }
    
    @Override
    public double getCustomerLifetimeValue(int customerId) {
        List<Order> customerOrders = getCustomerOrders(customerId);
        double totalValue = 0;
        for (Order order : customerOrders) {
            totalValue += order.totalAmount;
        }
        return totalValue;
    }
    
    @Override
    public boolean hasOutstandingBalance(int customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        return customer != null && customer.currentBalance > 0;
    }
}