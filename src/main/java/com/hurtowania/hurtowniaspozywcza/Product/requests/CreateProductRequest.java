package com.hurtowania.hurtowniaspozywcza.Product.requests;


import com.hurtowania.hurtowniaspozywcza.Product.ProductCategory;

public record CreateProductRequest(double price, String name, ProductCategory category, int quantity) {
}
