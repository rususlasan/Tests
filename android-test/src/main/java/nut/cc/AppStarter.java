package nut.cc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class AppStarter extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AppStarter.class, args).registerShutdownHook();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AppStarter.class);
    }

}
