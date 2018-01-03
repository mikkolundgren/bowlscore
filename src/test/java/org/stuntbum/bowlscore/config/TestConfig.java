package org.stuntbum.bowlscore.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by mikko on 23/01/16.
 */
@Configuration
public class TestConfig {

    @Bean
    public BasicDataSource dataSource() {
        return Mockito.mock(BasicDataSource.class);
    }

}
