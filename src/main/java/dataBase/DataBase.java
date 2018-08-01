package dataBase;

import model.Customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataBase {

    public static List<Customer> createData(){
        Customer customer1 = new Customer();
        customer1.setFirstName("John");
        customer1.setLastName("Daniz");
        customer1.setUser("John_Daniz");
        customer1.setBirthDate(randomDateOfBirth());
        customer1.setCountry("USA");
        customer1.setCity("New York");
        customer1.setStreet("Lincoln");
        customer1.setZipCode("1121");
        customer1.setPhoneNumber("20019882");
        customer1.setEmail("john@gmail.com");

        Customer customer2 = new Customer();
        customer2.setFirstName("Nick");
        customer2.setLastName("Jonson");
        customer2.setUser("Nick_Jonson");
        customer2.setBirthDate(randomDateOfBirth());
        customer2.setCountry("USA");
        customer2.setCity("Denver");
        customer2.setStreet("Lincoln2");
        customer2.setZipCode("1121");
        customer2.setPhoneNumber("20019862");
        customer2.setEmail("Nick@gmail.com");

        Customer customer3 = new Customer();
        customer3.setFirstName("John");
        customer3.setLastName("Kalomiris");
        customer3.setUser("John_Kalomiris");
        customer3.setBirthDate(randomDateOfBirth());
        customer3.setCountry("Germany");
        customer3.setCity("Berlin");
        customer3.setStreet("Daousen");
        customer3.setZipCode("1551");
        customer3.setPhoneNumber("20023212");
        customer3.setEmail("john1@gmail.com");

        Customer customer4 = new Customer();
        customer4.setFirstName("Jim");
        customer4.setLastName("Fotiadis");
        customer4.setUser("Jim_Fotiadis");
        customer4.setBirthDate(randomDateOfBirth());
        customer4.setCountry("Brazil");
        customer4.setCity("Rio de Janerio");
        customer4.setStreet("Samba1");
        customer4.setZipCode("99121");
        customer4.setPhoneNumber("65019882");
        customer4.setEmail("jim@gmail.com");

        Customer customer5 = new Customer();
        customer5.setFirstName("Jim");
        customer5.setLastName("Nicolson");
        customer5.setUser("Jim_Nicolson");
        customer5.setBirthDate(randomDateOfBirth());
        customer5.setCountry("USA");
        customer5.setCity("San Francisco");
        customer5.setStreet("Holiday11");
        customer5.setZipCode("991111");
        customer5.setPhoneNumber("65876882");
        customer5.setEmail("jim1@gmail.com");

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);
        customerList.add(customer4);
        customerList.add(customer5);

        return customerList;
    }

    private static Date randomDateOfBirth() {
        int day = randBetween(1,28);
        int month = randBetween(1,12);
        int year = randBetween(1900, 2018);
        return java.sql.Date.valueOf(LocalDate.of(year, month, day));
    }

    private static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }
}
