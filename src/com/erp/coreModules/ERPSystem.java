package com.erp.coreModules;

import com.erp.customerModules.Customer;
import com.erp.customerModules.CustomerConsoleUI;
import com.erp.customerModules.CustomerRepository;
import com.erp.customerModules.CustomerService;
import com.erp.customerModules.OrderRepository;
import com.erp.customerModules.OrderService;
import com.erp.employeeModules.EmployeeManager;
import com.erp.employeeModules.FullTimeEmployee;
import com.erp.financeModules.FinanceManager;
import com.erp.productModules.InventoryConsoleUI;
import com.erp.productModules.Product;
import com.erp.reportModules.ReportManager;
import com.erp.salesModules.SalesManager;
import com.erp.supplierModules.Supplier;
import com.erp.supplierModules.SupplierManager;
import java.util.*;

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

    static CustomerRepository customerRepo = new CustomerRepository();

    public static OrderService getOrderService() {
        return orderService;
    }
    public static void setOrderService(OrderService orderService) {
        ERPSystem.orderService = orderService;
    }
    static OrderRepository orderRepo = new OrderRepository();
    static CustomerService customerService = new CustomerService(customerRepo);
    static OrderService orderService = new OrderService(orderRepo);


    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("Welcome to " + companyName + " ERP System");
        System.out.println("========================================");

        initializeData();

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
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                EmployeeManager em = new EmployeeManager();
                em.showMenu();
            } else if (choice == 2) {
                InventoryConsoleUI im = new InventoryConsoleUI();
                im.showMenu();
            } else if (choice == 3) {
                SalesManager sm = new SalesManager();
                sm.showMenu();
            } else if (choice == 4) {
                CustomerConsoleUI cm = new CustomerConsoleUI(customerService, orderService, scanner);
                cm.showMenu();
            } else if (choice == 5) {
                SupplierManager supm = new SupplierManager();
                supm.showMenu();
            } else if (choice == 6) {
                FinanceManager fm = new FinanceManager();
                fm.showMenu();
            } else if (choice == 7) {
                ReportManager rm = new ReportManager();
                rm.showMenu();
            } else if (choice == 8) {
                SystemSettings ss = new SystemSettings();
                ss.showMenu();
            } else if (choice == 9) {
                System.out.println("Exiting system...");
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    public static void initializeData() {
        FullTimeEmployee e1 = new FullTimeEmployee();
        e1.employee_id = 1;
        e1.employee_name = "John Doe";
        e1.employee_department = "IT";
        e1.monthlySalary = 50000;
        e1.employee_email = "john@company.com";
        allEmployees.add(e1);

        FullTimeEmployee e2 = new FullTimeEmployee();
        e2.employee_id = 2;
        e2.employee_name = "Jane Smith";
        e2.employee_department = "HR";
        e2.monthlySalary = 45000;
        e2.employee_email = "jane@company.com";
        allEmployees.add(e2);

        Product p1 = new Product();
        p1.product_id = 1;
        p1.product_name = "Laptop";
        p1.product_price = 1200.0;
        p1.product_category = "Electronics";
        allProducts.add(p1);
        inventory.put(1, 50);

        Product p2 = new Product();
        p2.product_id = 2;
        p2.product_name = "Mouse";
        p2.product_price = 25.0;
        p2.product_category = "Electronics";
        allProducts.add(p2);
        inventory.put(2, 200);

        Customer c1 = new Customer();
        c1.customer_id = 1;
        c1.customer_name = "ABC Corp";
        c1.customer_email = "contact@abc.com";
        c1.customer_phone = "123-456-7890";
        c1.customer_creditLimit = 50000;
        allCustomers.add(c1);

        Supplier s1 = new Supplier();
        s1.supplier_id = 1;
        s1.supplier_name = "Tech Supplies Inc";
        s1.supplier_contact = "supplier@tech.com";
        s1.supplier_phone = "987-654-3210";
        allSuppliers.add(s1);
    }
}
