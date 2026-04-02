package org.zin.com.phoneshopapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zin.com.phoneshopapi.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
