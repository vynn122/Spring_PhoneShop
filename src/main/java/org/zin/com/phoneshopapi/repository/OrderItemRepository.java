package org.zin.com.phoneshopapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zin.com.phoneshopapi.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
