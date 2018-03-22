package stelmach.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import stelmach.entity.Company;
import stelmach.entity.Customer;

import java.util.List;

@Repository
public class CompanyDAOImpl implements CompanyDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void saveCompany(Company company) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(company);
    }

    @Override
    public Company getCompany(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Company.class, id);
    }

    @Override
    public List<Company> getCompanyList() {
        Session session = sessionFactory.getCurrentSession();
        Query<Company> query = session.createQuery("from Company order by name", Company.class);
        List<Company> companies = query.getResultList();
        return companies;
    }

    @Override
    public void deleteCompany(int id) {
        Session session = sessionFactory.getCurrentSession();
        Company company = session.get(Company.class, id);
        session.delete(company);
    }

    @Override
    public List<Customer> getCustomers(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Customer where company.id = :theId order by lastName", Customer.class);
        query.setParameter("theId", id);
        List<Customer> customers = query.getResultList();
        return customers;
    }

    @Override
    public List<Company> searchCompanies(String searchName) {
        Session session = sessionFactory.getCurrentSession();
        Query query;

        // Only search by name if search value is not empty. Else - get all companies
        if (searchName != null && searchName.trim().length() > 0) {
            query = session.createQuery("from Company where lower(name) like :theName order by name", Company.class);
            query.setParameter("theName", "%" + searchName.toLowerCase() + "%");
        }
        else {
            query = session.createQuery("from Company order by name", Company.class);
        }

        return query.getResultList();
    }

    @Override
    public List<Customer> searchCompanyCustomers(int id, String searchName) {
        Session session = sessionFactory.getCurrentSession();

        Query query;

        // Only search by name if search value is not empty. Else - get all company's customers
        if (searchName != null && searchName.trim().length() > 0) {
            query = session.createQuery("from Customer where company.id = :id and (lower(firstName) like :theName or lower(lastName) like :theName) order by lastName", Customer.class);
            query.setParameter("theName", "%" + searchName.toLowerCase() + "%");
            query.setParameter("id", id);
        }
        else {
            query = session.createQuery("from Customer where company.id = :id order by lastName", Customer.class);
            query.setParameter("id", id);
        }

        return query.getResultList();
    }
}
