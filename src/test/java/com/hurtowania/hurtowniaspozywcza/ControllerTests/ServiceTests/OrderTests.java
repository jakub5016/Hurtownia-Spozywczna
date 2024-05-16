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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
