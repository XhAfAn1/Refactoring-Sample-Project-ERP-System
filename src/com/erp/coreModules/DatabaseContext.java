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
        e1.employeeId = 1;
        e1.employeeName = "John Doe";
        e1.employeeDepartment = "IT";
        e1.monthlySalary = 50000;
        e1.employeeEmail = "john@company.com";
        allEmployees.add(e1);

        FullTimeEmployee e2 = new FullTimeEmployee();
        e2.employeeId = 2;
        e2.employeeName = "Jane Smith";
        e2.employeeDepartment = "HR";
        e2.monthlySalary = 45000;
        e2.employeeEmail = "jane@company.com";
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
        c1.customerId = 1;
        c1.customerName = "ABC Corp";
        c1.customerEmail = "contact@abc.com";
        c1.customerPhone = "123-456-7890";
        c1.customerCreditLimit = 50000;
        allCustomers.add(c1);

        Supplier s1 = new Supplier();
        s1.supplierId = 1;
        s1.supplierName = "Tech Supplies Inc";
        s1.supplierContact = "supplier@tech.com";
        s1.supplierPhone = "987-654-3210";
        allSuppliers.add(s1);
    }
}