package com.rabobank.csp.api;

import com.rabobank.csp.model.CustomerStatementProcessorRequest;
import com.rabobank.csp.model.CustomerStatementProcessorResponse;
import com.rabobank.csp.service.CustomerStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * The type Customer statement processor api.
 */
@RestController
public class CustomerStatementProcessorAPI {
	
	@Autowired
	private CustomerStatementService customerStatementService;


	/**
	 * Customer statement processor response entity.
	 *
	 * @param customerStatementProcessorItems the customer statement processor items
	 * @return the response entity
	 */
	@PostMapping(path="/customerStatementProcessor/v1", consumes = "application/json",  produces = "application/json")
	public ResponseEntity<CustomerStatementProcessorResponse> customerStatementProcessor(@Valid @RequestBody CustomerStatementProcessorRequest customerStatementProcessorItems) {

		CustomerStatementProcessorResponse transactionResponse= customerStatementService.processor(customerStatementProcessorItems);

		return new ResponseEntity<>(transactionResponse,HttpStatus.OK);
	}


}
