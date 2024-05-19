package com.hurtowania.hurtowniaspozywcza.ServiceTests;

import com.hurtowania.hurtowniaspozywcza.Order.Order;
import com.hurtowania.hurtowniaspozywcza.Price.Price;
import com.hurtowania.hurtowniaspozywcza.Price.PriceRepository;
import com.hurtowania.hurtowniaspozywcza.Price.PriceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PriceTests {
    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceServiceImpl priceService;
    private Price price;

    @BeforeEach
    public void setup(){
       price = Price.builder()
               .id(1l)
               .price(1d)
               .build();
    }

    @Test
    public void SavePrice_Success(){
        when(priceRepository.save(price)).thenReturn(price);
        Price tmp = priceRepository.save(price);

        assertEquals(price,tmp);
    }

    @Test
    void getPriceById_NotFound() {
    long id = 1L;

    when(priceRepository.findById(id)).thenReturn(Optional.empty());

    Price retrievedPrice = priceService.getPriceById(id);

    assertNull(retrievedPrice);
    }

    @Test
    void getPriceById_Success() {
    long priceId = 1L;

    when(priceRepository.findById(priceId)).thenReturn(Optional.of(price));

    Price retrievedPrice = priceService.getPriceById(priceId);

    assertEquals(price, retrievedPrice);
    }
}
