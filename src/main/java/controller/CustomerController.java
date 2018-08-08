package controller;

import Service.CustomerService;
import language.LanguageResource;
import model.Customer;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "customerView")
@RequestScoped
public class CustomerController implements Serializable {

    private static final long serialVersionUID = 2744527592489782284L;
    private List<Customer> customers;
    private List<Customer> filteredCustomers;

    @ManagedProperty(value = "#{customerService}")
    private CustomerService customerService;

    @PostConstruct
    public void init() {
        customers = customerService.getList();
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public List<Customer> getFilteredCustomers() {
        return filteredCustomers;
    }

    public void setFilteredCustomers(List<Customer> filteredCustomers) {
        this.filteredCustomers = filteredCustomers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void deleteMessage() {
        LanguageResource lrs = new LanguageResource();
        addMessage(lrs.getLanguageResource("successfull"), lrs.getLanguageResource("deleted"));
    }

    private void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


}
