package com.hurtowania.hurtowniaspozywcza.Order;

import com.hurtowania.hurtowniaspozywcza.Client.Client;
import com.hurtowania.hurtowniaspozywcza.Client.ClientRepository;
import com.hurtowania.hurtowniaspozywcza.Order.requests.CreateOrderPositionRequest;
import com.hurtowania.hurtowniaspozywcza.Order.requests.CreateOrderRequest;
import com.hurtowania.hurtowniaspozywcza.OrderedProduct.IOrderedProductService;
import com.hurtowania.hurtowniaspozywcza.OrderedProduct.OrderedProduct;
import com.hurtowania.hurtowniaspozywcza.OrderedProduct.OrderedProductId;
import com.hurtowania.hurtowniaspozywcza.Product.Product;
import com.hurtowania.hurtowniaspozywcza.Product.ProductDTO;
import com.hurtowania.hurtowniaspozywcza.Product.ProductRepository;
import com.hurtowania.hurtowniaspozywcza.Product.IProductService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final IOrderedProductService orderedProductService;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void addOrder(CreateOrderRequest request) {
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

    @Override
    public Order getOrderById(long id){
        Optional<Order> optionalOrder = orderRepository.findById(id);

        return optionalOrder.orElse(null);
    }

    @Override
    public List<Order> getOrdersByClientId(long clientId) {
        return orderRepository.findByClientId(clientId);
    }

    @Override
    public boolean updateOrderStatusById(long id, OrderStatus status) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setStatus(OrderStatus.valueOf(String.valueOf(status)));
            if (status == OrderStatus.FINALIZED){
                order.setDeliveryDate(LocalDate.now());


            }else if (status == OrderStatus.ACCEPTED){
                List<OrderedProduct> orderedProducts = order.getOrderedProducts();
                List<Product> products = orderedProducts.stream().map(OrderedProduct::getProduct).toList();

                for (int i = 0; i < orderedProducts.size(); i++) {
                    OrderedProduct orderedProduct = orderedProducts.get(i);
                    Product product=products.get(i);

                    int newQuantity = product.getAvailableQuantity() - orderedProduct.getQuantity();

                    if(newQuantity >= 0){

                        product.setAvailableQuantity(newQuantity);
                    }else{
                        product.setAvailableQuantity(0);
                    }
                }

                productRepository.saveAll(products);

            }
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    @Override
    public Page<Order> getOrder(int pageNo, int pageSize, String type) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        
        if ("all".equalsIgnoreCase(type)) {
            return orderRepository.findAll(pageable);
        } else if ("current".equalsIgnoreCase(type)) {
            List<OrderStatus> statuses = Arrays.asList(OrderStatus.CREATED, OrderStatus.IN_DELIVERY, OrderStatus.IN_PROGRESS, OrderStatus.ACCEPTED);
            return orderRepository.findByStatusIn(statuses, pageable);
        } else {
            List<OrderStatus> statuses = Arrays.asList(OrderStatus.CANCELLED, OrderStatus.FINALIZED, OrderStatus.REJECTED);
            return orderRepository.findByStatusIn(statuses, pageable);
        }
    }
    
}
