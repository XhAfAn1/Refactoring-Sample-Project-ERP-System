package com.erp;

public class Calculator {
    public double temp1;
    public double temp2;
    public double result;
    public String operation;

    public double add(double a, double b) {
        temp1 = a;
        temp2 = b;
        result = a + b;
        operation = "ADD";
        return result;
    }

    public double subtract(double a, double b) {
        temp1 = a;
        temp2 = b;
        result = a - b;
        operation = "SUBTRACT";
        return result;
    }

    public double multiply(double a, double b) {
        temp1 = a;
        temp2 = b;
        result = a * b;
        operation = "MULTIPLY";
        return result;
    }

    public double divide(double a, double b) {
        temp1 = a;
        temp2 = b;
        if(b == 0) {
            System.out.println("Error: Division by zero!");
            return 0;
        }
        result = a / b;
        operation = "DIVIDE";
        return result;
    }

    public double percentage(double amount, double percent) {
        temp1 = amount;
        temp2 = percent;
        result = (amount * percent) / 100;
        operation = "PERCENTAGE";
        return result;
    }

    public double calculateDiscount(double price, double discountPercent) {
        double discount = percentage(price, discountPercent);
        return price - discount;
    }

    public double calculateTax(double amount, double taxRate) {
        return amount + percentage(amount, taxRate);
    }

    public void printLastOperation() {
        System.out.println("Operation: " + operation);
        System.out.println("Operand 1: " + temp1);
        System.out.println("Operand 2: " + temp2);
        System.out.println("Result: " + result);
    }
}
