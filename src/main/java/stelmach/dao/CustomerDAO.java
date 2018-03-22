package stelmach.dao;

import stelmach.entity.Customer;
import java.util.List;

public interface CustomerDAO {
    void saveCustomer(Customer customer);
    Customer getCustomer(int id);
    List<Customer> getCustomerList();
    void deleteCustomer(int id);
    List<Customer> searchCustomers(String searchName);
}
