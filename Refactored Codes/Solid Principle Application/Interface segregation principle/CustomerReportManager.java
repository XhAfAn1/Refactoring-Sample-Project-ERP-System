public interface CustomerReportManager {
    void generateBalanceReport();
    void generateCustomerActivityReport();
    void generateTopCustomersReport();
    Map<String, Double> getCustomerStatistics();
}