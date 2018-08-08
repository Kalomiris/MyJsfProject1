package controller;

import Service.CustomerService;
import language.LanguageResource;
import model.Customer;
import org.primefaces.event.SelectEvent;
import validation.Validator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

@ManagedBean(name = "userController")
@RequestScoped
public class UserController implements Serializable {

    private static final String NAME_PATTERN = "^[a-z_-]{2,20}$";
    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{2,20}$";
    private static final String NUMBER_PATTERN = "^[0-9]{2,10}$";
    private static final String EMAIL_PATTERN = "^(.+)@(.+)$";
    //private static final String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";

    private static final long serialVersionUID = -4876525344689517081L;
    private Customer customer;
    private List<Customer> customers;
    private String country;
    private String city;
    private int id;
    private boolean acceptTerms;
    private Map<String,Map<String,String>> data = new HashMap<>();
    private Map<String,String> countries;
    private Map<String,String> cities;

    @ManagedProperty(value = "#{customerService}")
    private CustomerService customerService;

    @PostConstruct
    public void init() {
        countries = new HashMap<>();
        countries.put("USA", "USA");
        countries.put("Germany", "Germany");
        countries.put("Brazil", "Brazil");

        Map<String, String> map = new HashMap<>();
        map.put("New York", "New York");
        map.put("San Francisco", "San Francisco");
        map.put("Denver", "Denver");
        data.put("USA", map);

        map = new HashMap<>();
        map.put("Berlin", "Berlin");
        map.put("Munich", "Munich");
        map.put("Frankfurt", "Frankfurt");
        data.put("Germany", map);

        map = new HashMap<>();
        map.put("Sao Paolo", "Sao Paolo");
        map.put("Rio de Janerio", "Rio de Janerio");
        map.put("Salvador", "Salvador");
        data.put("Brazil", map);

        customer = new Customer();
        customers = new ArrayList<>();
        customers = CustomerService.getList();
        findCustomer(this.id);
        onCountryChange();
    }

    public void setFirstName(String firstName) {
        if(new Validator(NAME_PATTERN).validate(firstName))
        customer.setFirstName(firstName);
    }

    public String getFirstName() {
        return customer.getFirstName();
    }

    public void setLastName(String lastName) {
        if(new Validator(NAME_PATTERN).validate(lastName))
        customer.setLastName(lastName);
    }

    public String getLastName() {
        return customer.getLastName();
    }

    public void setUser(String user) {
        if(new Validator(USERNAME_PATTERN).validate(user))
        customer.setUser(user);
    }

    public String getUser() {
        return customer.getUser();
    }

    public String getCountry() {
        return  customer.getCountry();
    }

    public void setCountry(String country) {
        if (new Validator(NAME_PATTERN).validate(country)) {
            customer.setCountry(country);
        }
        this.country = country;
    }

    public String getCity() {
        return customer.getCity();
    }

    public void setCity(String city) {
        if (new Validator(NAME_PATTERN).validate(city)) {
            customer.setCity(city);
        }
        this.city = city;
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

    public List<Customer> getCustomers() {
        return customers;
    }

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
    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public Map<String, String> getCountries() {
        return countries;
    }

    public Map<String, String> getCities() {
        return cities;
    }

    //TODO:check for usage
    public CustomerService getCustomerService() {
        return customerService;
    }
    //TODO:check for usage
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void onCountryChange() {
        if(country !=null && !country.equals(""))
            cities = data.get(country);
        else
            cities = new HashMap<>();
    }
    //TODO:check for usage
    public void displayLocation() {
        FacesMessage msg;
        if(city != null && country != null)
            msg = new FacesMessage("Selected", city + " of " + country);
        else
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "City is not selected.");

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }


    public String reinit() {
        customer = new Customer();
        if (customers.contains(customer)) {
            return null;
        }
        return "home?faces-redirect=true";
    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("YYYY/dd/MM");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    private void findCustomer(int id){
        for (Customer element : customers) {
            if (element.getId() == id){
                customer = element;
            }
        }
    }

    public void addMessage() {
        LanguageResource lrs = new LanguageResource();
        if(customers.contains(customer)) {
            FacesMessage msg = new FacesMessage(lrs.getLanguageResource("dublicated"), lrs.getLanguageResource("alreadyExist"));
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else {
            FacesMessage msg = new FacesMessage(lrs.getLanguageResource("added"));
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

}