package dev.swarnim.project.database;

import dev.swarnim.project.model.User;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface UserDao {

    @SqlQuery("Select * from user")
    @RegisterBeanMapper(User.class)
    List<User> getAllUser();
}
