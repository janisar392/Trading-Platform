package com.janisar.service;

import com.janisar.domain.PaymentMethod;
import com.janisar.model.PaymentOrder;
import com.janisar.model.User;
import com.janisar.response.PaymentResponse;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentService {

    PaymentOrder createOrder(User user , Long amount , PaymentMethod paymentMethod);

    PaymentOrder getPaymentOrderById(Long id) throws Exception;

    Boolean ProceedPaymentOrder(PaymentOrder paymentOrder,String paymentId) throws RazorpayException;

    PaymentResponse createRazorpayPaymentLink(User user , Long amount , Long orderId);

    PaymentResponse createStripePaymentLink(User user , Long amount , Long orderId) throws StripeException;
}
