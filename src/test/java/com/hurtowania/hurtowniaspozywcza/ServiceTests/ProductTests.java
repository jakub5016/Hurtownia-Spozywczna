package com.hurtowania.hurtowniaspozywcza.ServiceTests;

import com.hurtowania.hurtowniaspozywcza.Order.Order;
import com.hurtowania.hurtowniaspozywcza.Price.IPriceService;
import com.hurtowania.hurtowniaspozywcza.Price.Price;
import com.hurtowania.hurtowniaspozywcza.Price.PriceRepository;
import com.hurtowania.hurtowniaspozywcza.Product.Product;
import com.hurtowania.hurtowniaspozywcza.Product.ProductRepository;
import com.hurtowania.hurtowniaspozywcza.Product.ProductServiceImpl;
import com.hurtowania.hurtowniaspozywcza.Product.requests.CreateProductRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductTests {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private IPriceService priceService;

    @InjectMocks
    private ProductServiceImpl productService;
    private Price price;

    @BeforeEach
    public void setup(){
        price = Price.builder()
                .id(1)
                .price(1)
                .lowestFrom30Days(1)
                .build();
    }

    @Test
    void AddProduct_Success(){
        Product product = Product.builder()
                .id(1)
                .name(null)
                .category(null)
                .availableQuantity(1)
                .build();

        CreateProductRequest request = new CreateProductRequest(1, null, null, 1);
        when(priceService.savePrice(any(Price.class))).thenReturn(price);
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        productService.addProduct(request);
    }

}

