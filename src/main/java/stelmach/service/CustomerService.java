package stelmach.service;

import stelmach.entity.Customer;
import java.util.List;

public interface CustomerService {

    List<Customer> getCustomerList();
    void saveCustomer(Customer customer);
    Customer getCustomer(int id);
    void deleteCustomer(int id);
    List<Customer> searchCustomers(String searchName);
}
