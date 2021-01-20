package com.cognizant.csp.service;

import com.cognizant.csp.model.CustomerStatementProcessorRequest;
import com.cognizant.csp.model.CustomerStatementProcessorResponse;
import org.springframework.stereotype.Component;


/**
 * The type Customer statement service.
 */
@Component
public class CustomerStatementService {

    private final Validator validator;

    /**
     * Instantiates a new Customer statement service.
     *
     * @param validator the validator
     */
    public CustomerStatementService(Validator validator) {
        this.validator = validator;
    }


    /**
     * Process customer statement processor response.
     *
     * @param customerStatementProcessorItems the customer statement processor items
     * @return the customer statement processor response
     */
    public CustomerStatementProcessorResponse process(CustomerStatementProcessorRequest customerStatementProcessorItems) {

        return validator.validateAndGetErrorRecords(customerStatementProcessorItems);

    }

}
