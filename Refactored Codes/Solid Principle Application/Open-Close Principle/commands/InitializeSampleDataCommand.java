class InitializeSampleDataCommand implements SettingCommand {
    public String getName() { return "Initialize Sample Data"; }

    public void execute() {
        ERPSystem.initializeData();
        System.out.println("Sample data initialized!");
    }
}