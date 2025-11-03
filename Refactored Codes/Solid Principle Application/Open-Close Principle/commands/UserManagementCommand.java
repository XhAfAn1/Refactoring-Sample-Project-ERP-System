class UserManagementCommand implements SettingCommand {
    public String getName() { return "User Management"; }

    public void execute() {
        System.out.println("\n=== USER MANAGEMENT ===");
        System.out.println("1. Add User");
        System.out.println("2. View Users");
        System.out.println("3. Change Password");
        System.out.println("4. User Permissions");
        System.out.print("Enter choice: ");
        int choice = ERPSystem.scanner.nextInt();
        ERPSystem.scanner.nextLine();

        if(choice == 1) {
            System.out.print("Enter username: ");
            String username = ERPSystem.scanner.nextLine();
            System.out.print("Enter password: ");
            String password = ERPSystem.scanner.nextLine();
            System.out.println("User created: " + username);
        } else if(choice == 2) {
            System.out.println("\n=== SYSTEM USERS ===");
            System.out.println("1. admin (Administrator)");
            System.out.println("2. manager (Manager)");
            System.out.println("3. user (Regular User)");
        } else if(choice == 3) {
            System.out.print("Enter username: ");
            String username = ERPSystem.scanner.nextLine();
            System.out.print("Enter new password: ");
            String password = ERPSystem.scanner.nextLine();
            System.out.println("Password changed for: " + username);
        } else if(choice == 4) {
            System.out.println("\n=== USER PERMISSIONS ===");
            System.out.println("admin: Full Access");
            System.out.println("manager: Read/Write");
            System.out.println("user: Read Only");
        }
    }
}