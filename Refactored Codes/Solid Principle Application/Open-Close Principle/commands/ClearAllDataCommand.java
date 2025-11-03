class ClearAllDataCommand implements SettingCommand {
    public String getName() { return "Clear All Data"; }

    public void execute() {
        System.out.print("Are you sure you want to clear all data? (yes/no): ");
        String confirm = ERPSystem.scanner.nextLine();

        if(confirm.equalsIgnoreCase("yes")) {
            ERPSystem.allEmployees.clear();
            ERPSystem.allProducts.clear();
            ERPSystem.allOrders.clear();
            ERPSystem.allCustomers.clear();
            ERPSystem.allSuppliers.clear();
            ERPSystem.inventory.clear();
            ERPSystem.totalRevenue = 0;
            ERPSystem.totalExpenses = 0;
            System.out.println("All data cleared!");
        } else {
            System.out.println("Operation cancelled.");
        }
    }
}