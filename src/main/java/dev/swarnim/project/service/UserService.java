package dev.swarnim.project.service;

import dev.swarnim.project.database.DeviceDao;
import dev.swarnim.project.database.LocationDao;
import dev.swarnim.project.database.SessionDao;
import dev.swarnim.project.database.CustomerDao;
import dev.swarnim.project.errorhandler.InvalidArgumentException;
import dev.swarnim.project.errorhandler.TodoApplicationValidationFailedException;
import dev.swarnim.project.errorhandler.TodoErrorCodes;
import dev.swarnim.project.model.Customer;
import dev.swarnim.project.model.request.Device;
import dev.swarnim.project.model.request.Location;
import dev.swarnim.project.model.request.SessionRequest;
import dev.swarnim.project.model.response.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class UserService {

    private final CustomerDao customerDao;
    private final DeviceDao deviceDao;
    private final LocationDao locationDao;
    private final SessionDao sessionDao;

    @Autowired
    public UserService(CustomerDao customerDao,
                       DeviceDao deviceDao,
                       LocationDao locationDao,
                       SessionDao sessionDao) {
        this.customerDao = customerDao;
        this.deviceDao = deviceDao;
        this.locationDao = locationDao;
        this.sessionDao = sessionDao;
    }

    public Session createSession(SessionRequest sessionRequest){
        Device device = sessionRequest.getDevice();
        log.info("device {}", device);
        Device existingDevice = deviceDao.findDeviceByIdentifierTypeAndIdentificationNumber(device.getDeviceType(),
                device.getDeviceMake(),
                device.getIdentifierType().name(),
                device.getIdentificationNumber());
        log.info("existing device {}", existingDevice);
        Long deviceId = existingDevice != null ? existingDevice.getId() : null;
        if(existingDevice==null){
            deviceId = deviceDao.createNewDevice(device.getDeviceType(),
                    device.getDeviceMake(),
                    device.getDeviceModel(),
                    device.getOs(),
                    device.getOsVersion(),
                    device.getIdentifierType().name(),
                    device.getIdentificationNumber());
        }
        String sessionId = UUID.randomUUID().toString();
        sessionDao.createNewSession(sessionRequest.getSource().toString(), sessionId, deviceId, null);
        return Session.builder().sessionId(sessionId).build();
    }

    public void createUser(Customer customer){
        if(customer.getUserName() == null || customer.getName() == null || customer.getPassword() == null){
            throw new InvalidArgumentException(TodoErrorCodes.TODO0001);
        }
        boolean isUserPresent = customerDao.findIfCustomerExist(customer.getUserName());

        if(isUserPresent){
            log.info("User already present with username {}", customer.getUserName());
            throw new InvalidArgumentException(TodoErrorCodes.TODO0002);
        }

        customerDao.createUser(customer.getName(), customer.getUserName(), customer.getPassword());
    }

    public void loginUser(Customer customer){
        log.info("login request for username: {}", customer.getUserName());
        if(customer.getUserName() == null || customer.getPassword() == null){
            throw new InvalidArgumentException(TodoErrorCodes.TODO0001);
        }

        if(!customerDao.findIfCustomerExist(customer.getUserName())){
            throw new InvalidArgumentException(TodoErrorCodes.TODO0003);
        }
        log.info("customer exists.");

        String password = customerDao.findCustomerPassword(customer.getUserName());
        if(!password.equals(customer.getPassword())){
            throw new InvalidArgumentException(TodoErrorCodes.TODO0004);
        }
    }
}
