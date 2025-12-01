package com.erp.employeeModules;

public class PartTimeEmployee extends Employee {
    public double hourlyRate;
    public int hoursPerWeek;
    public int vacationDays = 5; 

    public PartTimeEmployee() {
        super();
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * hoursPerWeek * 4; 
    }

    @Override
    public double calculateAnnualSalary() {
        return hourlyRate * hoursPerWeek * 52; 
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

    public void setHoursPerWeek(int hours) {
        this.hoursPerWeek = hours;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }
}