package com.cognizant.csp;

import com.cognizant.csp.CustomerStatementprocessorApplication;
import com.google.gson.Gson;
import com.cognizant.csp.enums.ProcessingResult;
import com.cognizant.csp.model.CustomerStatementItem;
import com.cognizant.csp.model.CustomerStatementProcessorRequest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {CustomerStatementprocessorApplication.class, ServletWebServerFactoryAutoConfiguration.class}, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CustomerStatementProcessorAPIIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private String customerStatementProcessorURI;

    @BeforeEach
    public void setup() {
        customerStatementProcessorURI = "/customer-statement-processor/v1";
    }

    @Test
    public void whenCorrectRequest_thenReturnsSuccess() throws Exception {

        CustomerStatementProcessorRequest customerStatementProcessorRequest = new CustomerStatementProcessorRequest();
        customerStatementProcessorRequest.addAll(
                Arrays.asList(
                        new CustomerStatementItem(442216661L, "NL26RB1267626726", BigDecimal.valueOf(123.43), BigDecimal.valueOf(3), "Paid at Grocery Shop", BigDecimal.valueOf(126.43)),
                        new CustomerStatementItem(442216662L, "NL26RB1267626726", BigDecimal.valueOf(126.20), BigDecimal.valueOf(3), "Paid at Grocery Shop", BigDecimal.valueOf(129.20)),
                        new CustomerStatementItem(442216663L, "NL26RB1267626728", BigDecimal.valueOf(128.60), BigDecimal.valueOf(2), "Paid at Grocery Shop", BigDecimal.valueOf(130.60))));


        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(customerStatementProcessorURI)
                .accept(MediaType.APPLICATION_JSON)
                .content(getJsonString(customerStatementProcessorRequest))
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(Matchers.containsString(ProcessingResult.SUCCESSFUL.toString())));
    }

    @Test
    public void whenDuplicateAndCorrectBalanceRequest_thenReturnsSuccess() throws Exception {

        CustomerStatementProcessorRequest customerStatementProcessorRequest = new CustomerStatementProcessorRequest();
        customerStatementProcessorRequest.addAll(
                Arrays.asList(
                        new CustomerStatementItem(442216661L, "NL26RB1267626726", BigDecimal.valueOf(123.43), BigDecimal.valueOf(3), "Paid at Grocery Shop", BigDecimal.valueOf(126.43)),
                        new CustomerStatementItem(442216661L, "NL26RB1267626726", BigDecimal.valueOf(126.20), BigDecimal.valueOf(3), "Paid at Grocery Shop", BigDecimal.valueOf(129.20)),
                        new CustomerStatementItem(442216663L, "NL26RB1267626728", BigDecimal.valueOf(128.60), BigDecimal.valueOf(2), "Paid at Grocery Shop", BigDecimal.valueOf(130.60))));


        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(customerStatementProcessorURI)
                .accept(MediaType.APPLICATION_JSON)
                .content(getJsonString(customerStatementProcessorRequest))
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(Matchers.containsString(ProcessingResult.DUPLICATE_REFERENCE.toString())));
    }

    @Test
    public void whenNoDuplicateAndInCorrectBalanceRequest_thenReturnsSuccess() throws Exception {

        CustomerStatementProcessorRequest customerStatementProcessorRequest = new CustomerStatementProcessorRequest();
        customerStatementProcessorRequest.addAll(
                Arrays.asList(
                        new CustomerStatementItem(442216661L, "NL26RB1267626726", BigDecimal.valueOf(123.43), BigDecimal.valueOf(3), "Paid at Grocery Shop", BigDecimal.valueOf(126.43)),
                        new CustomerStatementItem(442216662L, "NL26RB1267626726", BigDecimal.valueOf(126.20), BigDecimal.valueOf(6), "Paid at Grocery Shop", BigDecimal.valueOf(129.20)),
                        new CustomerStatementItem(442216663L, "NL26RB1267626728", BigDecimal.valueOf(128.60), BigDecimal.valueOf(2), "Paid at Grocery Shop", BigDecimal.valueOf(130.60))));


        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(customerStatementProcessorURI)
                .accept(MediaType.APPLICATION_JSON)
                .content(getJsonString(customerStatementProcessorRequest))
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(Matchers.containsString(ProcessingResult.INCORRECT_END_BALANCE.toString())));
    }

    @Test
    public void whenDuplicateAndInCorrectBalanceRequest_thenReturnsSuccess() throws Exception {

        CustomerStatementProcessorRequest customerStatementProcessorRequest = new CustomerStatementProcessorRequest();
        customerStatementProcessorRequest.addAll(
                Arrays.asList(
                        new CustomerStatementItem(442216661L, "NL26RB1267626726", BigDecimal.valueOf(123.43), BigDecimal.valueOf(3), "Paid at Grocery Shop", BigDecimal.valueOf(126.43)),
                        new CustomerStatementItem(442216661L, "NL26RB1267626726", BigDecimal.valueOf(126.20), BigDecimal.valueOf(3), "Paid at Grocery Shop", BigDecimal.valueOf(129.20)),
                        new CustomerStatementItem(442216663L, "NL26RB1267626728", BigDecimal.valueOf(128.60), BigDecimal.valueOf(-2), "Paid at Grocery Shop", BigDecimal.valueOf(130.60))));


        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(customerStatementProcessorURI)
                .accept(MediaType.APPLICATION_JSON)
                .content(getJsonString(customerStatementProcessorRequest))
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(Matchers.containsString(ProcessingResult.DUPLICATE_REFERENCE_INCORRECT_END_BALANCE.toString())));
    }

    @Test
    public void whenErrorInJson_thenReturnsBadRequest() throws Exception {
        //contains extra comma after last item in list to make the json object invalid
        String invalidJsonRequestString = "[\n" +
                "  {\n" +
                "    \"reference\": 442216661,\n" +
                "    \"accountNumber\": \"NL26RB1267626726\",\n" +
                "    \"startBalance\": 123.43,\n" +
                "    \"mutation\": 3.43,\n" +
                "    \"decription\": \"Paid to BodyShop\",\n" +
                "    \"endBalance\": 126.86\n" +
                "  },]";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(customerStatementProcessorURI)
                .accept(MediaType.APPLICATION_JSON)
                .content(invalidJsonRequestString)
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content()
                        .string(Matchers.containsString(ProcessingResult.BAD_REQUEST.toString())));
    }

    private String getJsonString(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}
