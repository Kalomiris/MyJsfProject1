package controller;

import Service.CustomerService;
import language.LanguageResource;
import model.Customer;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "customerController")
@RequestScoped
public class CustomerController implements Serializable {

    private static final long serialVersionUID = 2744527592489782284L;
    private static List<Customer> customers;
    private static List<Customer> filteredCustomers;

//    @ManagedProperty(value = "#{customerService}")
//    private CustomerService customerService;

    @PostConstruct
    private static void init() {
        CustomerService.initDataService();
        customers = CustomerService.getCustomerList();
    }

//    public void setCustomerService(CustomerService customerService) {
//        this.customerService = customerService;
//    }
//
//    public CustomerService getCustomerService() {
//        return customerService;
//    }

    public List<Customer> getFilteredCustomers() {
        return filteredCustomers;
    }

    public void setFilteredCustomers(List<Customer> filteredCustomers) {
        CustomerController.filteredCustomers = filteredCustomers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void deleteCustomer(Customer customer){
        deleteMessage();
        CustomerService.delete(customer);
    }

//    public String onPageRedirect(){
//        customers = CustomerService.getCustomerList();
//        return "editCustomer?faces-redirect=true";
//    }
//
//
//    public String goBack(){
//        customers = CustomerService.getCustomerList();
//        return "home?faces-redirect=true";
//    }

    public void deleteMessage() {
        LanguageResource lrs = new LanguageResource();
        addMessage(lrs.getLanguageResource("successfull"), lrs.getLanguageResource("deleted"));
    }

    private void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


}
