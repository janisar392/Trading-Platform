package com.janisar.service;

import com.janisar.model.Order;
import com.janisar.model.User;
import com.janisar.model.Wallet;

public interface WalletService {

    Wallet getUserWallet(User user);
    Wallet addBalance(Wallet wallet , Long money);
    Wallet findWalletById(Long id) throws Exception;
    Wallet walletToWalletTransfer(User sender ,Wallet receiverWallet ,Long amount ) throws Exception;
    Wallet payOrderPayment(Order order , User user) throws Exception;
}
