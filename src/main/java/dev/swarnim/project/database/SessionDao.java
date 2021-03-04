package dev.swarnim.project.database;

import dev.swarnim.project.model.response.Session;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface SessionDao {

    @SqlUpdate("INSERT INTO session (source, session_id, device_id, location_id) VALUES " +
            "(:source, :sessionId, :deviceId, :locationId)")
    @GetGeneratedKeys
    long createNewSession(String source,
                          String sessionId,
                          Long deviceId,
                          Long locationId);
}
