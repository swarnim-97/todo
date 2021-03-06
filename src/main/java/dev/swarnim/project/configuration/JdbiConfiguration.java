package dev.swarnim.project.configuration;

import dev.swarnim.project.database.DeviceDao;
import dev.swarnim.project.database.LocationDao;
import dev.swarnim.project.database.SessionDao;
import dev.swarnim.project.database.CustomerDao;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class JdbiConfiguration {
    /*
    we need a Jdbi instance as our entry point to access JDBI's API.
    Here, we're using an available DataSource and wrapping it in a TransactionAwareDataSourceProxy.
    We need this wrapper in order to integrate Spring-managed transactions with JDBI.
    Registering plugins and RowMapper instances is straightforward.
    All we have to do is call installPlugin and installRowMapper for every available JdbiPlugin and RowMapper, respectively.
    jdbi.installPlugin(new JodaTimePlugin());
        jdbi.registerArrayType(String.class, "text");
        jdbi.registerArrayType(Long.class, "bigint");
        jdbi.installPlugin(new GuavaPlugin());

    ## Using DAO's
    To use those DAOs in our application, we have to instantiate them using one of the factory methods available in JDBI.
    In a Spring context, the simplest way is to create a bean for every DAO using the onDemand method:
    The onDemand-created instance is thread-safe and uses a database connection only during a method call.
     Since JDBI we'll use the supplied TransactionAwareDataSourceProxy,
      this means we can use it seamlessly with Spring-managed transactions.

    ## Transactional Services
    If there's a unique key violation (or some other error) at any point, the whole operation must fail and a full rollback should be performed.
    JDBI provides a @Transaction annotation, but we can't use it here as it is unaware of other resources that might
     be participating in the same business transaction.
      Instead, we'll use Spring's @Transactional annotation in our service method:
    All those database operations will happen using the same underlying connection and will be part of the same transaction.
     The trick here lies in the way we've tied JDBI to Spring using TransactionAwareDataSourceProxy and creating onDemand DAOs.
      When JDBI requests a new Connection, it will get an existing one associated with the current transaction,
       thus integrating its lifecycle to other resources that might be enrolled.
     */
    @Bean
    public Jdbi jdbi(DataSource dataSource,
                     List<JdbiPlugin> jdbiPlugins,
                     List<RowMapper<?>> rowMappers){
        TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy(dataSource);
        Jdbi jdbi = Jdbi.create(proxy);
        jdbi.registerArrayType(String.class, "text");
        jdbi.registerArrayType(Long.class, "bigint");
        jdbiPlugins.forEach(plugin -> jdbi.installPlugin(plugin));
        rowMappers.forEach(mapper -> jdbi.registerRowMapper(mapper));
        return jdbi;
    }

    @Bean
    public JdbiPlugin sqlObjectPlugin() {
        return new SqlObjectPlugin();
    }

    @Bean
    public CustomerDao userDao(Jdbi jdbi) {
        return jdbi.onDemand(CustomerDao.class);
    }

    @Bean
    public DeviceDao deviceDao(Jdbi jdbi){
        return jdbi.onDemand(DeviceDao.class);
    }

    @Bean
    public LocationDao locationDao(Jdbi jdbi){
        return jdbi.onDemand(LocationDao.class);
    }

    @Bean
    public SessionDao sessionDao(Jdbi jdbi){
        return jdbi.onDemand(SessionDao.class);
    }

}
