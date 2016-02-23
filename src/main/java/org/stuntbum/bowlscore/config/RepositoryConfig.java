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

        BasicDataSource basicDataSource = new BasicDataSource();
        String username = "";
        String password = "";

        String dbUrl = "jdbc:postgresql://";
        if ("local".equals(System.getenv("DOMAIN"))) {
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
            dbUrl = "postgres://" + username + ":" + password + "@" + dbUri.getHost() + dbUri.getPath();
            dbUrl += "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
            basicDataSource.setUsername(username);
            basicDataSource.setPassword(password);
        }
        basicDataSource.setUrl(dbUrl);
        System.out.println("DBUrl: " + dbUrl + " Username: " + username + " pwd: " + password);
        return basicDataSource;
    }
}
