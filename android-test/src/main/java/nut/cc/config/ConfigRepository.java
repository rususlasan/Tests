package nut.cc.config;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "nut.cc.repositories")
@EnableAutoConfiguration
@EntityScan(basePackages = "nut.cc.entities")
public class ConfigRepository {

}
