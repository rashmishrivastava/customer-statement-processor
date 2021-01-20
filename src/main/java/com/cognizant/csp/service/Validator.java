package com.cognizant.csp.service;

import com.cognizant.csp.enums.ProcessingResult;
import com.cognizant.csp.model.CustomerStatementProcessorRequest;
import com.cognizant.csp.model.CustomerStatementProcessorResponse;
import com.cognizant.csp.model.ErrorRecord;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The Validator class is used to perform the validation on the customer statement received.
 */
@Component
public class Validator {

    /**
     * Validate and get error records customer statement processor response.
     *
     * @param customerStatementProcessorRequest the customer statement processor request
     * @return the customer statement processor response
     */
    public CustomerStatementProcessorResponse validateAndGetErrorRecords(CustomerStatementProcessorRequest customerStatementProcessorRequest) {
        List<ErrorRecord> nonUniqueItems = getNonUniqueReferences(customerStatementProcessorRequest);
        List<ErrorRecord> incorrectBalanceItems = getIncorrectEndBalances(customerStatementProcessorRequest);

        CustomerStatementProcessorResponse customerStatementProcessorResponse = new CustomerStatementProcessorResponse();
        if (nonUniqueItems.isEmpty() && incorrectBalanceItems.isEmpty()) {
            customerStatementProcessorResponse.setResult(ProcessingResult.SUCCESSFUL);
            customerStatementProcessorResponse.setErrorRecords(Collections.emptyList());
        } else if (nonUniqueItems.isEmpty()) {
            customerStatementProcessorResponse.setResult(ProcessingResult.INCORRECT_END_BALANCE);
            customerStatementProcessorResponse.setErrorRecords(incorrectBalanceItems);
        } else if (incorrectBalanceItems.isEmpty()) {
            customerStatementProcessorResponse.setResult(ProcessingResult.DUPLICATE_REFERENCE);
            customerStatementProcessorResponse.setErrorRecords(nonUniqueItems);
        } else {
            customerStatementProcessorResponse.setResult(ProcessingResult.DUPLICATE_REFERENCE_INCORRECT_END_BALANCE);
            customerStatementProcessorResponse.setErrorRecords(
                    Stream.of(nonUniqueItems, incorrectBalanceItems)
                            .flatMap(List::stream).distinct()
                            .collect(Collectors.toList()));
        }
        return customerStatementProcessorResponse;
    }

    /**
     * Gets non unique references.
     *
     * @param customerStatementProcessorRequest the customer statement processor request
     * @return the non unique references
     */
    private List<ErrorRecord> getNonUniqueReferences(CustomerStatementProcessorRequest customerStatementProcessorRequest) {
        return customerStatementProcessorRequest.stream()
                .filter(item -> Collections.frequency(customerStatementProcessorRequest, item) > 1)
                .map(transaction -> {
                    ErrorRecord errorRecord = new ErrorRecord();
                    errorRecord.setReference(transaction.getReference());
                    errorRecord.setAccountNumber(transaction.getAccountNumber());
                    return errorRecord;
                }).distinct().collect(Collectors.toList());
    }

    /**
     * Get incorrect end balances list.
     *
     * @param customerStatementProcessorRequest the customer statement processor request
     * @return the list
     */
    private List<ErrorRecord> getIncorrectEndBalances(CustomerStatementProcessorRequest customerStatementProcessorRequest) {
        return customerStatementProcessorRequest.stream()
                .filter(item -> (item.getEndBalance().compareTo(item.getStartBalance().add(item.getMutation()))) != 0)
                .map(transaction -> {
                    ErrorRecord errorRecord = new ErrorRecord();
                    errorRecord.setReference(transaction.getReference());
                    errorRecord.setAccountNumber(transaction.getAccountNumber());
                    return errorRecord;
                }).distinct().collect(Collectors.toList());
    }
}
