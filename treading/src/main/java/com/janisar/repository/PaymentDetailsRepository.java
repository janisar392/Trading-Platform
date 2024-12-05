package com.janisar.repository;

import com.janisar.model.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails,Long > {

    PaymentDetails findByUserId(Long userId);
}
