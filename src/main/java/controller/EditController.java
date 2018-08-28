package controller;

import Service.EditService;
import model.Customer;
import org.primefaces.event.SelectEvent;
import utils.Localizer;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Map;

@ManagedBean(name = "editController")
@RequestScoped
public class EditController implements Serializable {

    private static final long serialVersionUID = -4876525344689517081L;

    private Customer customer;
    private boolean acceptTerms;
    private Long id;

    private EditService editService = new EditService();

    @PostConstruct
    public void init() {
        customer = new Customer();
        editService.initLocation();
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

    public boolean isAcceptTerms() {
        return acceptTerms;
    }

    public void setAcceptTerms(boolean acceptTerms) {
        this.acceptTerms = acceptTerms;
    }

    public Map<String, String> getCountries() {
        return editService.getCountries();
    }

    public Map<String, String> getCities() {
        return editService.getCities();
    }

    public void onCountryChange() {
        String country = customer.getCountry();
        editService.onCountryChange(country);
    }

    public String saveCustomer() {
        editService.saveCustomer(customer);
        return "home?faces-redirect=true";
    }

//    private void saveMessage() {
//        Localizer lrs = new Localizer();
//        addInfoMessage(lrs.getLanguageResource("Saved"), lrs.getLanguageResource("Customer added successfully!"));
//    }


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