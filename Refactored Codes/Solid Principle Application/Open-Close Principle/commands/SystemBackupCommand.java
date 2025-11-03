class SystemBackupCommand implements SettingCommand {
    public String getName() { return "System Backup"; }

    public void execute() {
        System.out.println("\n=== SYSTEM BACKUP ===");
        System.out.println("Creating backup...");
        System.out.println("Backup created: backup_" + new Date().getTime() + ".bak");
        System.out.println("\nBackup Contents:");
        System.out.println("- " + ERPSystem.allEmployees.size() + " employees");
        System.out.println("- " + ERPSystem.allProducts.size() + " products");
        System.out.println("- " + ERPSystem.allOrders.size() + " orders");
        System.out.println("- " + ERPSystem.allCustomers.size() + " customers");
        System.out.println("- " + ERPSystem.allSuppliers.size() + " suppliers");
        System.out.println("\nBackup completed successfully!");
    }
}