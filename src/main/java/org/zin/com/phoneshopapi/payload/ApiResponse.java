package org.zin.com.phoneshopapi.payload;


import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse <T>{
    private boolean success;
    private String message;
    private T data;

    public static <T> ApiResponse<T> success(String message, T data){
        return new ApiResponse<>(true, message,data);
    }

    public static <T> ApiResponse<T> fail(String message){
        return new ApiResponse<>(false, message, null);
    }
}
