package com.cognizant.csp.enums;

/**
 * The enum Processing result.
 */
public enum ProcessingResult {
    /**
     * Successful processing result.
     */
    SUCCESSFUL,
    /**
     * Incorrect end balance processing result.
     */
    INCORRECT_END_BALANCE,
    /**
     * Duplicate reference processing result.
     */
    DUPLICATE_REFERENCE,
    /**
     * Duplicate reference incorrect end balance processing result.
     */
    DUPLICATE_REFERENCE_INCORRECT_END_BALANCE,
    /**
     * Bad request processing result.
     */
    BAD_REQUEST,
    /**
     * Internal server error processing result.
     */
    INTERNAL_SERVER_ERROR
}
