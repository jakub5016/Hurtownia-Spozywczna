package com.hurtowania.hurtowniaspozywcza.OrderedProduct;

import com.hurtowania.hurtowniaspozywcza.Order.Order;
import com.hurtowania.hurtowniaspozywcza.Product.Product;

public interface IOrderedProductService {
    OrderedProduct createOrderedProduct(Order order, long productId, int quantity);
    void deleteOrderedProduct(OrderedProduct orderedProduct);
}
