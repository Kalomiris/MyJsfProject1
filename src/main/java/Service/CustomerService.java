package Service;

import dataBase.DataBase;
import model.Customer;

import java.io.Serializable;
import java.util.List;


//@ManagedBean(name = "customerService")
//@RequestScoped
public class CustomerService implements Serializable{

    private static final long serialVersionUID = -6851187912764155763L;
//    private static List<Customer> list = new ArrayList<>();

//    @PostConstruct
    public static void initDataService() {
        DataBase.runOnlyOnceCustomer();
//        list = DataBase.getCustomerList();
    }

    public static void delete(Customer customer){
        DataBase.deleteCustomer(customer);
    }

    public static List<Customer> getCustomerList() {
        return DataBase.getCustomerList();
    }
}
