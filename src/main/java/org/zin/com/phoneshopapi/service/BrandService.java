package org.zin.com.phoneshopapi.service;

import org.zin.com.phoneshopapi.dto.request.BrandRequest;
import org.zin.com.phoneshopapi.dto.response.BrandResponse;
import org.zin.com.phoneshopapi.payload.PageResponse;

import java.util.Map;

public interface BrandService {
    /**
     * Create new Brand
     * 
     * @param dto the brand request data
     * @return the brand created response
     */
    BrandResponse save(BrandRequest dto);

    /**
     * Get Brand id
     * 
     * @param id the brand id
     * @return brand detail response
     * @throws NotFoundException if brand does not exist
     */
    BrandResponse getBrandById(Long id);

    /**
     * Get all brands with pagination and optional filtering.
     * 
     * @param page    the page number start 1
     * @param size    the page size
     * @param sort    the sorting criteria (e.g. "name,asc")
     * @param filters the filtering criteria as map of field to value
     * @return paginated list of brand response mathcing the criterial
     */
    PageResponse<BrandResponse> getAll(int page, int size, String sort, Map<String, String> filters);

    /**
     * Updated and existing brand by id
     * 
     * @param id  the brand id
     * @param dto the brand request data
     * @return the brand update response
     * @throws NotFoundException if brand does not exist
     */
    BrandResponse updateBrand(Long id, BrandRequest dto);

    /**
     * Delete brand by id
     * 
     * @param id the brand id
     * @throws NotFoundException if brand does not exist
     */
    void delete(Long id);

}
