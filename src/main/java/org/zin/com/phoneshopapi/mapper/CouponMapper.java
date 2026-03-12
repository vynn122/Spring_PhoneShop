package org.zin.com.phoneshopapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.zin.com.phoneshopapi.dto.request.CouponRequest;
import org.zin.com.phoneshopapi.dto.response.CouponResponse;
import org.zin.com.phoneshopapi.entity.Coupon;

@Mapper(componentModel = "spring")
public interface CouponMapper {
    CouponResponse toDTO(Coupon coupon);

    @Mapping(target = "id", ignore = true) // Ignore ID when creating a new entity
    Coupon toEntity(CouponRequest dto);

    void updateEntityFromDTO(CouponRequest dto,
            @MappingTarget Coupon coupon);
}
