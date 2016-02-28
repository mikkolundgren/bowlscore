package org.stuntbum.bowlscore.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by mikko on 03/01/16.
 */
@Configuration
@EnableJpaRepositories(basePackages = "org.stuntbum.bowlscore.repository")
@EnableAutoConfiguration
@EntityScan(basePackages = "org.stuntbum.bowlscore.domain")
public class RepositoryConfig {

    @Bean
    public BasicDataSource dataSource() throws Exception {


        String dbUrl = "";
        String env = System.getenv("DOMAIN");
        BasicDataSource basicDataSource = new BasicDataSource();
        if ("local".equals(env)) {
            dbUrl = "jdbc:postgresql://localhost:5432/scores";
        } else {
            dbUrl = System.getenv("JDBC_DATABASE_URL");
            String username = System.getenv("JDBC_DATABASE_USERNAME");
            String password = System.getenv("JDBC_DATABASE_PASSWORD");
            basicDataSource.setUsername(username);
            basicDataSource.setPassword(password);
            System.out.println("DBUrl: " + dbUrl + " Username: " + username + " pwd: " + password);
        }

        basicDataSource.setUrl(dbUrl);
        return basicDataSource;
    }
}
