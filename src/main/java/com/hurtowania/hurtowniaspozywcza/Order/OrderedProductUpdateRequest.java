package com.hurtowania.hurtowniaspozywcza.Order;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedProductUpdateRequest {
    private long id; // ID zamówionego produktu
    private long newProductId; // Nowy identyfikator produktu
    private int quantity; // Nowa ilość zamówionego produktu
}
