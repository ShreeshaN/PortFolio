package com.self;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Configuration
@EnableTransactionManagement
@PropertySource( value = { "classpath:application.properties" } )
public class Application {

    // private static final Logger LOG =
    // LoggerFactory.getLogger(Application.class);

    public static void main( String[] args ) throws Exception
    {
        SpringApplication.run(Application.class, args);
    }

    
   
}
