package com.erp.employeeModules;

public class FullTimeEmployee extends Employee {
    public double monthlySalary;
    public double bonus;
    public int vacationDays = 15; // Full-time gets 15 days

    public FullTimeEmployee() {
        super();
    }

    @Override
    public double calculateSalary() {
        return monthlySalary; // Fixed monthly salary
    }

    @Override
    public double calculateAnnualSalary() {
        return (monthlySalary * 12) + bonus;
    }

    @Override
    public int getVacationDays() {
        return vacationDays;
    }

    @Override
    public void giveRaise(double percentage) {
        monthlySalary = monthlySalary + (monthlySalary * percentage / 100);
        System.out.println("New monthly salary: $" + monthlySalary);
    }

    public void addBonus(double amount) {
        bonus += amount;
        System.out.println("Bonus added: $" + amount);
    }

    // FullTimeEmployee specific methods
    public void applyForLeave(int days) {
        if (days <= vacationDays) {
            vacationDays -= days;
            System.out.println("Leave approved. Remaining vacation days: " + vacationDays);
        } else {
            System.out.println("Insufficient vacation days!");
        }
    }
}