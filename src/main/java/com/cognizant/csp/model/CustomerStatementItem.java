package com.cognizant.csp.model;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Data class for customer statement item
 */
@Getter
@Setter
@AllArgsConstructor
public class CustomerStatementItem {

    @SerializedName("reference")
    private Long reference;

    @SerializedName("accountNumber")
    private String accountNumber;

    @SerializedName("startBalance")
    private BigDecimal startBalance;

    @SerializedName("mutation")
    private BigDecimal mutation;

    @SerializedName("description")
    private String description;

    @SerializedName("endBalance")
    private BigDecimal endBalance;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerStatementItem that = (CustomerStatementItem) o;
        return Objects.equals(reference, that.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference);
    }
}

