class ChangeCompanyNameCommand implements SettingCommand {
    public String getName() { return "Change Company Name"; }

    public void execute() {
        System.out.print("Enter new company name: ");
        String name = ERPSystem.scanner.nextLine();
        ERPSystem.companyName = name;
        System.out.println("Company name updated to: " + name);
    }
}