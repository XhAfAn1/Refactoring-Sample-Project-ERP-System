package com.erp.coreModules;

import com.erp.coreModules.commands.ChangeCompanyNameCommand;
import com.erp.coreModules.commands.ClearAllDataCommand;
import com.erp.coreModules.commands.DatabaseStatsCommand;
import com.erp.coreModules.commands.InitializeSampleDataCommand;
import com.erp.coreModules.commands.SystemBackupCommand;
import com.erp.coreModules.commands.SystemLogsCommand;
import com.erp.coreModules.commands.TaxSettingsCommand;
import com.erp.coreModules.commands.UserManagementCommand;
import com.erp.coreModules.commands.ViewSystemInfoCommand;
import java.util.*;

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

            if (ERPSystem.scanner == null) {
                System.out.println("Error: System scanner not initialized.");
                return;
            }

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