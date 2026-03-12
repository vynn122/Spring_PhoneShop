package org.zin.com.phoneshopapi.service;

import org.zin.com.phoneshopapi.dto.request.ProductRequest;
import org.zin.com.phoneshopapi.dto.response.ProductResponse;

/**
 * Service responsible for managing products.
 * Provides operations for creating, retrieving,
 * updating and deleting products.
 */
public interface ProductService {

    /**
     * Create a new product.
     *
     * @param dto the product request data
     * @throws NotFoundException if related entities (e.g. brand, category) do not
     *                           exist
     * @return the created product response
     */

    ProductResponse createProduct(ProductRequest dto);

    /**
     * Get product detail by id.
     *
     * @param id the product id
     * @return product detail response
     * @throws NotFoundException if product does not exist
     */

    ProductResponse getProduct(Long id);
}
