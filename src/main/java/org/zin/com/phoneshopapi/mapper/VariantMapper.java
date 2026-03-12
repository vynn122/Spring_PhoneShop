package org.zin.com.phoneshopapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.zin.com.phoneshopapi.dto.request.VariantRequestDTO;
import org.zin.com.phoneshopapi.dto.response.VariantResponse;
import org.zin.com.phoneshopapi.entity.DiscountType;
import org.zin.com.phoneshopapi.entity.ProductVariant;

@Mapper(componentModel = "spring")
public interface VariantMapper {

    @Mapping(target = "finalPrice", expression = "java(calculateFinalPrice(variant))")
    VariantResponse toDTO(ProductVariant variant);

    ProductVariant toEntity(VariantRequestDTO dto);

    void updateEntityFromDTO(VariantRequestDTO dto,
            @MappingTarget ProductVariant variant);

    default Double calculateFinalPrice(ProductVariant variant) {

        if (variant.getDiscountType() == null
                || variant.getDiscountValue() == null)
            return variant.getPrice();

        if (variant.getDiscountType() == DiscountType.PERCENTAGE) {
            return variant.getPrice()
                    - (variant.getPrice()
                            * variant.getDiscountValue() / 100);
        }

        return variant.getPrice() - variant.getDiscountValue();
    }

}