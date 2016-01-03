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
    public BasicDataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        //String username = dbUri.getUserInfo().split(":")[0];
        //String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        //basicDataSource.setUsername(username);
        //basicDataSource.setPassword(password);
        return basicDataSource;
    }
}
