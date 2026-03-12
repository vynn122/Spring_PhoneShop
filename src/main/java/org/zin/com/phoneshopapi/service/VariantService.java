package org.zin.com.phoneshopapi.service;

import java.util.List;

import org.zin.com.phoneshopapi.dto.request.VariantRequest;
import org.zin.com.phoneshopapi.dto.response.VariantResponse;

public interface VariantService {

    /**
     * Create a new variant for a given product.
     *
     * @param productId the product id
     * @param dto       the variant request data
     * @return the created variant response
     * @throws NotFoundException if product does not exist
     */
    VariantResponse createVariant(Long productId, VariantRequest dto);

    /**
     * Update an existing variant by id.
     *
     * @param variantId the variant id
     * @param dto       the variant request data
     * @return the updated variant response
     * @throws NotFoundException if variant does not exist
     */

    VariantResponse updateVariant(Long variantId, VariantRequest dto);

    /**
     * Delete a variant by id.
     * 
     * @param variantId the variant id
     * @throws NotFoundException if variant does not exist
     */
    void deleteVariant(Long variantId);

    /**
     * Get all variants for a given product.
     *
     * @param productId the product id
     * @return list of variant response for the given product id
     * @throws NotFoundException if product does not exist
     */

    List<VariantResponse> getVariantsByProduct(Long productId);

}
