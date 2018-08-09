package Service;

import dataBase.DataBaseSimulation;
import model.Customer;

import java.util.Map;

public class EditService {

    public static void initLocation() {
        DataBaseSimulation.runOnlyOnceLocation();
    }

    public static Map<String, String> getCountries() {
        return DataBaseSimulation.getCountries();
    }

    public static Map<String, String> getCities() {
        return DataBaseSimulation.getCities();
    }

    public static void onCountryChange(String country) {
        DataBaseSimulation.onCountryChange(country);
    }

    public static Customer findCustomer(int id) {
        if (id != 0) {
            return DataBaseSimulation.find(id);
        }
        return null;
    }

    public static boolean isDuplex(Customer customer){
        if (customer != null){
            return DataBaseSimulation.isDuplex(customer);
        }
        //TODO: throw exception
        return false;
    }

    public static void saveCustomer(Customer customer){
        if (customer != null)
        DataBaseSimulation.save(customer);
    }
}
