package Service;

import dataBase.DataBase;
import model.Customer;

import java.util.Map;

public class EditService {

    public static void initLocation() {
        DataBase.runOnlyOnceLocation();
    }

    public static Map<String, String> getCountries() {
        return DataBase.getCountries();
    }

    public static Map<String, String> getCities() {
        return DataBase.getCities();
    }

    public static void onCountryChange(String country) {
        DataBase.onCountryChange(country);
    }

    public static Customer findCustomer(int id) {
        if (id != 0) {
            return DataBase.find(id);
        }
        return null;
    }

    public static boolean isDuplex(Customer customer){
        if (customer != null){
            return DataBase.isDuplex(customer);
        }
        //TODO: throw exception
        return false;
    }

    public static void saveCustomer(Customer customer){
        if (customer != null)
        DataBase.save(customer);
    }
}
