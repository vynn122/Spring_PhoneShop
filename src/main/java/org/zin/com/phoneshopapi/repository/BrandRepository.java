package org.zin.com.phoneshopapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.zin.com.phoneshopapi.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> , JpaSpecificationExecutor<Brand> {
}
