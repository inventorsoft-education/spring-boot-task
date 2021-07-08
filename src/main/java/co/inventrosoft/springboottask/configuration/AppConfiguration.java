package co.inventrosoft.springboottask.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class AppConfiguration {
    private final JdbcConfig jdbcConfig;

    public AppConfiguration(JdbcConfig jdbcConfig) {
        this.jdbcConfig = jdbcConfig;
    }

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }


}
