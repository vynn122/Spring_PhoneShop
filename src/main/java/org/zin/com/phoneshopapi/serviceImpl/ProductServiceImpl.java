package org.zin.com.phoneshopapi.serviceImpl;

import java.io.IOException;
import java.nio.file.Path;
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

    private Product buildProduct(ProductRequest dto) {
        Product product = productMapper.toEntity(dto);

        product.setBrand(brandRepository.findById(dto.getBrandId())
                .orElseThrow(() -> new NotFoundException("Brand with id " + dto.getBrandId() + " not found")));
        product.setCategory(categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category with id " + dto.getCategoryId() + " not found")));

        return product;

    }

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
