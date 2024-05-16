package com.hurtowania.hurtowniaspozywcza.Price.requests;

import com.hurtowania.hurtowniaspozywcza.PriceLog.PriceLog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class ReturnAllPricesDTO {
    private ReturnBothPriceDTO prices;
    private PriceLog logs;
}
