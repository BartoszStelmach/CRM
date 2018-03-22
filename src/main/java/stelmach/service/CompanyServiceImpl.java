package stelmach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stelmach.dao.CompanyDAO;
import stelmach.entity.Company;
import stelmach.entity.Customer;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDAO companyDAO;

    @Override
    @Transactional
    public List<Company> getCompanyList() {
        return companyDAO.getCompanyList();
    }

    @Override
    @Transactional
    public void saveCompany(Company company) {
        companyDAO.saveCompany(company);
    }

    @Override
    @Transactional
    public Company getCompany(int id) {
        return companyDAO.getCompany(id);
    }

    @Override
    @Transactional
    public void deleteCompany(int id) {
        companyDAO.deleteCompany(id);
    }

    @Override
    @Transactional
    public List<Customer> getCustomers(int id) {
        return companyDAO.getCustomers(id);
    }

    @Override
    @Transactional
    public List<Company> searchCompanies(String searchName) {
        return companyDAO.searchCompanies(searchName);
    }

    @Override
    @Transactional
    public List<Customer> searchCompanyCustomers(int id, String searchName) { return companyDAO.searchCompanyCustomers(id, searchName);
    }
}
