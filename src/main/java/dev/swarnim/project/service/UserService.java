package dev.swarnim.project.service;

import dev.swarnim.project.database.DeviceDao;
import dev.swarnim.project.database.LocationDao;
import dev.swarnim.project.database.SessionDao;
import dev.swarnim.project.database.CustomerDao;
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
                       LocationDao locationDao, SessionDao sessionDao) {
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

        Location location = sessionRequest.getLocation();
        Location existingLocation = locationDao.findLocationByLatAndLong(location.getLatitude(), location.getLongitude());
        Long locationId = existingLocation != null ? existingLocation.getId() : null;
        if(existingLocation == null){
            locationId = locationDao.createNewLocation(location.getLatitude(), location.getLongitude());
        }
        String sessionId = UUID.randomUUID().toString();
        sessionDao.createNewSession(sessionRequest.getSource().toString(), sessionId, deviceId, locationId);
        return Session.builder().sessionId(sessionId).build();
    }

    public void createUser(Customer customer){
        if(customer.getUserName() == null || customer.getName() == null || customer.getPassword() == null){
            System.out.println("throw error");
        }
        boolean isUserPresent = customerDao.findIfCustomerExist(customer.getUserName());

        if(isUserPresent){
            System.out.println("throw error with message user alredy prpesent");
        }
    }
}
