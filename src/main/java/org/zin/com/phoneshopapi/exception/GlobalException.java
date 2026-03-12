package org.zin.com.phoneshopapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.zin.com.phoneshopapi.payload.ApiResponse;

import io.swagger.v3.oas.annotations.Hidden;

import java.util.stream.Collectors;

@RestControllerAdvice
@Hidden
public class GlobalException {
    /**
     * Handle NotFoundException and return a 404 response with the error message.
     *
     * @param ex the NotFoundException instance containing the error details
     * @return a ResponseEntity with a 404 status and an ApiResponse containing the
     *         error message
     */

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.fail(ex.getMessage()));
    }

    // end point not found

    /**
     * Handle NoResourceFoundException and return a 404 response with the error
     * message.
     *
     * @param ex the NoResourceFoundException instance containing the error details
     * @return a ResponseEntity with a 404 status and an ApiResponse containing the
     *         error message
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFound(NoResourceFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.fail(ex.getMessage()));
    }

    /**
     * Handle MethodArgumentNotValidException and return a 400 response with the
     * validation errors.
     *
     * @param ex the MethodArgumentNotValidException instance containing the
     *           validation errors
     * @return a ResponseEntity with a 400 status and an ApiResponse containing the
     *         validation errors
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidation(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.fail(errors));
    }

    /**
     * Handle all other exceptions and return a 500 response with a generic error
     * message.
     *
     * @param e the Exception instance containing the error details
     * @return a ResponseEntity with a 500 status and an ApiResponse containing a
     *         generic error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.fail("An unexpected error occurred: " + e.getMessage()));
    }

}
