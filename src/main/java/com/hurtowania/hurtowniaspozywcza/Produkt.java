package com.hurtowania.hurtowniaspozywcza;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Getter
@Setter
@AllArgsConstructor
public class Produkt {
    @Id
    private Long id;

    private String nazwaProduktu;
    private int iloscProduktu;

}
