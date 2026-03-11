package org.zin.com.phoneshopapi.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.zin.com.phoneshopapi.dto.BrandRequest;
import org.zin.com.phoneshopapi.dto.BrandResponse;
import org.zin.com.phoneshopapi.entity.Brand;
import org.zin.com.phoneshopapi.payload.PageResponse;

import java.util.List;
import java.util.Map;

public interface BrandService {
    BrandResponse save(BrandRequest dto);
    BrandResponse getBrandById(Long id);

    PageResponse<BrandResponse> getAll(int page, int size, String sort, Map<String,String> filters);

    // update
    BrandResponse updateBrand(Long id,BrandRequest dto);
    void delete(Long id);


}
