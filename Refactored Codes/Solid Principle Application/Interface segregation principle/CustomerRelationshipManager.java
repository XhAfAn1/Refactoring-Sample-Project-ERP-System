public interface CustomerRelationshipManager {
    List<Order> getCustomerOrders(int customerId);
    int getCustomerOrderCount(int customerId);
    double getCustomerLifetimeValue(int customerId);
    boolean hasOutstandingBalance(int customerId);
}