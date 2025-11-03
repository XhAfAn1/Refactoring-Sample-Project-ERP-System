public interface CreditManager {
    void updateCreditLimit(int customerId, double creditLimit);
    double getAvailableCredit(int customerId);
    boolean validateCreditLimit(double amount, int customerId);
    double getCustomerBalance(int customerId);
}