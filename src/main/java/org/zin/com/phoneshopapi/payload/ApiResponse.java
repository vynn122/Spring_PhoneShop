package org.zin.com.phoneshopapi.payload;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;







    /**
     * Static factory method to create a successful ApiResponse instance.
     *
     * @param <T>     the type of the data in the response
     * @param message the success message
     * @param data    the data to include in the response
     * @return a successful ApiResponse instance
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }

    /**
     * Static factory method to create a failed ApiResponse instance.
     *
     * @param <T>     the type of the data in the response (null for failure)
     * @param message the error message
     * @return a failed ApiResponse instance
     */
    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<>(false, message, null);
    }

}
