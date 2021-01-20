package com.cognizant.csp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Customer statement processor application main class
 */
@Slf4j
@SpringBootApplication
public class CustomerStatementProcessorApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        log.info("-------Started Customer Statement Processor----------- ");
        SpringApplication.run(CustomerStatementProcessorApplication.class, args);
    }

}
