package com.cognizant.csp.model;


import com.google.gson.annotations.SerializedName;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Data class for customer statement item
 */
@Data
@AllArgsConstructor
public class CustomerStatementItem {

    @NonNull
    @SerializedName("reference")
    private Long reference;

    @NonNull
    @EqualsAndHashCode.Exclude
    @SerializedName("accountNumber")
    private String accountNumber;

    @NonNull
    @EqualsAndHashCode.Exclude
    @SerializedName("startBalance")
    private BigDecimal startBalance;

    @NonNull
    @EqualsAndHashCode.Exclude
    @SerializedName("mutation")
    private BigDecimal mutation;

    @NonNull
    @EqualsAndHashCode.Exclude
    @SerializedName("description")
    private String description;

    @NonNull
    @EqualsAndHashCode.Exclude
    @SerializedName("endBalance")
    private BigDecimal endBalance;

}

