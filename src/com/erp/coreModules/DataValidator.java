package com.erp.coreModules;

public class DataValidator {
    public static boolean flag = true;
    public static int counter = 0;
    public static String lastError = "";

    public boolean validateEmail(String email) {
        if(email == null) return false;
        if(email.length() == 0) return false;
        if(!email.contains("@")) {
            lastError = "Email must contain @";
            return false;
        }
        if(!email.contains(".")) {
            lastError = "Email must contain .";
            return false;
        }
        counter++;
        return true;
    }

    public boolean validatePhone(String phone) {
        if(phone == null) return false;
        if(phone.length() < 10) {
            lastError = "Phone must be at least 10 characters";
            return false;
        }
        counter++;
        return true;
    }

    public boolean validateSalary(double salary) {
        if(salary < 0) {
            lastError = "Salary cannot be negative";
            return false;
        }
        if(salary > 1000000) {
            lastError = "Salary too high";
            return false;
        }
        counter++;
        return true;
    }

    public boolean validatePrice(double price) {
        if(price <= 0) {
            lastError = "Price must be positive";
            return false;
        }
        counter++;
        return true;
    }

    public boolean validateQuantity(int qty) {
        if(qty < 0) {
            lastError = "Quantity cannot be negative";
            return false;
        }
        counter++;
        return true;
    }

    public void printValidationStats() {
        System.out.println("Total validations: " + counter);
        System.out.println("Last error: " + lastError);
    }

    public void reset() {
        counter = 0;
        lastError = "";
        flag = true;
    }
}
