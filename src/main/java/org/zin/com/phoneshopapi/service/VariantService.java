package org.zin.com.phoneshopapi.service;

import java.util.List;

import org.zin.com.phoneshopapi.dto.request.VariantRequest;
import org.zin.com.phoneshopapi.dto.response.VariantResponse;

public interface VariantService {

    VariantResponse updateVariant(Long variantId, VariantRequest dto);

    VariantResponse createVariant(Long productId, VariantRequest dto);

    void deleteVariant(Long variantId);

    List<VariantResponse> getVariantsByProduct(Long productId);

}
