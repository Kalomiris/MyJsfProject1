package controller;

import Service.EditService;
import model.Customer;
import org.primefaces.event.SelectEvent;
import utils.Localizer;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import java.io.Serializable;
import java.text.SimpleDateFormat;
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

    public String editCustomer(Long id) {
        this.id = id;
        initCustomer();
        return "editCustomer?faces-redirect=true&includeViewParams=true";
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;

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

    public void saveCustomer() {
        saveMessage();
        editService.saveCustomer(customer);

    }

    private void saveMessage() {
        Localizer lrs = new Localizer();
        addInfoMessage(lrs.getLanguageResource("Saved"), lrs.getLanguageResource("Customer added successfully!"));
    }


    private void saveMessageDuplex() {
        Localizer lrs = new Localizer();
        addWarnMessage(lrs.getLanguageResource("Duplex"), lrs.getLanguageResource("This customer is already Existed!"));
    }


//    public void warnMessage() {
//        Localizer lrs = new Localizer();
//        addWarnMessage(lrs.getLanguageResource("Invalid"), lrs.getLanguageResource("Something goes wrong with customer!"));
//    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("YYYY/dd/MM");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    private void addWarnMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private void addInfoMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}