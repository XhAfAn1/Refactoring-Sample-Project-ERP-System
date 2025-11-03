package com.erp;

import java.util.*;
import java.io.*;

public class ERPSystem {
    public static String companyName = "Nebuchadnezzar";
    public static double totalRevenue = 0;
    public static double totalExpenses = 0;
    public static ArrayList allEmployees = new ArrayList();
    public static ArrayList allProducts = new ArrayList();
    public static ArrayList allOrders = new ArrayList();
    public static ArrayList allCustomers = new ArrayList();
    public static ArrayList allSuppliers = new ArrayList();
    public static HashMap inventory = new HashMap();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("Welcome to " + companyName + " ERP System");
        System.out.println("========================================");

        initializeData();

        while(true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Employee Management");
            System.out.println("2. Inventory Management");
            System.out.println("3. Sales & Orders");
            System.out.println("4. Customer Management");
            System.out.println("5. Supplier Management");
            System.out.println("6. Finance & Accounting");
            System.out.println("7. Reports & Analytics");
            System.out.println("8. System Settings");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if(choice == 1) {
                EmployeeManager em = new EmployeeManager();
                em.showMenu();
            } else if(choice == 2) {
                InventoryManager im = new InventoryManager();
                im.showMenu();
            } else if(choice == 3) {
                SalesManager sm = new SalesManager();
                sm.showMenu();
            } else if(choice == 4) {
                CustomerManager cm = new CustomerManager();
                cm.showMenu();
            } else if(choice == 5) {
                SupplierManager supm = new SupplierManager();
                supm.showMenu();
            } else if(choice == 6) {
                FinanceManager fm = new FinanceManager();
                fm.showMenu();
            } else if(choice == 7) {
                ReportManager rm = new ReportManager();
                rm.showMenu();
            } else if(choice == 8) {
                SystemSettings ss = new SystemSettings();
                ss.showMenu();
            } else if(choice == 9) {
                System.out.println("Exiting system...");
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    public static void initializeData() {
        Employee e1 = new Employee();
        e1.id = 1;
        e1.name = "John Doe";
        e1.department = "IT";
        e1.salary = 50000;
        e1.email = "john@company.com";
        allEmployees.add(e1);

        Employee e2 = new Employee();
        e2.id = 2;
        e2.name = "Jane Smith";
        e2.department = "HR";
        e2.salary = 45000;
        e2.email = "jane@company.com";
        allEmployees.add(e2);

        Product p1 = new Product();
        p1.id = 1;
        p1.name = "Laptop";
        p1.price = 1200.0;
        p1.category = "Electronics";
        allProducts.add(p1);
        inventory.put(1, 50);

        Product p2 = new Product();
        p2.id = 2;
        p2.name = "Mouse";
        p2.price = 25.0;
        p2.category = "Electronics";
        allProducts.add(p2);
        inventory.put(2, 200);

        Customer c1 = new Customer();
        c1.id = 1;
        c1.name = "ABC Corp";
        c1.email = "contact@abc.com";
        c1.phone = "123-456-7890";
        c1.creditLimit = 50000;
        allCustomers.add(c1);

        Supplier s1 = new Supplier();
        s1.id = 1;
        s1.name = "Tech Supplies Inc";
        s1.contact = "supplier@tech.com";
        s1.phone = "987-654-3210";
        allSuppliers.add(s1);
    }
}
