package org.zin.com.phoneshopapi.dto;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandRequest {



    @NotBlank(message = "name is required")
    private String name;

    @Nullable
    private String description;
    @Nullable
    private String image;
    @Nullable
    private Boolean status;
    private String createdBy;
    private String updatedBy;


}
