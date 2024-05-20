package com.hurtowania.hurtowniaspozywcza.Order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hurtowania.hurtowniaspozywcza.Client.Client;
import com.hurtowania.hurtowniaspozywcza.OrderedProduct.OrderedProduct;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="DBOrder")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private long id;
    @Column(nullable = false)
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    @Column(nullable = false)
    private OrderStatus status;
    @Column(nullable = false)
    private double totalPrice;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnoreProperties("orders")
    private Client client;


    @OneToMany(mappedBy = "order")
    private List<OrderedProduct> orderedProducts;

    public OrderedProduct getOrderedProductByProductId(long productId) {
        for (OrderedProduct orderedProduct : orderedProducts) {
            if (orderedProduct.getProduct().getId() == productId) {
                return orderedProduct;
            }
        }
        return null; // Return null if the ordered product with the specified ID is not found
    }

}
