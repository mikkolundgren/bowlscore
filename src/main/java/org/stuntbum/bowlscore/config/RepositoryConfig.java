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
    public DataSource dataSource() throws Exception {

        org.apache.tomcat.jdbc.pool.DataSource dataSource
                = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        String username = "";
        String password = "";

        String dbUrl = "jdbc:postgresql://";
        String env = System.getenv("DOMAIN");
        if ("local".equals(env)) {
            dbUrl += "localhost:5432/scores";
        } else {
            URI dbUri = new URI(System.getenv("DATABASE_URL"));

            if (dbUri.getUserInfo() != null) {
                String[] creds = dbUri.getUserInfo().split(":");
                if (creds.length > 0) {
                    username = creds[0];
                    password = creds[1];
                }
            }
            dbUrl = "jdbc:postgresql://" + username + ":" + password + "@" + dbUri.getHost() + dbUri.getPath();
            dbUrl += "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
            dataSource.setUsername(username);
            dataSource.setPassword(password);
        }
        dataSource.setUrl(dbUrl);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnReturn(true);
        dataSource.setValidationQuery("SELECT 1");
        System.out.println("DBUrl: " + dbUrl + " Username: " + username + " pwd: " + password);
        return dataSource;
    }
}
