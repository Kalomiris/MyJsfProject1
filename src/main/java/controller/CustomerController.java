package controller;

import Service.CustomerService;
import utils.Localizer;
import model.Customer;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "customerController")
@RequestScoped
public class CustomerController implements Serializable {

    private static final long serialVersionUID = 2744527592489782284L;
    private static List<Customer> customers;
    private static List<Customer> filteredCustomers;
    private CustomerService customerService = new CustomerService();

    @PostConstruct
    private void init() {
        customers = customerService.getCustomerList();
    }

    public String editCustomer(Long id){
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("id", id);
        return "editCustomer?faces-redirect=true&includeViewParams=true";
    }

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
        customerService.delete(customer);
    }

    private void deleteMessage() {
        Localizer lrs = new Localizer();
        addMessage(lrs.getLanguageResource("successfull"), lrs.getLanguageResource("deleted"));
    }

    private void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
