package com.hurtowania.hurtowniaspozywcza.Order;

import java.util.List;

import com.hurtowania.hurtowniaspozywcza.Order.requests.CreateOrderRequest;

public interface IOrderService {
    void addOrder(CreateOrderRequest request);
    void deleteOrder(long orderId);
    Order getOrderById(long id);
    List<Order> getOrdersByClientId(long clientId);
    boolean updateOrderStatusById(long id, OrderStatus status);
    boolean updateOrderedProducts(long orderId, List<OrderedProductUpdateRequest> orderedProductUpdates);
}
