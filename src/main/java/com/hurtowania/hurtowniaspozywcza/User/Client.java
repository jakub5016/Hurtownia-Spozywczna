package com.hurtowania.hurtowniaspozywcza.User;

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

    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}
