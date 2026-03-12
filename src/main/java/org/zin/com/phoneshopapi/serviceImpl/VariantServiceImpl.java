package org.zin.com.phoneshopapi.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zin.com.phoneshopapi.dto.request.VariantRequest;
import org.zin.com.phoneshopapi.dto.response.VariantResponse;
import org.zin.com.phoneshopapi.entity.Product;
import org.zin.com.phoneshopapi.entity.ProductVariant;
import org.zin.com.phoneshopapi.exception.NotFoundException;
import org.zin.com.phoneshopapi.mapper.VariantMapper;
import org.zin.com.phoneshopapi.repository.ProductRepository;
import org.zin.com.phoneshopapi.repository.VariantRepository;
import org.zin.com.phoneshopapi.service.VariantService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VariantServiceImpl implements VariantService {
    private final VariantRepository variantRepository;
    private final ProductRepository productRepository;
    private final VariantMapper variantMapper;

    @Override
    public VariantResponse updateVariant(Long variantId, VariantRequest dto) {
        ProductVariant variant = variantRepository.findById(variantId)
                .orElseThrow(() -> new NotFoundException("Variant with id " + variantId + " not found"));

        if (dto.getPrice() != null)
            variant.setPrice(dto.getPrice());

        if (dto.getStock() != null)
            variant.setStock(dto.getStock());

        if (dto.getDiscountType() != null)
            variant.setDiscountType(dto.getDiscountType());

        if (dto.getDiscountValue() != null)
            variant.setDiscountValue(dto.getDiscountValue());

        variantRepository.save(variant);

        return variantMapper.toDTO(variant);
    }

    @Override
    public VariantResponse createVariant(Long productId, VariantRequest dto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product with id " + productId + " not found"));

        ProductVariant variant = variantMapper.toEntity(dto);
        variant.setProduct(product);
        variantRepository.save(variant);
        return variantMapper.toDTO(variant);

    }

    @Override
    public void deleteVariant(Long variantId) {
        ProductVariant variant = variantRepository.findById(variantId)
                .orElseThrow(() -> new NotFoundException("Variant with id " + variantId + " not found"));

        variantRepository.delete(variant);

    }

    @Override
    public List<VariantResponse> getVariantsByProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product with id " + productId + " not found"));

        return product.getVariants()
                .stream()
                .map(variantMapper::toDTO)
                .toList();
    }

}