package com.hurtowania.hurtowniaspozywcza.Price;

import com.hurtowania.hurtowniaspozywcza.Product.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Price {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private long id;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private double lowestFrom30Days;

    @OneToOne
    @JoinColumn(nullable = false, name = "product")
    private Product product;
}
