package Service;

import dataBase.DataBase;
import model.Customer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@ManagedBean(name = "customerService")
@RequestScoped
public class CustomerService implements Serializable{

    private static final long serialVersionUID = -6851187912764155763L;
    private List<Customer> list = new ArrayList<>();

    @PostConstruct
    public void createCustomer() {
        list.addAll(DataBase.createData());
    }

    public List<Customer> getList() {
        return list;
    }
}
