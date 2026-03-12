package org.zin.com.phoneshopapi.service;

import org.zin.com.phoneshopapi.dto.request.BrandRequest;
import org.zin.com.phoneshopapi.dto.response.BrandResponse;
import org.zin.com.phoneshopapi.payload.PageResponse;

import java.util.Map;

public interface BrandService {
    BrandResponse save(BrandRequest dto);

    BrandResponse getBrandById(Long id);

    PageResponse<BrandResponse> getAll(int page, int size, String sort, Map<String, String> filters);

    // update
    BrandResponse updateBrand(Long id, BrandRequest dto);

    void delete(Long id);

}
