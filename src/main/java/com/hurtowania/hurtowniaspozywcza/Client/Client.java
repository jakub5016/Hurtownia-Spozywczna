package com.hurtowania.hurtowniaspozywcza.Client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hurtowania.hurtowniaspozywcza.AppUser.AppUser;
import com.hurtowania.hurtowniaspozywcza.Order.Order;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;


    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    @OneToOne(mappedBy = "client", fetch = FetchType.LAZY)
    @JsonIgnore
    private AppUser appUser;
}
