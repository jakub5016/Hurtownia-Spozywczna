package com.hurtowania.hurtowniaspozywcza.ServiceTests;

import com.hurtowania.hurtowniaspozywcza.Order.Order;
import com.hurtowania.hurtowniaspozywcza.Price.IPriceService;
import com.hurtowania.hurtowniaspozywcza.Price.Price;
import com.hurtowania.hurtowniaspozywcza.Price.PriceRepository;
import com.hurtowania.hurtowniaspozywcza.PriceLog.PriceLog;
import com.hurtowania.hurtowniaspozywcza.PriceLog.PriceLogRepository;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class ProductTests {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private IPriceService priceService;
    @Mock
    private PriceLogRepository priceLogRepository;

    @InjectMocks
    private ProductServiceImpl productService;
    private Price price;
    private PriceLog priceLog;

    @BeforeEach
    public void setup(){
        price = Price.builder()
                .id(1)
                .price(1)
                .lowestFrom30Days(1)
                .build();

        priceLog = PriceLog.builder()
                .prices(new ArrayList<>(Arrays.asList()))
                .times(new ArrayList<>(Arrays.asList(LocalDate.now())))
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
        when(priceLogRepository.save(any(PriceLog.class))).thenReturn(priceLog);
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        productService.addProduct(request);
    }

    @Test
    public void getProduct_Success_ProductsExist() {
    List<Product> expectedProducts = Arrays.asList(
        Product.builder().id(1L).name("Product 1").build(),
        Product.builder().id(2L).name("Product 2").build()
    );

    when(productRepository.findAll()).thenReturn(expectedProducts);

    List<Product> retrievedProducts = productService.getProduct();

    assertNotNull(retrievedProducts);
    assertEquals(expectedProducts.size(), retrievedProducts.size());

    for (int i = 0; i < retrievedProducts.size(); i++) {
        assertEquals(expectedProducts.get(i).getId(), retrievedProducts.get(i).getId());
        assertEquals(expectedProducts.get(i).getName(), retrievedProducts.get(i).getName());
        }
    }

    @Test
    public void getProduct_Success_NoProductsExist() {

    when(productRepository.findAll()).thenReturn(Collections.emptyList());
    
    List<Product> retrievedProducts = productService.getProduct();
    
    assertNotNull(retrievedProducts);
    assertTrue(retrievedProducts.isEmpty());
    }

    @Test
    void getProductById_Success() {
    long productId = 1L;
    Product product = Product.builder().id(productId).build();

    when(productRepository.findById(productId)).thenReturn(Optional.of(product));

    Product retrievedProduct = productService.getProductById(productId);

    assertEquals(product, retrievedProduct);
    }

    @Test
    void getProductById_NotFound() {
    long productId = 1L;

    when(productRepository.findById(productId)).thenReturn(Optional.empty());

    Product retrievedProduct = productService.getProductById(productId);

    assertNull(retrievedProduct);
    }

    @Test
    void getProductByName_Success() {
    String productName = "Test product";
    Product product = Product.builder().name(productName).build();

    when(productRepository.findByName(productName)).thenReturn(product);

    Product retrievedProduct = productService.getProductByName(productName);

    assertEquals(product, retrievedProduct);
    }

    @Test
    void getProductByName_NotFound() {
    String productName = "Test product";

    when(productRepository.findByName(productName)).thenReturn(null);

    Product retrievedProduct = productService.getProductByName(productName);

    assertNull(retrievedProduct);
    }

    @Test
    void updateProductPriceById_Success() {
        long productId = 1L;
        double newPrice = 10.0;
    
        PriceLog priceLog = PriceLog.builder()
                .prices(new ArrayList<>()) 
                .times(new ArrayList<>())
                .build();
    
        Price price = Price.builder()
                .id(productId)
                .price(5.0) 
                .lowestFrom30Days(5.0)
                .history(priceLog)
                .build();
    
        Product product = Product.builder()
                .id(productId)
                .price(price)
                .build();
    
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
    
        when(priceService.savePrice(any(Price.class))).thenReturn(price);
    
        boolean updateResult = productService.updateProductPriceById(productId, newPrice);
    
        assertTrue(updateResult);
    
        Mockito.verify(priceService).savePrice(any(Price.class));
    }

    @Test
    void updateProductPriceById_ProductNotFound() {
    long productId = 1L;
    double newPrice = 10.0;

    when(productRepository.findById(productId)).thenReturn(Optional.empty());

    boolean updateResult = productService.updateProductPriceById(productId, newPrice);

    assertFalse(updateResult);
    }

    @Test
    public void updateProductAmountById_Success() {
    long productId = 1L;
    int newAmount = 10;

    Product product = Product.builder()
    .id(productId)
    .availableQuantity(5)
    .build();
    when(productRepository.findById(productId)).thenReturn(Optional.of(product));

    boolean updateSuccessful = productService.updateProductAmountById(productId, newAmount);

    assertTrue(updateSuccessful);
    assertEquals(newAmount, product.getAvailableQuantity());

    verify(productRepository).save(product);
    }

    @Test
    public void updateProductAmountById_ProductNotFound() {
    long productId = 1L;
    int newAmount = 10;

    when(productRepository.findById(productId)).thenReturn(Optional.empty());

    boolean updateSuccessful = productService.updateProductAmountById(productId, newAmount);

    assertFalse(updateSuccessful);

    verify(productRepository, never()).save(any(Product.class));
    }

}