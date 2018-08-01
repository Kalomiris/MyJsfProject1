import dataBase.DataBase;
import model.Customer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@ManagedBean(name = "customerService")
@SessionScoped
public class CustomerService implements Serializable{

    private static final long serialVersionUID = -6851187912764155763L;
    private List<Customer> list = new ArrayList<Customer>();

    @PostConstruct
    public void createCustomer() {
        list.addAll(DataBase.createData());
    }

    public List<Customer> getList() {
        return list;
    }

    private Date randomDateOfBirth() {
        int day = randBetween(1,28);
        int month = randBetween(1,12);
        int year = randBetween(1900, 2018);
        return java.sql.Date.valueOf(LocalDate.of(year, month, day));
    }

    private int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }
}
