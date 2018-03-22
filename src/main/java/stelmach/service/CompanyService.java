package stelmach.service;

import stelmach.entity.Company;
import stelmach.entity.Customer;

import java.util.List;

public interface CompanyService {

    List<Company> getCompanyList();
    void saveCompany(Company company);
    Company getCompany(int id);
    void deleteCompany(int id);
    List<Customer> getCustomers(int id);
    List<Company> searchCompanies(String searchName);
    List<Customer> searchCompanyCustomers(int id, String searchName);
}
