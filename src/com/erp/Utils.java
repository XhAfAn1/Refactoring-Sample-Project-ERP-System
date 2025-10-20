package com.erp;

import java.util.*;
import java.text.*;

public class Utils {
    public static String temp = "";
    public static int mode = 0;

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        temp = sdf.format(date);
        return temp;
    }

    public static String formatCurrency(double amount) {
        temp = "$" + String.format("%.2f", amount);
        return temp;
    }

    public static String toUpperCase(String str) {
        if(str == null) return "";
        temp = str.toUpperCase();
        return temp;
    }

    public static String toLowerCase(String str) {
        if(str == null) return "";
        temp = str.toLowerCase();
        return temp;
    }

    public static String trim(String str) {
        if(str == null) return "";
        temp = str.trim();
        return temp;
    }

    public static boolean isEmpty(String str) {
        if(str == null) return true;
        if(str.length() == 0) return true;
        if(str.trim().length() == 0) return true;
        return false;
    }

    public static int generateId() {
        Random random = new Random();
        int id = random.nextInt(10000) + 1;
        return id;
    }

    public static double roundToTwoDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public static void printSeparator() {
        System.out.println("========================================");
    }

    public static void printHeader(String title) {
        printSeparator();
        System.out.println(title);
        printSeparator();
    }

    public static String generateReference() {
        return "REF-" + System.currentTimeMillis();
    }

    public static boolean confirmAction(String message) {
        System.out.print(message + " (yes/no): ");
        String response = ERPSystem.scanner.nextLine();
        return response.equalsIgnoreCase("yes");
    }
}
