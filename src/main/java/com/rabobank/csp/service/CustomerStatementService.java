package com.rabobank.csp.service;

import com.rabobank.csp.model.CustomerStatementProcessorRequest;
import com.rabobank.csp.model.CustomerStatementProcessorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Customer statement service class for processing customer statement
 */
@Component
public class CustomerStatementService {

    private static final Logger log = LoggerFactory.getLogger(CustomerStatementService.class);

    @Autowired
    private Validator validator;


    /**
     * Processor customer statement processor response.
     *
     * @param customerStatementProcessorItems the customer statement processor items
     * @return the customer statement processor response
     */
    public CustomerStatementProcessorResponse processor(CustomerStatementProcessorRequest customerStatementProcessorItems){

        return validator.validateAndGetErrorRecords(customerStatementProcessorItems);

    }

}
