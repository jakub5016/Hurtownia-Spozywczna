package com.hurtowania.hurtowniaspozywcza.Order;

import com.hurtowania.hurtowniaspozywcza.Order.requests.CreateOrderRequest;

public interface IOrderService {
    void addOrder(CreateOrderRequest request);
    void deleteOrder(long orderId);
}
