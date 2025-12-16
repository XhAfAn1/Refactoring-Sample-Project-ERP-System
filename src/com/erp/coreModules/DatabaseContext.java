package com.erp.coreModules;

import com.erp.customerModules.Customer;
import com.erp.employeeModules.FullTimeEmployee;
import com.erp.productModules.Product;
import com.erp.supplierModules.Supplier;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseContext {
    private static DatabaseContext instance;

    public ArrayList allEmployees;
    public ArrayList allProducts;
    public ArrayList allOrders;
    public ArrayList allCustomers;
    public ArrayList allSuppliers;
    public HashMap inventory;

    private DatabaseContext() {
        allEmployees = new ArrayList();
        allProducts = new ArrayList();
        allOrders = new ArrayList();
        allCustomers = new ArrayList();
        allSuppliers = new ArrayList();
        inventory = new HashMap();
    }

    public static synchronized DatabaseContext getInstance() {
        if (instance == null) {
            instance = new DatabaseContext();
        }
        return instance;
    }

    public void initializeData() {
        if (!allEmployees.isEmpty()) return; 

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

        Product p1 = new Product.Builder()
                .withId(1)
                .withName("Laptop")
                .withPrice(1200.0)
                .withCategory("Electronics")
                .build();
                
        allProducts.add(p1);
        inventory.put(1, 50);

        Product p2 = new Product.Builder()
                .withId(2)
                .withName("Mouse")
                .withPrice(25.0)
                .withCategory("Electronics")
                .build();
                
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