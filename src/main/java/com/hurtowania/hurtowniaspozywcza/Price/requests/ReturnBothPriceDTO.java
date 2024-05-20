package com.hurtowania.hurtowniaspozywcza.Price.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ReturnBothPriceDTO {
    public double currentPrice;
    public double priceFrom30Days;
}
