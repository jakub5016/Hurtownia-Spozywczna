package com.hurtowania.hurtowniaspozywcza.Product;


import org.hibernate.validator.internal.util.stereotypes.Lazy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hurtowania.hurtowniaspozywcza.Price.Price;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private int availableQuantity;
    
    @Column(nullable = false)
    private String category;
    
    @Column(nullable = false)
    @Builder.Default
    private boolean archived = false;
    
    @OneToOne(mappedBy = "product")
    private Price price; 
}
