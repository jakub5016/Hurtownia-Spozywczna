package com.hurtowania.hurtowniaspozywcza.Order.requests;

import java.time.LocalDate;
import java.util.List;

public record CreateOrderRequest(long clientId, List<CreateOrderPositionRequest> orderedProducts) {
}
