package com.example.auth_reference.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@AutoConfiguration
@ComponentScan(basePackages = "com.example.auth_reference")
@EntityScan("com.example.auth_reference.entity")
@EnableJpaRepositories("com.example.auth_reference.repository")
public class AuthAutoConfiguration {
}
