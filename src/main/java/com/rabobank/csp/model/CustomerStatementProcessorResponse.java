package com.rabobank.csp.model;

import com.google.gson.annotations.SerializedName;
import com.rabobank.csp.enums.ProcessingResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

/**
 * Data class for Customer statement processor response.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerStatementProcessorResponse {
    @SerializedName("result")
    private ProcessingResult result = null;

    @SerializedName("errorRecords")
    private List<ErrorRecord> errorRecords = null;

    /**
     * Result customer statement processor response.
     *
     * @param result the result
     * @return the customer statement processor response
     */
    public CustomerStatementProcessorResponse result(ProcessingResult result) {
        this.result = result;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomerStatementProcessorResponse customerStatementProcessorResponse = (CustomerStatementProcessorResponse) o;
        return Objects.equals(this.result, customerStatementProcessorResponse.result) &&
                Objects.equals(this.errorRecords, customerStatementProcessorResponse.errorRecords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, errorRecords);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CustomerStatementProcessorResponseDTO {\n");

        sb.append("    result: ").append(toIndentedString(result)).append("\n");
        sb.append("    errorRecords: ").append(toIndentedString(errorRecords)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}

