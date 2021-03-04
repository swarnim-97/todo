package dev.swarnim.project.database;

import dev.swarnim.project.model.request.Device;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface DeviceDao {

    @SqlQuery("SELECT * FROM Device WHERE device_type=:deviceType AND device_make=:deviceMake AND " +
            "identifier_type=:identifierType AND identification_number=:identificationNumber")
    @RegisterBeanMapper(Device.class)
    Device findDeviceByIdentifierTypeAndIdentificationNumber(String deviceType,
                                                             String deviceMake,
                                                             String identifierType,
                                                             String identificationNumber);

    @SqlUpdate("INSERT INTO Device (device_type, device_make, device_model, os, os_version, identifier_type, identification_number)" +
            "VALUES (:deviceType, :deviceMake, :deviceModel, :os, :osVersion, :identifierType, :identificationNumber)")
    @GetGeneratedKeys
    long createNewDevice(String deviceType,
                         String deviceMake,
                         String deviceModel,
                         String os,
                         String osVersion,
                         String identifierType,
                         String identificationNumber);
}
