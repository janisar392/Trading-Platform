package com.janisar.service;

import com.janisar.domain.OrderType;
import com.janisar.model.Coin;
import com.janisar.model.Order;
import com.janisar.model.OrderItem;
import com.janisar.model.User;

import java.util.List;

public interface OrderService {

    Order createOrder(User user , OrderItem orderItem , OrderType orderType);

    Order getOrderById(Long orderId) throws Exception;

    List<Order> getAllOredrOfUser(Long userId ,OrderType orderType ,String assetSymbol);

    Order processOrder(Coin coin , double quantity , OrderType orderType , User user) throws Exception;
}
