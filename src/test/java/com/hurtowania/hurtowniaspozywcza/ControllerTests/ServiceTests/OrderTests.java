package com.hurtowania.hurtowniaspozywcza.ControllerTests.ServiceTests;

import com.hurtowania.hurtowniaspozywcza.Client.Client;
import com.hurtowania.hurtowniaspozywcza.Client.ClientRepository;
import com.hurtowania.hurtowniaspozywcza.Order.Order;
import com.hurtowania.hurtowniaspozywcza.Order.OrderRepository;
import com.hurtowania.hurtowniaspozywcza.Order.OrderServiceImpl;
import com.hurtowania.hurtowniaspozywcza.Order.OrderStatus;
import com.hurtowania.hurtowniaspozywcza.Order.requests.CreateOrderPositionRequest;
import com.hurtowania.hurtowniaspozywcza.Order.requests.CreateOrderRequest;
import com.hurtowania.hurtowniaspozywcza.OrderedProduct.IOrderedProductService;
import com.hurtowania.hurtowniaspozywcza.OrderedProduct.OrderedProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderTests {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private IOrderedProductService orderedProductService;

    @InjectMocks
    private OrderServiceImpl orderService;
    private CreateOrderRequest createOrderRequest;

    @BeforeEach
    public void setup(){
        createOrderRequest = new CreateOrderRequest(1, List.of(
                new CreateOrderPositionRequest(1,1)
        ));
    }


    @Test
    public void AddOrder_Success(){
        Client client = Client.builder()
                .appUser(null)
                .name("Stefan")
                .id(1l)
                .orders(List.of())
                .build();
        Order order = Order.builder()
                .status(OrderStatus.CREATED)
                .build();
        OrderedProduct orderedProduct = OrderedProduct.builder()
                .product(null)
                .price(10)
                .quantity(1)
                .order(order)
                .build();

        when(clientRepository.findById(1l)).thenReturn(Optional.of(client));
        when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);
        when(orderedProductService.createOrderedProduct(order,1,1)).thenReturn(orderedProduct);

        orderService.addOrder(createOrderRequest);
    }

    @Test
    public void AddOrder_NoClient_ResponseStatusException_404(){
        when(clientRepository.findById(1l)).thenReturn(Optional.empty());

        try{
            orderService.addOrder(createOrderRequest);
            assert false;
        }catch(ResponseStatusException e){
            assertEquals(HttpStatus.NOT_FOUND,e.getStatusCode());
        }
    }

     @Test
    public void getOrderById_Success() {
        Order order = Order.builder()
                .id(1L)
                .client(Client.builder().id(1L).build())
                .orderDate(LocalDate.now())
                .status(OrderStatus.CREATED)
                .build();

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Order retrievedOrder = orderService.getOrderById(1L);

        assertEquals(order, retrievedOrder);
    }

    @Test
    public void getOrderById_OrderNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        Order retrievedOrder = orderService.getOrderById(1L);
        assertNull(retrievedOrder);
    }

    @Test
    public void getOrdersByClientId_Success_SingleOrder() {
    long clientId = 1L;
    Order expectedOrder = Order.builder()
        .id(1L)
        .client(Client.builder().id(clientId).build())
        .build();

    when(orderRepository.findByClientId(clientId)).thenReturn(Collections.singletonList(expectedOrder));

    List<Order> retrievedOrders = orderService.getOrdersByClientId(clientId);

    assertEquals(1, retrievedOrders.size());
    assertEquals(expectedOrder, retrievedOrders.get(0));
    }

    @Test
    public void getOrdersByClientId_Success_MultipleOrders() {
    long clientId = 1L;
    Order order1 = Order.builder().id(1L).client(Client.builder().id(clientId).build()).build();
    Order order2 = Order.builder().id(2L).client(Client.builder().id(clientId).build()).build();

    when(orderRepository.findByClientId(clientId)).thenReturn(Arrays.asList(order1, order2));

    List<Order> retrievedOrders = orderService.getOrdersByClientId(clientId);

    assertEquals(2, retrievedOrders.size());

    assertTrue(retrievedOrders.contains(order1));
    assertTrue(retrievedOrders.contains(order2));
    }

    @Test
    public void getOrdersByClientId_NoOrdersFound() {
    long clientId = 1L;

    when(orderRepository.findByClientId(clientId)).thenReturn(Collections.emptyList());

    List<Order> retrievedOrders = orderService.getOrdersByClientId(clientId);

    assertEquals(0, retrievedOrders.size());
    }

    @Test
    public void updateOrderStatusById_Success() {
    long orderId = 1L;
    OrderStatus newStatus = OrderStatus.ACCEPTED;

    Order order = Order.builder()
        .id(orderId)
        .status(OrderStatus.CREATED) 
        .build();
    when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

    boolean updateSuccessful = orderService.updateOrderStatusById(orderId, newStatus);

    assertTrue(updateSuccessful);
    assertEquals(newStatus, order.getStatus());

    verify(orderRepository).save(order);
    }

    @Test
    public void updateOrderStatusById_OrderNotFound() {
    long orderId = 1L;
    OrderStatus newStatus = OrderStatus.ACCEPTED;

    when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

    boolean updateSuccessful = orderService.updateOrderStatusById(orderId, newStatus);

    assertFalse(updateSuccessful);

    verify(orderRepository, never()).save(any(Order.class));
    }
    
}
