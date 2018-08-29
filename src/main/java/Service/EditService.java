package Service;

import dataBase.DataBaseSimulation;
import model.Customer;

import java.util.Map;

public class EditService {

    private DataBaseSimulation dataBaseSimulation = new DataBaseSimulation();


    public Map<String, String> getCountries() {
        return dataBaseSimulation.getCountries();
    }

    public Map<String, String> getCities(String country) {
        return dataBaseSimulation.getCities(country);
    }

    public void onCountryChange(String country) {
        dataBaseSimulation.onCountryChange(country);
    }

    public Customer findCustomer(Long id) {
            return dataBaseSimulation.find(id);
    }

    public boolean userNameExists(Customer customer) {
        return customer != null && dataBaseSimulation.userNameExists(customer);
    }
//TODO: Make validator
    public void saveCustomer(Customer customer) {
//        try {
//            Validator validator = new Validator();
//            if (customer != null && validator.validate(customer)) {
//                DataBaseSimulation.save(customer);

//        } catch (Exception e) {
//            throw new NullPointerException("Error");
//        }

            dataBaseSimulation.save(customer);

    }
}
