package dev.swarnim.project.database;

import dev.swarnim.project.model.Customer;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface CustomerDao {

    @SqlQuery("Select * from customer")
    @RegisterBeanMapper(Customer.class)
    List<Customer> getAllUser();

    @SqlQuery("Select EXISTS(Select id from customer where username=:userName)")
    boolean findIfCustomerExist(String userName);

}
