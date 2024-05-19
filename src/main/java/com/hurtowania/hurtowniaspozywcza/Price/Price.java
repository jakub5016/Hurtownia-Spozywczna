package com.hurtowania.hurtowniaspozywcza.Price;

import java.util.List;

import org.hibernate.validator.internal.util.stereotypes.Lazy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hurtowania.hurtowniaspozywcza.PriceLog.PriceLog;
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

    @JsonIgnore
    @OneToOne
    @JoinColumn(nullable = false, name = "product")
    private Product product;

    @OneToOne
    @JoinColumn(nullable = false,  name = "history")
    private PriceLog history;
}
