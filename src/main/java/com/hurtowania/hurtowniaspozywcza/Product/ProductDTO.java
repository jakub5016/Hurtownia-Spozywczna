package com.hurtowania.hurtowniaspozywcza.Product;

import lombok.*;

@Getter
@Setter
public class ProductDTO {
    private long oldId;
    private long id;
    private int Quantity;

    public long getId() {
        return id;
    }
}