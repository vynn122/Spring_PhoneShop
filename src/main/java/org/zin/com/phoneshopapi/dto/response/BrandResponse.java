package org.zin.com.phoneshopapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandResponse {
    private Long id;
    private String name;
    private String description;
    private String image;
    private boolean status;
    // private String createdBy;
    // private String updatedBy;

}
