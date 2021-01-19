package com.cognizant.csp.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * Data class for Error record.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorRecord {
    @SerializedName("reference")
    private Long reference = null;

    @SerializedName("accountNumber")
    private String accountNumber = null;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ErrorRecord errorRecord = (ErrorRecord) o;
        return Objects.equals(this.reference, errorRecord.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference, accountNumber);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ErrorRecordDTO {\n");

        sb.append("    reference: ").append(toIndentedString(reference)).append("\n");
        sb.append("    accountNumber: ").append(toIndentedString(accountNumber)).append("\n");
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

