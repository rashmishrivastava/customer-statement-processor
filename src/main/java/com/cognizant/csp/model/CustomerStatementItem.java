package com.cognizant.csp.model;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Data class for customer statement item
 */
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class CustomerStatementItem {

    @NotNull
    @SerializedName("reference")
    private Long reference;

    @NotNull
    @EqualsAndHashCode.Exclude
    @SerializedName("accountNumber")
    private String accountNumber;

    @NotNull
    @EqualsAndHashCode.Exclude
    @SerializedName("startBalance")
    private BigDecimal startBalance;

    @NotNull
    @EqualsAndHashCode.Exclude
    @SerializedName("mutation")
    private BigDecimal mutation;

    @NotNull
    @EqualsAndHashCode.Exclude
    @SerializedName("description")
    private String description;

    @NotNull
    @EqualsAndHashCode.Exclude
    @SerializedName("endBalance")
    private BigDecimal endBalance;

}

