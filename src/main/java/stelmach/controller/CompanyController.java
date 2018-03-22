package stelmach.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import stelmach.entity.Company;
import stelmach.entity.Customer;
import stelmach.service.CompanyService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/company")
@SessionAttributes({"company", "searchName"})
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/list")
    public String listCompanies(Model model){

        // Get all companies
        List<Company> companies = companyService.getCompanyList();
        model.addAttribute("companies", companies);

        return "list-companies";
    }

    @GetMapping("/addForm")
    public String addForm(Model model){

        // Create company to bind with the form
        Company company = new Company();
        model.addAttribute("company", company);

        return "company-form";
    }

    @GetMapping("/updateForm")
    public String updateForm(@RequestParam("companyId") int id, Model model){

        // Get the company to pre-populate the form
        Company company = companyService.getCompany(id);
        model.addAttribute("company", company);

        return "company-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("company") Company company){

        // Save or update the company
        companyService.saveCompany(company);

        return "redirect:/company/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("companyId") int id){

        // Delete the company
        companyService.deleteCompany(id);

        return "redirect:/company/list";
    }

    @RequestMapping("/search")
    public String searchCompanies(@RequestParam(name = "searchName", required = false) String searchName, Model model ){

        // Get companies according to the search value
        List<Company> companies = companyService.searchCompanies(searchName);
        model.addAttribute("companies", companies);

        return "list-companies";
    }

    @GetMapping("/companyCustomers")
    public String companyCustomers(HttpServletRequest request, @RequestParam(name = "companyId", required = false) Integer id, Model model){

        Company company = null;

        // If we have id - set company as a SessionAttribute. Else - get it from SessionAttribute
        if(id != null) {
            company = companyService.getCompany(id);
            request.getSession().setAttribute("company",company);
        } else {
            company = (Company)request.getSession().getAttribute("company");
            id = company.getId();
        }

        model.addAttribute("company", company);

        // Get sorted customers of the company
        List<Customer> customers = companyService.getCustomers(id);
        model.addAttribute("customers", customers);

        return "company-list-customers";
    }

    @PostMapping("/searchCompanyCustomers")
    public String searchCompanyCustomers(HttpServletRequest request, @ModelAttribute("company") Company company, @RequestParam(name = "searchName", required = false) String searchName, Model model ){

        // Save company as a session attribute
        request.getSession().setAttribute("company",company);

        // Get company's customers according to the search value
        List<Customer> customers = companyService.searchCompanyCustomers(company.getId(), searchName);
        model.addAttribute("customers", customers);

        return "company-list-customers";
    }

    @GetMapping("/searchCompanyCustomers")
    public String searchCompanyCustomers(HttpServletRequest request, @RequestParam(name = "searchName", required = false) String searchName, Model model ){

        // Receive company from session attribute
        Company company = (Company)request.getSession().getAttribute("company");

        // Get company's customers according to the search value
        List<Customer> customers = companyService.searchCompanyCustomers(company.getId(), searchName);
        model.addAttribute("customers", customers);

        return "company-list-customers";
    }
}
