package com.hurtowania.hurtowniaspozywcza.Order;

import java.util.List;

import com.hurtowania.hurtowniaspozywcza.Order.requests.CreateOrderRequest;
import com.hurtowania.hurtowniaspozywcza.OrderedProduct.OrderedProduct;
import com.hurtowania.hurtowniaspozywcza.Product.ProductDTO;

public interface IOrderService {
    void addOrder(CreateOrderRequest request);
    void deleteOrder(long orderId);
    Order getOrderById(long id);
    List<Order> getOrdersByClientId(long clientId);
    boolean updateOrderStatusById(long id, OrderStatus status);
    /*Order*/boolean updateOrderedProducts(long orderId, List<ProductDTO> productDTOs);
}
