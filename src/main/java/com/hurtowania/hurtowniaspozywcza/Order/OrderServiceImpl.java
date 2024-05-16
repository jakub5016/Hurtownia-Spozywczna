package com.hurtowania.hurtowniaspozywcza.Order;

import com.hurtowania.hurtowniaspozywcza.Client.Client;
import com.hurtowania.hurtowniaspozywcza.Client.ClientRepository;
import com.hurtowania.hurtowniaspozywcza.Order.requests.CreateOrderPositionRequest;
import com.hurtowania.hurtowniaspozywcza.Order.requests.CreateOrderRequest;
import com.hurtowania.hurtowniaspozywcza.OrderedProduct.IOrderedProductService;
import com.hurtowania.hurtowniaspozywcza.OrderedProduct.OrderedProduct;
import com.hurtowania.hurtowniaspozywcza.OrderedProduct.OrderedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final IOrderedProductService orderedProductService;

    @Override
    @Transactional
    public void addOrder(CreateOrderRequest request) {
        //Powinno zostać zmienione po wprowadzeniu własnego systemu wyjątków
        Client client  = clientRepository.findById(request.clientId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        Order order = Order.builder()
                .client(client)
                .orderDate(LocalDate.now())
                .status(OrderStatus.CREATED)
                .build();

        order = orderRepository.save(order);
        double total = 0d;
        for (CreateOrderPositionRequest position : request.orderedProducts()){
            OrderedProduct orderedProduct = orderedProductService.createOrderedProduct(order,position.productId(), position.quantity());
            total += orderedProduct.getPrice();
        }

        order.setTotalPrice(total);
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);

        if (order != null) {
            for(OrderedProduct orderedProduct : order.getOrderedProducts()){
                orderedProductService.deleteOrderedProduct(orderedProduct);
            }
            orderRepository.delete(order);
        }
    }
}
