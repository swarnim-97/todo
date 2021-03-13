package dev.swarnim.project.service;

import dev.swarnim.project.database.CustomerDao;
import dev.swarnim.project.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@Slf4j
public class UserServiceTest {

    @Mock private CustomerDao customerDao;
    @InjectMocks private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void done(){
        System.out.println("Hello hi");
        Customer customer = Customer.builder().name("name").build();
        assertNotNull(customer);
    }

    @Test
    public void loginUser() {
        Customer customer = Customer.builder().userName("@name").password("pass").build();
        assertNotNull(customer.getUserName());
        assertNotNull(customer.getPassword());

        when(customerDao.findIfCustomerExist(anyString())).thenReturn(true);
        when(customerDao.findCustomerPassword(anyString())).thenReturn(customer.getPassword());

        userService.loginUser(customer);

        verify(customerDao).findIfCustomerExist(anyString());
        verify(customerDao).findCustomerPassword(anyString());
    }

    @Test
    @Disabled
    public void createSession() {

    }

    @Test
    @Disabled
    public void createUser() throws Exception{

    }

}
