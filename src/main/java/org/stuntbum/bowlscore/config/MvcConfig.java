package org.stuntbum.bowlscore.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mikko on 03/01/16.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"org.stuntbum.bowlscore.controller"})
public class MvcConfig {
}
