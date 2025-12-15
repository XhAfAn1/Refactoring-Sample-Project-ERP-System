package com.erp.coreModules.commands;

import com.erp.coreModules.DatabaseContext; 
import com.erp.coreModules.SettingCommand;

public class InitializeSampleDataCommand implements SettingCommand {
    public String getName() { return "Initialize Sample Data"; }

    public void execute() {
        // REFACTORED: Call initialization on the Singleton
        DatabaseContext.getInstance().initializeData();
        System.out.println("Sample data initialized via DatabaseContext!");
    }
}