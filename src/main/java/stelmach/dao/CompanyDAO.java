package stelmach.dao;

import stelmach.entity.Company;
import stelmach.entity.Customer;

import java.util.List;

public interface CompanyDAO {
    void saveCompany(Company company);
    Company getCompany(int id);
    List<Company> getCompanyList();
    void deleteCompany(int id);
    List<Customer> getCustomers(int id);
    List<Company> searchCompanies(String searchName);
    List<Customer> searchCompanyCustomers(int id, String searchName);

}
