package org.test.hotelsapi.exception;

public class InvalidSearchCriteriaException extends RuntimeException {
    private static final String INVALID_SEARCH_CRITERIA = "Invalid search criteria: %s";

    public InvalidSearchCriteriaException(String criteria) {
        super(String.format(INVALID_SEARCH_CRITERIA, criteria));
    }
}
