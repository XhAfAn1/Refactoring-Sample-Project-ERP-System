package com.erp.coreModules.commands;

import com.erp.coreModules.DatabaseContext; 
import com.erp.coreModules.SettingCommand;

public class InitializeSampleDataCommand implements SettingCommand {
    public String getName() { return "Initialize Sample Data"; }

    public void execute() {
        
        DatabaseContext.getInstance().initializeData();
        System.out.println("Sample data initialized via DatabaseContext!");
    }
}