package controller;

import Service.EditService;
import model.Customer;
import utils.Localizer;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import java.io.Serializable;
import java.util.Map;

@ManagedBean(name = "editController")
@ViewScoped
public class EditController implements Serializable {

    private static final long serialVersionUID = -4876525344689517081L;
    private Map<String, String> countries;
    private Map<String, String> cities;
    private Customer customer;

    private Long id;

    private EditService editService = new EditService();

    @PostConstruct
    public void init() {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        id = (Long) flash.get("id");
        customer = new Customer();
        countries = editService.getCountries();
        initCustomer();
        onCountryChange();
    }

    private void initCustomer() {
        if (id != null) {
            customer = editService.findCustomer(id);
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Map<String, String> getCountries() {
        return countries;
    }

    public Map<String, String> getCities() {
        return cities;
    }

    public void onCountryChange() {
        cities = editService.getCities(customer.getCountry());
    }

    public void saveCustomer() throws Exception {
        if (customer.isAcceptTerms()) {
            saveMessage();
            editService.saveCustomer(customer);
        }else {
            warnMessage();
        }
    }

    private void saveMessage() {
        Localizer lrs = new Localizer();
        addMessage(lrs.getLanguageResource("successful"), lrs.getLanguageResource("saved"));
    }

    private void warnMessage() {
        Localizer lrs = new Localizer();
        addMessage(lrs.getLanguageResource("warn"), lrs.getLanguageResource("acceptTerms"));
    }

    private void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}