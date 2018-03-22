package stelmach.controller;

import org.springframework.web.bind.annotation.*;
import stelmach.entity.Company;
import stelmach.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import stelmach.service.CompanyService;
import stelmach.service.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CompanyService companyService;

    @GetMapping("/list")
    public String listCustomers(Model model){

        // Get all customers
        List<Customer> customers = customerService.getCustomerList();
        model.addAttribute("customers", customers);

        return "list-customers";
    }

    @GetMapping("/addForm")
    public String addForm(Model model){

        // Create customer to bind with the form
        Customer customer = new Customer();
        model.addAttribute("customer", customer);

        // Get all companies to show in the select list
        List<Company> companies = companyService.getCompanyList();
        model.addAttribute("companies", companies);

        return "customer-form";
    }

    @GetMapping("/updateForm")
    public String updateForm(@RequestParam("customerId") int id, Model model){

        // Get the customer to pre-populate the form
        Customer customer = customerService.getCustomer(id);
        model.addAttribute("customer", customer);

        // Get all companies to show in the select list
        List<Company> companies = companyService.getCompanyList();
        model.addAttribute("companies", companies);

        return "customer-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("customer") Customer customer){

        // Save or update the customer
        customerService.saveCustomer(customer);

        return "redirect:/customer/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("customerId") int id){

        // Delete the customer
        customerService.deleteCustomer(id);

        return "redirect:/customer/list";
    }

    @RequestMapping("/search")
    public String searchCustomers(@RequestParam(name = "searchName", required = false) String searchName, Model model ){

        // Get customers according to the search value
        List<Customer> customers = customerService.searchCustomers(searchName);
        model.addAttribute("customers", customers);

        return "list-customers";
    }

    @GetMapping(value = "/statute")
    public String statute() {
        return "forward:/resources/pdfs/statute.pdf";
    }


}
