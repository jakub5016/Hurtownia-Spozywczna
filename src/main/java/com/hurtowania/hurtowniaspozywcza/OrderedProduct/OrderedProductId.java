package com.hurtowania.hurtowniaspozywcza.OrderedProduct;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@AllArgsConstructor
@Getter@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class OrderedProductId {
    private long productId;
    private long orderId;
}
