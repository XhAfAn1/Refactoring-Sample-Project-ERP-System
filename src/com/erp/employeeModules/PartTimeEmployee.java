package com.erp.employeeModules;

public class PartTimeEmployee extends Employee {
    public double hourlyRate;
    public int hoursPerWeek;
    public int vacationDays = 5; // Part-time gets fewer days

    public PartTimeEmployee() {
        super();
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * hoursPerWeek * 4; // Monthly estimate
    }

    @Override
    public double calculateAnnualSalary() {
        return hourlyRate * hoursPerWeek * 52; // Annual calculation
    }

    @Override
    public int getVacationDays() {
        return vacationDays;
    }

    @Override
    public void giveRaise(double percentage) {
        hourlyRate = hourlyRate + (hourlyRate * percentage / 100);
        System.out.println("New hourly rate: $" + hourlyRate);
    }

    // PartTimeEmployee specific methods
    public void setHoursPerWeek(int hours) {
        this.hoursPerWeek = hours;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }
}