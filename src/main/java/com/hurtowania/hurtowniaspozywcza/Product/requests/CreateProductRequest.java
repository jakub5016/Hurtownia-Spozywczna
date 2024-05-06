package com.hurtowania.hurtowniaspozywcza.Product.requests;


public record CreateProductRequest(double price, String name, String category, int quantity) {
}
