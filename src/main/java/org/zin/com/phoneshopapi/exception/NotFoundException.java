package org.zin.com.phoneshopapi.exception;

/**
 * Custom exception class for handling not found errors in the application.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
