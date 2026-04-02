package org.zin.com.phoneshopapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zin.com.phoneshopapi.entity.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}