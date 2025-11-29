package com.erp.coreModules.commands;

import com.erp.coreModules.ERPSystem;
import com.erp.coreModules.SettingCommand;

public class InitializeSampleDataCommand implements SettingCommand {
    public String getName() { return "Initialize Sample Data"; }

    public void execute() {
        ERPSystem.initializeData();
        System.out.println("Sample data initialized!");
    }
}