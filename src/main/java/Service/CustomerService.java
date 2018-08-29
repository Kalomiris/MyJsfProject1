package Service;

import dataBase.DataBaseSimulation;
import model.Customer;

import java.io.Serializable;
import java.util.List;

public class CustomerService implements Serializable{


    private static final long serialVersionUID = 4524233348929636912L;
    private DataBaseSimulation dataBaseSimulation = new DataBaseSimulation();

    public void delete(Customer customer){
        dataBaseSimulation.deleteCustomer(customer);
    }

    public List<Customer> getCustomerList() {
        return dataBaseSimulation.getCustomerList();
    }
}
