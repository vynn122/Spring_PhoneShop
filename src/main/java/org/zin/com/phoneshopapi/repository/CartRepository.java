package org.zin.com.phoneshopapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zin.com.phoneshopapi.entity.Cart;
import org.zin.com.phoneshopapi.entity.User;

import java.util.Optional;


@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUser(User user);
}
