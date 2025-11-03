class ViewSystemInfoCommand implements SettingCommand {
    public String getName() { return "View System Info"; }

    public void execute() {
        System.out.println("\n=== SYSTEM INFORMATION ===");
        System.out.println("Company: " + ERPSystem.companyName);
        System.out.println("System Version: 1.0");
        System.out.println("Database: In-Memory");
        System.out.println("Current Date: " + new Date());
        System.out.println("\n--- Statistics ---");
        System.out.println("Total Employees: " + ERPSystem.allEmployees.size());
        System.out.println("Total Products: " + ERPSystem.allProducts.size());
        System.out.println("Total Orders: " + ERPSystem.allOrders.size());
        System.out.println("Total Customers: " + ERPSystem.allCustomers.size());
        System.out.println("Total Suppliers: " + ERPSystem.allSuppliers.size());
    }
}