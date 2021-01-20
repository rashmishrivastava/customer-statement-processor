package com.cognizant.csp.model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

/**
 * Data class for Error record.
 */
@Data
public class ErrorRecord {

    @SerializedName("reference")
    private Long reference = null;

    @SerializedName("accountNumber")
    @EqualsAndHashCode.Exclude
    private String accountNumber = null;

}

