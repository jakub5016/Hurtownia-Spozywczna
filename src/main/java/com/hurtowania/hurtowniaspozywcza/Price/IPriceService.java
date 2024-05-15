package com.hurtowania.hurtowniaspozywcza.Price;


public interface IPriceService {

    Price savePrice(Price price);

    void deletePrice(Price price);

    Price getPriceById(long id);

}
