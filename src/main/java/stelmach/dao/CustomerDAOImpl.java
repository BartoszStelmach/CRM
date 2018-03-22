package stelmach.dao;

import stelmach.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomerList() {
        Session session = sessionFactory.getCurrentSession();

        Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);

        return query.getResultList();
    }

    @Override
    public Customer getCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Customer.class, id);
    }

    @Override
    public void saveCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(customer);
    }

    @Override
    public void deleteCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();
        Customer customer = session.get(Customer.class, id);
        session.delete(customer);
    }

    @Override
    public List<Customer> searchCustomers(String searchName) {
        Session session = sessionFactory.getCurrentSession();

        Query query;

        // Only search by name if search value is not empty. Else - get all customers
        if (searchName != null && searchName.trim().length() > 0) {
            query = session.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName order by lastName", Customer.class);
            query.setParameter("theName", "%" + searchName.toLowerCase() + "%");
        }
        else {
            query = session.createQuery("from Customer order by lastName", Customer.class);
        }

        return query.getResultList();
    }
}
