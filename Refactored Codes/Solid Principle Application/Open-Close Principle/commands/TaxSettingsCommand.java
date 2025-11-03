class TaxSettingsCommand implements SettingCommand {
    public String getName() { return "Tax Settings"; }

    public void execute() {
        System.out.println("\n=== TAX SETTINGS ===");
        System.out.print("Enter default tax rate (%): ");
        double rate = ERPSystem.scanner.nextDouble();
        ERPSystem.scanner.nextLine();
        System.out.println("Tax rate set to: " + rate + "%");
        System.out.print("Apply tax automatically? (yes/no): ");
        String auto = ERPSystem.scanner.nextLine();
        if(auto.equalsIgnoreCase("yes")) {
            System.out.println("Automatic tax calculation enabled");
        } else {
            System.out.println("Automatic tax calculation disabled");
        }
    }
}