package com.hurtowania.hurtowniaspozywcza.Product.requests;

import org.springframework.data.domain.Page;

import com.hurtowania.hurtowniaspozywcza.Product.Product;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetAllProductsPage {
    Page<Product> page;
    long pagesNumber;
}
