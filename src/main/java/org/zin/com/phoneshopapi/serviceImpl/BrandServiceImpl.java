package org.zin.com.phoneshopapi.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.zin.com.phoneshopapi.dto.BrandRequest;
import org.zin.com.phoneshopapi.dto.BrandResponse;
import org.zin.com.phoneshopapi.entity.Brand;
import org.zin.com.phoneshopapi.exception.NotFoundException;
import org.zin.com.phoneshopapi.mapper.BrandMapper;
import org.zin.com.phoneshopapi.payload.PageResponse;
import org.zin.com.phoneshopapi.repository.BrandRepository;
import org.zin.com.phoneshopapi.service.BrandService;
import org.zin.com.phoneshopapi.utils.PageUtil;
import org.zin.com.phoneshopapi.utils.SpecificationUtil;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    private final JpaSpecificationExecutor<Brand> jpaSpecificationExecutor;

    @Override
    public BrandResponse save(BrandRequest dto) {

        Brand brand = brandMapper.fromRequest(dto);
        Brand saved = brandRepository.save(brand);
        return brandMapper.toResponse(saved);

    }

    @Override
    public BrandResponse getBrandById(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new NotFoundException("Brand " + id + " not found!"));
        return brandMapper.toResponse(brand);
    }


    @Override
    public PageResponse<BrandResponse> getAll(int page, int size, String sort, Map<String, String> filters) {
        Pageable pageable = PageUtil.buildPageable(page, size, sort);
        Page<Brand> result = jpaSpecificationExecutor.findAll(SpecificationUtil.buildSpecification(filters), pageable);
        return PageResponse.from(result.map(brandMapper::toResponse));
    }

//    @Override
//    public BrandResponse updateBrand(BrandRequest dto) {
//        Brand brand = brandMapper.fromRequest(dto);
//        Brand saved = brandRepository.save(brand);
//        return brandMapper.toResponse(saved);
//    }

    @Override
    public BrandResponse updateBrand(Long id, BrandRequest dto) {

        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Brand " + id + " not found"));

        brandMapper.updateEntityFromRequest(dto, brand);

        Brand saved = brandRepository.save(brand);

        return brandMapper.toResponse(saved);
    }
    @Override
    public void delete(Long id) {
        brandRepository.findById(id).orElseThrow(() -> new NotFoundException("Brand " + id + " not found!"));
        brandRepository.deleteById(id);
    }

}
