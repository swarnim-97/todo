package dev.swarnim.project.database;

import dev.swarnim.project.model.Customer;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface CustomerDao {

    @SqlQuery("Select * from customer")
    @RegisterBeanMapper(Customer.class)
    List<Customer> getAllUser();

    @SqlQuery("Select EXISTS(Select id from customer where username=:userName)")
    boolean findIfCustomerExist(String userName);

    @SqlUpdate("Insert into customer (name, username, password) values (:name, :username, :password)")
    int createUser(String name,
                   String username,
                   String password);

    @SqlQuery("SELECT password from customer where username=:username")
    String findCustomerPassword(String username);

    @SqlQuery("SELECT EXISTS (SELECT 1 from customer where id=:customerId)")
    boolean checkIfCustomerExists(Long customerId);

}
