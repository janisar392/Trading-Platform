package com.janisar.repository;

import com.janisar.model.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOrderRepository extends JpaRepository <PaymentOrder ,Long>{

}
