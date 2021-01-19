package com.rabobank.csp.service;

import com.rabobank.csp.model.CustomerStatementItem;
import com.rabobank.csp.model.CustomerStatementProcessorRequest;
import com.rabobank.csp.model.CustomerStatementProcessorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CustomerStatementServiceTest {

    @InjectMocks
    private CustomerStatementService customerStatementService;

    @Mock
    private Validator validator;

    @Test
    public void whenProcessorCalled_thenValidatorInvokedOnce() {
        CustomerStatementProcessorRequest customerStatementProcessorRequest = Mockito.mock(CustomerStatementProcessorRequest.class);

        Mockito.when(customerStatementService.processor(Mockito.any())).thenReturn(Mockito.mock(CustomerStatementProcessorResponse.class));
        customerStatementService.processor(customerStatementProcessorRequest);

        Mockito.verify(validator, Mockito.times(1)).validateAndGetErrorRecords(customerStatementProcessorRequest);
    }

}
