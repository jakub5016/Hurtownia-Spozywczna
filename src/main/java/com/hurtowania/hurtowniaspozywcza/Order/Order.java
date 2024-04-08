package com.hurtowania.hurtowniaspozywcza.Order;

import com.hurtowania.hurtowniaspozywcza.OrderedProduct.OrderedProduct;
import com.hurtowania.hurtowniaspozywcza.User.Client;
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
    private Client user;

    @OneToMany(mappedBy = "order")
    private List<OrderedProduct> orderedProducts;

}
