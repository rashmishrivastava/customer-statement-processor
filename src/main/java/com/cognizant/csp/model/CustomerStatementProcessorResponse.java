package com.cognizant.csp.model;

import com.cognizant.csp.enums.ProcessingResult;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;

/**
 * Data class for Customer statement processor response.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerStatementProcessorResponse {
    @SerializedName("result")
    private ProcessingResult result = null;

    @SerializedName("errorRecords")
    private List<ErrorRecord> errorRecords = null;

}

