package com.hurtowania.hurtowniaspozywcza.OrderedProduct;

import com.hurtowania.hurtowniaspozywcza.Order.Order;
import com.hurtowania.hurtowniaspozywcza.Product.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class OrderedProduct {
    @EmbeddedId
    private OrderedProductId id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn
    @MapsId("orderId")
    private Order order;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn
    @MapsId("productId")
    private Product product;

    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private int quantity;

}
