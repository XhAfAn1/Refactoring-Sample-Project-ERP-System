package com.erp.coreModules;

import com.erp.customerModules.CustomerConsoleUI;
import com.erp.customerModules.CustomerPortalFacade;
import com.erp.customerModules.CustomerRepository;
import com.erp.customerModules.CustomerService;
import com.erp.customerModules.OrderRepository;
import com.erp.customerModules.OrderService;
import com.erp.employeeModules.EmployeeManager;
import com.erp.financeModules.FinanceManager;
import com.erp.productModules.InventoryConsoleUI;
import com.erp.reportModules.ReportManager;
import com.erp.salesModules.SalesManager;
import com.erp.supplierModules.SupplierManager;
import java.util.*;

public class ERPSystem {

    public static String companyName = "Nebuchadnezzar";
    public static double totalRevenue = 0;
    public static double totalExpenses = 0;
    public static Scanner scanner = new Scanner(System.in);

    public static ArrayList allEmployees = DatabaseContext.getInstance().allEmployees;
    public static ArrayList allProducts = DatabaseContext.getInstance().allProducts;
    public static ArrayList allOrders = DatabaseContext.getInstance().allOrders;
    public static ArrayList allCustomers = DatabaseContext.getInstance().allCustomers;
    public static ArrayList allSuppliers = DatabaseContext.getInstance().allSuppliers;
    public static HashMap inventory = DatabaseContext.getInstance().inventory;

    static CustomerRepository customerRepo = new CustomerRepository();
    static OrderRepository orderRepo = new OrderRepository();
    static CustomerService customerService = new CustomerService(customerRepo);
    static OrderService orderService = new OrderService(orderRepo);

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("Welcome to " + companyName + " ERP System");
        System.out.println("========================================");

        
        DatabaseContext.getInstance().initializeData();

        while (true) {
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
            System.out.println("10. Customer Portal"); 
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) new EmployeeManager().showMenu();
            else if (choice == 2) new InventoryConsoleUI().showMenu();
            else if (choice == 3) new SalesManager().showMenu();
            else if (choice == 4) new CustomerConsoleUI(customerService, orderService, scanner).showMenu();
            else if (choice == 5) new SupplierManager().showMenu();
            else if (choice == 6) new FinanceManager().showMenu();
            else if (choice == 7) new ReportManager().showMenu();
            else if (choice == 8) new SystemSettings().showMenu();
            else if (choice == 9) {
                System.out.println("Exiting system...");
                break;
            } 
           
            else if (choice == 10) {
                new CustomerPortalFacade().showPortal();
            }
            else {
                System.out.println("Invalid choice!");
            }
        }
    }
}