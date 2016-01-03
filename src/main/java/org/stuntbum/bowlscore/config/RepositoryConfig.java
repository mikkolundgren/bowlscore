package org.stuntbum.bowlscore.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by mikko on 03/01/16.
 */
@Configuration
@EnableJpaRepositories(basePackages = "org.stuntbum.bowlscore.repository")
@EnableAutoConfiguration
@EntityScan(basePackages = "org.stuntbum.bowlscore.domain")
public class RepositoryConfig {
}
