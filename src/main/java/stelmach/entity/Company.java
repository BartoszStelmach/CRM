package stelmach.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "branch")
    private String branch;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Customer> customers;

    public Company() {
    }

    public Company(String name, String branch) {
        this.name = name;
        this.branch = branch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public void addCustomer(Customer customer) {
        if (customers == null) {
            customers = new ArrayList<>();
        }

        customers.add(customer);

        customer.setCompany(this);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", branch='" + branch + '\'' +
                '}';
    }
}
