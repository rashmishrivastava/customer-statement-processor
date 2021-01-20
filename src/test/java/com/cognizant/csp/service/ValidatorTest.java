package com.cognizant.csp.service;

import com.cognizant.csp.enums.ProcessingResult;
import com.cognizant.csp.model.CustomerStatementItem;
import com.cognizant.csp.model.CustomerStatementProcessorRequest;
import com.cognizant.csp.model.CustomerStatementProcessorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ValidatorTest {

    private Validator validator;

    private CustomerStatementProcessorRequest customerStatementProcessorRequest;

    private CustomerStatementProcessorResponse customerStatementProcessorResponse = null;

    @BeforeEach
    public void setup() {
        customerStatementProcessorRequest = new CustomerStatementProcessorRequest();
        validator = new Validator();
    }

    @Test
    public void whenValidDataThanResultSuccessful() {
        customerStatementProcessorRequest.addAll(
                Arrays.asList(
                        new CustomerStatementItem(442216661L, "NL26RB1267626726", BigDecimal.valueOf(123.43), BigDecimal.valueOf(3.43), "Paid at Grocery Shop", BigDecimal.valueOf(126.86)),
                        new CustomerStatementItem(442216662L, "NL26RB1267626726", BigDecimal.valueOf(126.20), BigDecimal.valueOf(3), "Paid at Grocery Shop", BigDecimal.valueOf(129.20)),
                        new CustomerStatementItem(442216663L, "NL26RB1267626728", BigDecimal.valueOf(128.60), BigDecimal.valueOf(2), "Paid at Grocery Shop", BigDecimal.valueOf(130.60))
                ));
        customerStatementProcessorResponse = validator.validateAndGetErrorRecords(customerStatementProcessorRequest);
        assertEquals(customerStatementProcessorResponse.getResult(), ProcessingResult.SUCCESSFUL);
        assertEquals(customerStatementProcessorResponse.getErrorRecords().size(), 0);
    }

    @Test
    public void whenDuplicateRecordThanResultDuplicate() {
        customerStatementProcessorRequest.addAll(
                Arrays.asList(new CustomerStatementItem(442216661L, "NL26RB1267626726", BigDecimal.valueOf(123.43), BigDecimal.valueOf(3.43), "Paid at Grocery Shop", BigDecimal.valueOf(126.86)),
                        new CustomerStatementItem(442216661L, "NL26RB1267626726", BigDecimal.valueOf(126.20), BigDecimal.valueOf(3), "Paid at Grocery Shop", BigDecimal.valueOf(129.20)),
                        new CustomerStatementItem(442216663L, "NL26RB1267626728", BigDecimal.valueOf(128.60), BigDecimal.valueOf(2), "Paid at Grocery Shop", BigDecimal.valueOf(130.60))
                ));
        customerStatementProcessorResponse = validator.validateAndGetErrorRecords(customerStatementProcessorRequest);
        assertEquals(customerStatementProcessorResponse.getResult(), ProcessingResult.DUPLICATE_REFERENCE);
        assertEquals(customerStatementProcessorResponse.getErrorRecords().size(), 1);
    }

    @Test
    public void whenIncorrectBalanceRecordThanResultIncorrectBalance() {
        customerStatementProcessorRequest.addAll(
                Arrays.asList(new CustomerStatementItem(442216661L, "NL26RB1267626726", BigDecimal.valueOf(123.43), BigDecimal.valueOf(3.43), "Paid at Grocery Shop", BigDecimal.valueOf(126.86)),
                        new CustomerStatementItem(442216662L, "NL26RB1267626726", BigDecimal.valueOf(126.20), BigDecimal.valueOf(5), "Paid at Grocery Shop", BigDecimal.valueOf(130)),
                        new CustomerStatementItem(442216663L, "NL26RB1267626728", BigDecimal.valueOf(128.60), BigDecimal.valueOf(2.40), "Paid at Grocery Shop", BigDecimal.valueOf(130))
                ));
        customerStatementProcessorResponse = validator.validateAndGetErrorRecords(customerStatementProcessorRequest);
        assertEquals(customerStatementProcessorResponse.getResult(), ProcessingResult.INCORRECT_END_BALANCE);
        assertEquals(customerStatementProcessorResponse.getErrorRecords().size(), 2);
    }

    @Test
    public void whenDuplicateAndIncorrectBalanceRecordThanResultDuplicateAndIncorrectBalance() {
        customerStatementProcessorRequest.addAll(
                Arrays.asList(new CustomerStatementItem(442216661L, "NL26RB1267626726", BigDecimal.valueOf(123.43), BigDecimal.valueOf(3.43), "Paid at Grocery Shop", BigDecimal.valueOf(126.43)),
                        new CustomerStatementItem(442216661L, "NL26RB1267626726", BigDecimal.valueOf(126.20), BigDecimal.valueOf(5), "Paid at Grocery Shop", BigDecimal.valueOf(130)),
                        new CustomerStatementItem(42216663L, "NL26RB1267626728", BigDecimal.valueOf(128.60), BigDecimal.valueOf(2.40), "Paid at Grocery Shop", BigDecimal.valueOf(130))
                ));
        customerStatementProcessorResponse = validator.validateAndGetErrorRecords(customerStatementProcessorRequest);
        assertEquals(customerStatementProcessorResponse.getResult(), ProcessingResult.DUPLICATE_REFERENCE_INCORRECT_END_BALANCE);
        assertEquals(customerStatementProcessorResponse.getErrorRecords().size(), 2);
    }

}
