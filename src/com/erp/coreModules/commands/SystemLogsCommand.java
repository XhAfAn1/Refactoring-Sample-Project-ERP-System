package com.erp.coreModules.commands;

import com.erp.coreModules.SettingCommand;

public class SystemLogsCommand implements SettingCommand {
    public String getName() { return "System Logs"; }

    public void execute() {
        System.out.println("Showing recent system logs...");
    }
}