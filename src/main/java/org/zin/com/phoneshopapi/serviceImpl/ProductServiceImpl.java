package org.zin.com.phoneshopapi.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zin.com.phoneshopapi.dto.request.ProductRequest;
import org.zin.com.phoneshopapi.dto.response.ProductResponse;
import org.zin.com.phoneshopapi.entity.Product;
import org.zin.com.phoneshopapi.entity.ProductImage;
import org.zin.com.phoneshopapi.entity.ProductVariant;
import org.zin.com.phoneshopapi.exception.NotFoundException;
import org.zin.com.phoneshopapi.mapper.ProductMapper;
import org.zin.com.phoneshopapi.repository.BrandRepository;
import org.zin.com.phoneshopapi.repository.CategoryRepository;
import org.zin.com.phoneshopapi.repository.ProductRepository;
import org.zin.com.phoneshopapi.service.ProductService;
import org.zin.com.phoneshopapi.utils.FileUploadUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    private final FileUploadUtil fileUploadUtil;

    @Override
    @Transactional
    public ProductResponse createProduct(ProductRequest dto) {

        // build pro
        Product product = buildProduct(dto);

        // geneate
        List<ProductVariant> variants = generateVariants(product, dto.getColors(), dto.getStorages());

        product.setVariants(variants);

        /// images
        List<ProductImage> images = buildImages(product, dto);
        product.setImages(images);

        // save to db

        productRepository.save(product);

        return productMapper.toDTO(product);

    }

    @Override
    public ProductResponse getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product with id " + id + " not found"));

        return productMapper.toDTO(product);
    }

    /// ========= Helper Func ==========

    /**
     * Build a Product entity from the ProductRequest DTO. This includes setting the
     * brand and category associations based on the provided IDs. If the brand or
     * category
     * does not exist, a NotFoundException is thrown. This method does not handle
     * variants or images, which are processed separately.
     * 
     * @param dto the product request data containing the product details and
     *            associated IDs
     * @return a Product entity ready for persistence, with brand and category
     *         associations set
     * @throws NotFoundException if the specified brand or category does not exist
     *                           in the database
     */
    private Product buildProduct(ProductRequest dto) {
        Product product = productMapper.toEntity(dto);

        product.setBrand(brandRepository.findById(dto.getBrandId())
                .orElseThrow(() -> new NotFoundException("Brand with id " + dto.getBrandId() + " not found")));
        product.setCategory(categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category with id " + dto.getCategoryId() + " not found")));

        return product;

    }

    /**
     * Generate a list of ProductVariant entities based on the provided product and
     * the lists of colors and storages. For each combination of color and storage,
     * a new ProductVariant is created with default price and stock values. If
     * either
     * the colors or storages list is null, an empty list of variants is returned.
     *
     * @param product  the product to which the variants will be associated
     * @param colors   the list of colors for the variants
     * @param storages the list of storage options for the variants
     * @return a list of ProductVariant entities representing all combinations of
     *         colors and storages for the given product
     * 
     */
    private List<ProductVariant> generateVariants(
            Product product,
            List<String> colors,
            List<String> storages) {

        List<ProductVariant> variants = new ArrayList<>();

        if (colors == null || storages == null)
            return variants;

        for (String color : colors) {
            for (String storage : storages) {

                ProductVariant variant = new ProductVariant();

                variant.setProduct(product);
                variant.setColor(color);
                variant.setStorage(storage);
                variant.setPrice(0.0);
                variant.setStock(0);

                variants.add(variant);
            }
        }

        return variants;
    }

    /**
     * Build a list of ProductImage entities from the ProductRequest DTO. This
     * method
     * processes the list of image files provided in the DTO, saves each file using
     * the FileUploadUtil, and creates a ProductImage entity for each saved file
     * with
     * the corresponding image URL. If the images list in the DTO is null or empty,
     * an empty list is returned. If any file fails to upload, a RuntimeException is
     * thrown.
     *
     * @param product the product to which the images will be associated
     * @param dto     the product request data containing the list of image files
     * @return a list of ProductImage entities representing the uploaded images for
     *         the given product
     * @throws RuntimeException if any image file fails to upload
     */

    private List<ProductImage> buildImages(Product product, ProductRequest dto) {

        if (dto.getImages() == null || dto.getImages().isEmpty())
            return new ArrayList<>();

        return dto.getImages().stream().map(file -> {

            try {

                String path = fileUploadUtil.save(file, "product");

                ProductImage pImage = new ProductImage();
                pImage.setImageUrl(path);
                pImage.setProduct(product);

                return pImage;

            } catch (IOException e) {
                throw new RuntimeException("Failed to upload image", e);
            }

        }).toList();
    }

}
