package com.hurtowania.hurtowniaspozywcza.Price;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements IPriceService{
    private final PriceRepository priceRepository;


    @Override
    public Price savePrice(Price price) {
        return priceRepository.save(price);
    }

    @Override
    public void deletePrice(Price price) {
        priceRepository.delete(price);
    }

    @Override
    public Price getPriceById(long id){
        Optional<Price> optionalPrice = priceRepository.findById(id);
        return optionalPrice.orElse(null);
    }
}
