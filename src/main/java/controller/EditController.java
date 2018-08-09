package controller;

import Service.EditService;
import model.Customer;
import org.primefaces.event.SelectEvent;
import validation.Validator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@ManagedBean(name = "editController")
@RequestScoped
public class EditController implements Serializable {

    private static final String NAME_PATTERN = "^[a-z_-]{2,40}$";
    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{2,40}$";
    private static final String NUMBER_PATTERN = "^[0-9]{2,30}$";
    private static final String EMAIL_PATTERN = "^(.+)@(.+)$";
    //private static final String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";

    private static final long serialVersionUID = -4876525344689517081L;
    private Customer customer;
    private boolean acceptTerms;
    private int id;
//    private String city;
//    private String country;
//    private List<Customer> customers;
//    private Map<String,Map<String,String>> data = new HashMap<>();
//    private Map<String,String> countries;
//    private Map<String,String> cities;
//
//    @ManagedProperty(value = "#{customerService}")
//    private CustomerService customerService;

    @PostConstruct
    public void init() {
//        countries = new HashMap<>();
//        countries.put("USA", "USA");
//        countries.put("Germany", "Germany");
//        countries.put("Brazil", "Brazil");
//
//        Map<String, String> map = new HashMap<>();
//        map.put("New York", "New York");
//        map.put("San Francisco", "San Francisco");
//        map.put("Denver", "Denver");
//        data.put("USA", map);
//
//        map = new HashMap<>();
//        map.put("Berlin", "Berlin");
//        map.put("Munich", "Munich");
//        map.put("Frankfurt", "Frankfurt");
//        data.put("Germany", map);
//
//        map = new HashMap<>();
//        map.put("Sao Paolo", "Sao Paolo");
//        map.put("Rio de Janerio", "Rio de Janerio");
//        map.put("Salvador", "Salvador");
//        data.put("Brazil", map);
//
//        customer = new Customer();
//        customers = new ArrayList<>();
//        customers = CustomerService.getCustomerList();
//        findCustomer(this.id);
//        onCountryChange();
        customer = new Customer();
        EditService.initLocation();
    }

    public void setFirstName(String firstName) {
        if (new Validator(NAME_PATTERN).validate(firstName))
            customer.setFirstName(firstName);
    }

    public String getFirstName() {
        return customer.getFirstName();
    }

    public void setLastName(String lastName) {
        if (new Validator(NAME_PATTERN).validate(lastName))
            customer.setLastName(lastName);
    }

    public String getLastName() {
        return customer.getLastName();
    }

    public void setUser(String user) {
        if (new Validator(USERNAME_PATTERN).validate(user))
            customer.setUser(user);
    }

    public String getUser() {
        return customer.getUser();
    }

    public String getCountry() {
        return customer.getCountry();
    }

    public void setCountry(String country) {
        customer.setCountry(country);

//        this.country = country;
    }

    public String getCity() {
        return customer.getCity();
    }

    public void setCity(String city) {

        customer.setCity(city);

//        this.city = city;
    }

    public String getStreet() {
        return customer.getStreet();
    }

    public void setStreet(String street) {
        if (new Validator(NAME_PATTERN).validate(street))
            customer.setStreet(street);
    }

    public String getZipCode() {
        return customer.getZipCode();
    }

    public void setZipCode(String zipCode) {
        if (new Validator(NUMBER_PATTERN).validate(zipCode))
            customer.setZipCode(zipCode);
    }

    public String getPhoneNumber() {
        return customer.getPhoneNumber();
    }

    public void setPhoneNumber(String phoneNumber) {
        if (new Validator(NUMBER_PATTERN).validate(phoneNumber))
            customer.setPhoneNumber(phoneNumber);
    }

    public String getEmail() {
        return customer.getEmail();
    }

    public void setEmail(String email) {
        if (new Validator(EMAIL_PATTERN).validate(email))
            customer.setEmail(email);
    }

    public Date getBirthDate() {
        return customer.getBirthDate();
    }

    public void setBirthDate(Date birthDate) {
        customer.setBirthDate(birthDate);
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

//    public List<Customer> getCustomers() {
//        return customers;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAcceptTerms() {
        return acceptTerms;
    }

    public void setAcceptTerms(boolean acceptTerms) {
        this.acceptTerms = acceptTerms;
    }

    //TODO:check for usage
//    public Map<String, Map<String, String>> getData() {
//        return data;
//    }

    public Map<String, String> getCountries() {
        return EditService.getCountries();
    }

    public Map<String, String> getCities() {
        return EditService.getCities();
    }

//    //TODO:check for usage
//    public CustomerService getCustomerService() {
//        return customerService;
//    }
//    //TODO:check for usage
//    public void setCustomerService(CustomerService customerService) {
//        this.customerService = customerService;
//    }

    public void onCountryChange() {
        String country = customer.getCountry();
        EditService.onCountryChange(country);
    }


//    public String reinit() {
//        customer = new Customer();
//        if (customers.contains(customer)) {
//            return null;
//        }
//        return "home?faces-redirect=true";
//    }

    public String saveCustomer() {
        if (!EditService.isDuplex(customer)) {
            EditService.saveCustomer(customer);
//            saveMessage();
            return "home?faces-redirect=true";
        }
//        saveMessageDuplex();
        return null;
    }

    public void findCustomer(int id) {
//        for (Customer element : customers) {
//            if (element.getId() == id){
//                customer = element;
//            }
//
        customer = EditService.findCustomer(id);

    }

//    private void saveMessage() {
//        Localizer lrs = new Localizer();
//        addInfoMessage(lrs.getLanguageResource("Saved"), lrs.getLanguageResource("Customer added successfully!"));
//    }


//    private void saveMessageDuplex() {
//        Localizer lrs = new Localizer();
//        addWarnMessage(lrs.getLanguageResource("Duplex"), lrs.getLanguageResource("This customer is already Existed!"));
//    }


//    public void warnMessage() {
//        Localizer lrs = new Localizer();
//        addWarnMessage(lrs.getLanguageResource("Invalid"), lrs.getLanguageResource("Something goes wrong with customer!"));
//    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("YYYY/dd/MM");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    //TODO:check for usage
    public void displayLocation() {
        FacesMessage msg;
        if (customer.getCity() != null && customer.getCountry() != null)
            msg = new FacesMessage("Selected", customer.getCity() + " of " + customer.getCountry());
        else
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "City is not selected.");

        FacesContext.getCurrentInstance().addMessage(null, msg);
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