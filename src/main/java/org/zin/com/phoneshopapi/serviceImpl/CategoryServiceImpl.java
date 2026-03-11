package org.zin.com.phoneshopapi.serviceImpl;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.zin.com.phoneshopapi.dto.CategoryRequest;
import org.zin.com.phoneshopapi.dto.CategoryResponse;
import org.zin.com.phoneshopapi.entity.Category;
import org.zin.com.phoneshopapi.exception.NotFoundException;
import org.zin.com.phoneshopapi.mapper.CategoryMapper;
import org.zin.com.phoneshopapi.payload.PageResponse;
import org.zin.com.phoneshopapi.repository.CategoryRepository;
import org.zin.com.phoneshopapi.service.CategoryService;
import org.zin.com.phoneshopapi.utils.PageUtil;
import org.zin.com.phoneshopapi.utils.SpecificationUtil;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final JpaSpecificationExecutor<Category> jpaSpecificationExecutor;
    private final CategoryService categoryService;


    @Override
    public CategoryResponse save(CategoryRequest dto) {
        ///  get data from request
        Category category = categoryMapper.toEntity(dto);
        Category save = categoryRepository.save(category);
        return categoryMapper.toDto(save);
    }

    @Override
    public CategoryResponse getById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category " + id + " not found!"));
        return categoryMapper.toDto(category);
    }

    @Override
    public PageResponse<CategoryResponse> getAll(int page, int size, String sort, Map<String, String> filters) {
        Pageable pageable = PageUtil.buildPageable(page, size, sort);
        Page<Category> categories = jpaSpecificationExecutor.findAll(SpecificationUtil.buildSpecification(filters), pageable);

        return PageResponse.from(categories.map(categoryMapper::toDto));

    }


    @Override
    public CategoryResponse update(Long id,CategoryRequest dto) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category " + id + " not found!"));
        categoryMapper.updateEntityFromRequest(dto, category);
        Category saved = categoryRepository.save(category);
        return categoryMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException("Category " + id + " not found!");
        }
        categoryRepository.deleteById(id);
    }

}
