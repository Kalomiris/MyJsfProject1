package dataBase;

import model.Customer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class DataBaseSimulation implements Serializable {

    private static final long serialVersionUID = -1058834760623324195L;

    private static List<Customer> customerList = new LinkedList<>();
    private static Map<String, Map<String, String>> data = new HashMap<>();
    private static Map<String, String> countries;
    private static Map<String, String> cities;

    private static boolean isExecutedInitCustomer = false;
    private static boolean isExecutedInitLocation = false;

    private void initCustomer() {

        // simulate auto increment id
        Long autoIncrementId = 1L;

        Customer customer1 = new Customer(autoIncrementId++);
        customer1.setFirstName("John");
        customer1.setLastName("Daniz");
        customer1.setUserName("John_Daniz");
        customer1.setBirthDate(randomDateOfBirth());
        customer1.setCountry("USA");
        customer1.setCity("New York");
        customer1.setStreet("Lincoln");
        customer1.setZipCode("1121");
        customer1.setPhoneNumber("20019882");
        customer1.setEmail("john@gmail.com");

        Customer customer2 = new Customer(autoIncrementId++);
        customer2.setFirstName("Nick");
        customer2.setLastName("Kantas");
        customer2.setUserName("Nick_kantas");
        customer2.setBirthDate(randomDateOfBirth());
        customer2.setCountry("USA");
        customer2.setCity("Denver");
        customer2.setStreet("Lincoln2");
        customer2.setZipCode("1121");
        customer2.setPhoneNumber("20019862");
        customer2.setEmail("Nick@gmail.com");

        Customer customer3 = new Customer(autoIncrementId++);
        customer3.setFirstName("George");
        customer3.setLastName("Kakogiannis");
        customer3.setUserName("George_kakogiannis");
        customer3.setBirthDate(randomDateOfBirth());
        customer3.setCountry("Germany");
        customer3.setCity("Berlin");
        customer3.setStreet("Daousen");
        customer3.setZipCode("1551");
        customer3.setPhoneNumber("20023212");
        customer3.setEmail("george@gmail.com");

        Customer customer4 = new Customer(autoIncrementId++);
        customer4.setFirstName("Tim");
        customer4.setLastName("Fotiadis");
        customer4.setUserName("Jim_Fotiadis");
        customer4.setBirthDate(randomDateOfBirth());
        customer4.setCountry("Brazil");
        customer4.setCity("Rio de Janerio");
        customer4.setStreet("Samba1");
        customer4.setZipCode("99121");
        customer4.setPhoneNumber("65019882");
        customer4.setEmail("jim@gmail.com");

        Customer customer5 = new Customer(autoIncrementId++);
        customer5.setFirstName("Jim");
        customer5.setLastName("Nicolson");
        customer5.setUserName("Jim_Nicolson");
        customer5.setBirthDate(randomDateOfBirth());
        customer5.setCountry("USA");
        customer5.setCity("San Francisco");
        customer5.setStreet("Holiday11");
        customer5.setZipCode("991111");
        customer5.setPhoneNumber("65876882");
        customer5.setEmail("jim1@gmail.com");

        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);
        customerList.add(customer4);
        customerList.add(customer5);
    }

    private void initLocation() {
        countries = new HashMap<>();
        countries.put("USA", "USA");
        countries.put("Germany", "Germany");
        countries.put("Brazil", "Brazil");
        countries.put("Greece", "Greece");

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

        map = new HashMap<>();
        map.put("Athens", "Athens");
        map.put("Volos", "Volos");
        map.put("Patra", "Patra");
        data.put("Greece", map);
    }

    public void runOnlyOnceCustomer() {
        if (!isExecutedInitCustomer) {
            initCustomer();
            isExecutedInitCustomer = true;
        }
    }

    public void runOnlyOnceLocation() {
        if (!isExecutedInitLocation) {
            initLocation();
            isExecutedInitLocation = true;
        }
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void save(Customer customer) {
        if (customer.getId() > 0) {
            Long id = customer.getId();
            Customer removedCustomer = find(id);
            deleteCustomer(removedCustomer);
            customerList.add(customer);
        }else {
            // simulate auto increment id
            Long maxId = customerList.get(customerList.size() - 1).getId();
            customer.setId(maxId + 1);
            customerList.add(customer);
        }
    }

    public Customer find(Long id) {
        for (Customer customer : customerList) {
            if (id.equals(customer.getId())) {
                return customer;
            }
        }
        return null;
    }

    public void deleteCustomer(Customer customer) {
        customerList.remove(customer);
    }

    public boolean userNameExists(Customer customer) {
        for (Customer element : customerList) {
            if (customerList.contains(customer)) {
                return true;
            }
        }
        return false;
    }

    private static Date randomDateOfBirth() {
        int day = randBetween(1, 28);
        int month = randBetween(1, 12);
        int year = randBetween(1900, 2018);
        return java.sql.Date.valueOf(LocalDate.of(year, month, day));
    }

    private static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public Map<String, String> getCountries() {
        return countries;
    }

    public Map<String, String> getCities() {
        return cities;
    }

    public void onCountryChange(String country) {
        if (country != null && !country.equals("")) {
            cities = data.get(country);
        } else {
            cities = new HashMap<>();
        }
    }
}
