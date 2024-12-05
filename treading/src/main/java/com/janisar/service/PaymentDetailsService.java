package com.janisar.service;

import com.janisar.model.PaymentDetails;
import com.janisar.model.User;

public interface PaymentDetailsService {

    public PaymentDetails addPaymentDetails(String accountNumber,
                                            String accountHolderName,
                                            String ifsc,
                                            String bankName,
                                            User user);

    public PaymentDetails getUserPaymentsDetails(User user);
}
