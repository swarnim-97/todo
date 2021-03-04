package dev.swarnim.project.database;

import dev.swarnim.project.model.request.Location;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.math.BigDecimal;

public interface LocationDao {

    @SqlUpdate("INSERT INTO location (latitude, longitude) values (:latitude, :longitude)")
    @GetGeneratedKeys
    long createNewLocation(BigDecimal latitude,
                           BigDecimal longitude);

    @SqlQuery(" SELECT * FROM location where latitude=:latitude AND longitude=:longitude")
    @RegisterBeanMapper(Location.class)
    Location findLocationByLatAndLong(BigDecimal latitude,
                                      BigDecimal longitude);
}
