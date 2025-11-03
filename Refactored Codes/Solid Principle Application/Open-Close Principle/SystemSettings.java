package com.erp;

import java.util.*;
import com.erp.commands.*;

public class SystemSettings {
    private List<SettingCommand> commands = new ArrayList<>();

    public SystemSettings() {
        commands.add(new ChangeCompanyNameCommand());
        commands.add(new ViewSystemInfoCommand());
        commands.add(new DatabaseStatsCommand());
        commands.add(new ClearAllDataCommand());
        commands.add(new InitializeSampleDataCommand());
        commands.add(new SystemBackupCommand());
        commands.add(new UserManagementCommand());
        commands.add(new TaxSettingsCommand());
        
        // New commands can be added here without modifying existing code
        commands.add(new SystemLogsCommand());
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== SYSTEM SETTINGS ===");
            for (int i = 0; i < commands.size(); i++) {
                System.out.println((i + 1) + ". " + commands.get(i).getName());
            }
            System.out.println((commands.size() + 1) + ". Back");
            System.out.print("Enter choice: ");

            int choice = ERPSystem.scanner.nextInt();
            ERPSystem.scanner.nextLine();

            if (choice == commands.size() + 1) break;
            if (choice >= 1 && choice <= commands.size()) {
                commands.get(choice - 1).execute();
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }
}
