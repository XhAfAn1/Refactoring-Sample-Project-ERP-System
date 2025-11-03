public class CustomerCreditService implements CreditManager {
    private final CustomerCRUDManager customerService;
    
    public CustomerCreditService(CustomerCRUDManager customerService) {
        this.customerService = customerService;
    }
    
    @Override
    public void updateCreditLimit(int customerId, double creditLimit) {
        Customer customer = customerService.findCustomerById(customerId);
        if (customer != null) {
            customer.creditLimit = creditLimit;
            customerService.updateCustomer(customer);
        }
    }
    
    @Override
    public double getAvailableCredit(int customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        return customer != null ? customer.getAvailableCredit() : 0;
    }
    
    @Override
    public boolean validateCreditLimit(double amount, int customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        return customer != null && customer.canPlaceOrder(amount);
    }
    
    @Override
    public double getCustomerBalance(int customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        return customer != null ? customer.currentBalance : 0;
    }
}
