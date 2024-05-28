package com.hurtowania.hurtowniaspozywcza.Order;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hurtowania.hurtowniaspozywcza.Order.requests.CreateOrderRequest;


public interface IOrderService {
    void addOrder(CreateOrderRequest request);
    void deleteOrder(long orderId);
    Order getOrderById(long id);
    List<Order> getOrdersByClientId(long clientId);
    boolean updateOrderStatusById(long id, OrderStatus status);
    Page<Order> getOrder(int pageNo, int pageSize);
}
