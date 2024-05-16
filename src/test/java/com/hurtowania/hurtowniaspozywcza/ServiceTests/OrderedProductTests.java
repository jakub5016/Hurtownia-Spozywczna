package com.hurtowania.hurtowniaspozywcza.ServiceTests;

import com.hurtowania.hurtowniaspozywcza.Order.Order;
import com.hurtowania.hurtowniaspozywcza.Order.requests.CreateOrderPositionRequest;
import com.hurtowania.hurtowniaspozywcza.Order.requests.CreateOrderRequest;
import com.hurtowania.hurtowniaspozywcza.OrderedProduct.OrderedProduct;
import com.hurtowania.hurtowniaspozywcza.OrderedProduct.OrderedProductId;
import com.hurtowania.hurtowniaspozywcza.OrderedProduct.OrderedProductRepository;
import com.hurtowania.hurtowniaspozywcza.OrderedProduct.OrderedProductServiceImpl;
import com.hurtowania.hurtowniaspozywcza.Price.Price;
import com.hurtowania.hurtowniaspozywcza.Product.Product;
import com.hurtowania.hurtowniaspozywcza.Product.ProductRepository;
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
public class OrderedProductTests {
    @Mock
    private OrderedProductRepository orderedProductRepository;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderedProductServiceImpl orderedProductService;
    private Order order;

    @BeforeEach
    public void setup(){
    order = Order.builder()
            .id(1l)
            .build();
    }

    @Test
    public void CreateOrderedProduct_Success(){
        Product product = Product.builder()
                .id(1l)
                .availableQuantity(10)
                .price(Price.builder().price(3).build())
                .build();
        OrderedProduct orderedProduct = OrderedProduct.builder()
                .id(new OrderedProductId(order.getId(), 1))
                .order(order)
                .product(product)
                .quantity(5)
                .price(3*5)
                .build();
        when(productRepository.findById(1l)).thenReturn(Optional.of(product));
        when(orderedProductRepository.save(orderedProduct)).thenReturn(orderedProduct);

        OrderedProduct tmp = orderedProductService.createOrderedProduct(order, 1,5);

        assertEquals(orderedProduct, tmp);
    }

    @Test
    public void CreateOrderedProduct_NoProduct_ResponseStatusException_404(){
        when(productRepository.findById(1l)).thenReturn(Optional.empty());

        try{
            orderedProductService.createOrderedProduct(order, 1,5);
            assert false;
        }catch(ResponseStatusException e){
            assertEquals(HttpStatus.NOT_FOUND,e.getStatusCode());
        }
    }

    @Test
    public void CreateOrderedProduct_Above_Avaliable_Quantity_ResponseStatusException_409(){
        Product product = Product.builder()
                .id(1l)
                .availableQuantity(1)
                .price(Price.builder().price(3).build())
                .build();
        when(productRepository.findById(1l)).thenReturn(Optional.of(product));

        try{
            orderedProductService.createOrderedProduct(order, 1,5);
            assert false;
        }catch(ResponseStatusException e){
            assertEquals(HttpStatus.CONFLICT,e.getStatusCode());
        }
    }

}
