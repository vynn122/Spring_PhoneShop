package org.zin.com.phoneshopapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.zin.com.phoneshopapi.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> , JpaSpecificationExecutor<Category> {
}
