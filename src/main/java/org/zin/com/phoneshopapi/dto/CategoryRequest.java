package org.zin.com.phoneshopapi.dto;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @Nullable
    private String description;
    @Nullable
    private String image;
    @Nullable
    private Boolean status;
    private String createBy;
    private String updateBy;

}
