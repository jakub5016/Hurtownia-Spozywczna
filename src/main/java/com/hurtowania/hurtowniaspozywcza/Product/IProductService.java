package com.hurtowania.hurtowniaspozywcza.Product;

import com.hurtowania.hurtowniaspozywcza.Product.requests.CreateProductRequest;

public interface IProductService {
    void addProduct(CreateProductRequest request);
    void deleteProduct(long productId);
}
